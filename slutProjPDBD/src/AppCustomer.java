import javax.swing.*;
import java.sql.*;


public class AppCustomer extends JFrame {

    public static void main(String[] args) {
        AppCustomer frameTabel = new AppCustomer();
    }

    JLabel welcome = new JLabel("Welcome to Customer App, enter wanted title in field below.");
    JPanel panel = new JPanel();
    //    JTextField titleSearch = new JTextField("Search book by title");
    JTextField titleSearch = new JTextField("Den ensamma katten");
    JTextArea searchResult = new JTextArea("Search result");
    JButton toAppAdmin = new JButton("to Admin App");
    JButton bSearch = new JButton("Search");

    AppCustomer() {
        super("Customer App");
        setSize(500, 500);
        setLocation(500, 20);
        panel.setLayout(null);
        titleSearch.setBounds(10, 80, 250, 20);
        searchResult.setBounds(10, 120, 480, 100);
        welcome.setBounds(10, 50, 500, 20);
        toAppAdmin.setBounds(10, 10, 120, 20);
        bSearch.setBounds(390, 450, 100, 20);
        panel.add(welcome);
        panel.add(titleSearch);
        panel.add(searchResult);
        panel.add(toAppAdmin);
        panel.add(bSearch);
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
            String q1 = "select titel, författare, sidor, klassifikation, lånadAv from inl1Base.böcker where titel = ? ;";
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
                            + "\n" + "Utlånad till: " + rs.getInt(5)
                            + " , Om '0' Ledig för utlåning annars upptagen.");
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
    }


}
