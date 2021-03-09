import javax.swing.*;
import java.sql.*;


public class AppCustomer extends JFrame {

    public static void main(String[] args) {
        AppCustomer frameTabel = new AppCustomer();
    }

    JLabel welcome = new JLabel("Welcome to Customer App, enter wanted title in field below.");
    JPanel panel = new JPanel();
    JTextField titleSearch = new JTextField("Search book by title");
    JTextArea searchResult = new JTextArea("Search results will be displayed here" + "\n" + "Make sure the book is available before borrowing.");
    JButton toAppAdmin = new JButton("to Admin App");
    JButton bSearch = new JButton("Search");
    JButton bShowAvailBooks = new JButton("Show available books");
    JButton bShowAllBooks = new JButton("Show all books");
    JButton bBorrowBook = new JButton("Borrow book");
    JTextField borrowKundID = new JTextField("kundID");
    JTextField borrowInv = new JTextField("invID");
    JLabel borrowInstructions = new JLabel("Input your 'kundID' and the books 'invID' to borow book.");

    AppCustomer() {
        super("Customer App");
        setSize(500, 500);
        setLocation(500, 20);
        panel.setLayout(null);
        titleSearch.setBounds(10, 80, 250, 20);
        searchResult.setBounds(10, 120, 480, 200);
        welcome.setBounds(10, 50, 500, 20);
        toAppAdmin.setBounds(10, 10, 120, 20);
        bSearch.setBounds(390, 450, 100, 20);
        bShowAvailBooks.setBounds(10, 450, 200, 20);
        bShowAllBooks.setBounds(10, 420, 200, 20);
        bBorrowBook.setBounds(10, 390, 200, 20);
        borrowKundID.setBounds(220, 390, 60, 20);
        borrowInv.setBounds(300, 390, 60, 20);
        borrowInstructions.setBounds(10, 360, 500, 20);
        panel.add(welcome);
        panel.add(titleSearch);
        panel.add(searchResult);
        panel.add(toAppAdmin);
        panel.add(bSearch);
        panel.add(bShowAvailBooks);
        panel.add(bShowAllBooks);
        panel.add(bBorrowBook);
        panel.add(borrowKundID);
        panel.add(borrowInv);
        panel.add(borrowInstructions);
        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        actionlogin();
    }

    public void actionlogin() {
        toAppAdmin.addActionListener(ae -> {
            AppAdmin regFace = new AppAdmin();
            regFace.setVisible(true);
            dispose();
        });
        bSearch.addActionListener(ae -> {
            Connection con;
            String q1 = "select titel, författare, sidor, klassifikation, lånadAv, inv from inl1Base.böcker where titel REGEXP  ? ;";
            String input = titleSearch.getText();
            String ans = "";
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost/inl1Base", Login.uName, String.valueOf(Login.pWord));
                PreparedStatement pState = con.prepareStatement(q1);
                pState.setString(1, input);
                ResultSet rs = pState.executeQuery();
                while (rs.next())

                    ans = "Titel: " + (rs.getString(1)
                            + "\n" + "Författare: " + rs.getString(2)
                            + "\n" + "Antal Sidor: " + rs.getInt(3)
                            + "\n" + "Klassifikation: " + rs.getString(4)
                            + "\n" + "Utlånad till: => " + rs.getInt(5) + " <="
                            + " , Om '0' Ledig för utlåning annars upptagen."
                            + "\n" + "Inventarienummer: " + rs.getInt(6));
                con.close();
                if (ans.equals("")) {
                    searchResult.setText("No matches found");
                } else {
                    searchResult.setText(ans);
                }

            } catch (SQLException throwables) {
                searchResult.setText("invalid input. Please try again.");
                throwables.printStackTrace();
            }


        });
        bShowAvailBooks.addActionListener(ae -> {
            Connection con;
            String q1 = "select titel, författare, lånadAv from böcker where lånadAv is null;";
            String ans = "╒════════════════════════════════╤═════════════════════════════╤═══════════════════════╕\n" +
                    "│ TITEL                        . │ Författare                . │ Utlånad till / kundID │\n" +
                    "╘════════════════════════════════╧═════════════════════════════╧═══════════════════════╛";
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost/inl1Base", Login.uName, String.valueOf(Login.pWord));
                Statement state = con.prepareStatement(q1);
                ResultSet rs = state.executeQuery(q1);
                while (rs.next())
                    ans = ans + "\n   " + (rs.getString(1)
                            + "             " + rs.getString(2)
                            + "                           " + rs.getInt(3));
                con.close();
                searchResult.setText(ans);


            } catch (SQLException throwables) {

                throwables.printStackTrace();
            }


        });
        bShowAllBooks.addActionListener(ae -> {
            Connection con;
            String q1 = "select titel, författare, lånadAv from böcker;";
            String ans = "╒════════════════════════════════╤═════════════════════════════╤═══════════════════════╕\n" +
                    "│ TITEL                        . │ Författare                . │ Utlånad till / kundID │\n" +
                    "╘════════════════════════════════╧═════════════════════════════╧═══════════════════════╛";
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost/inl1Base", Login.uName, String.valueOf(Login.pWord));
                Statement state = con.prepareStatement(q1);
                ResultSet rs = state.executeQuery(q1);
                while (rs.next())
                    ans = ans + "\n   " + (rs.getString(1)
                            + "             " + rs.getString(2)
                            + "                           " + rs.getInt(3));
                con.close();
                searchResult.setText(ans);


            } catch (SQLException throwables) {

                throwables.printStackTrace();
            }


        });
        bBorrowBook.addActionListener(ae -> {
            Connection con;
            String q1 = "UPDATE böcker SET lånadAv = ? where inv = ?;";
            String kundIdInput = borrowKundID.getText();
            String invInput = borrowInv.getText();
            String ans = "success";
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost/inl1Base", Login.uName, String.valueOf(Login.pWord));
                PreparedStatement pState = con.prepareStatement(q1);
                pState.setString(1, kundIdInput);
                pState.setString(2, invInput);
                pState.execute();
                con.close();
                searchResult.setText(ans);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }


}
