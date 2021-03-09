import javax.swing.*;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AppAdmin extends JFrame {

    public static void main(String[] args) {
        AppAdmin frameTabel = new AppAdmin();
    }

    JLabel welcome = new JLabel("Welcome to a Admin App, search employees by name below.");
    JPanel panel = new JPanel();
    JTextField search = new JTextField("Search");
    JTextArea result = new JTextArea("Result");
    JButton toAppCustomer = new JButton("to Customer App");
    JButton bCommit = new JButton("Commit");
    JButton bSearch = new JButton("Search");
    JButton bShowEmployees = new JButton("Show all employees");
    JLabel editInstructions1
            = new JLabel("Input new values in fields below- => Press 'Commit'");
    JTextField fieldId = new JTextField("ID");
    JTextField field = new JTextField("1-7");
    JTextField newValue = new JTextField("new value");
    JLabel editInstructions2
            = new JLabel("1=Namn, 2=Adress, 3=tel1, 4=tel2, 5=tel3, 6=mån.lön, 7=sem.dagar");

    AppAdmin() {
        super("Admin App");
        setSize(500, 500);
        setLocation(500, 20);
        panel.setLayout(null);
        search.setBounds(10, 80, 250, 20);
        bSearch.setBounds(270, 80, 100, 20);
        result.setBounds(10, 120, 480, 200);
        welcome.setBounds(10, 50, 500, 20);
        toAppCustomer.setBounds(10, 10, 120, 20);
        bCommit.setBounds(390, 450, 100, 20);
        bShowEmployees.setBounds(10, 450, 160, 20);
        editInstructions1.setBounds(10, 330, 500, 20);
        editInstructions2.setBounds(10, 350, 500, 20);
        fieldId.setBounds(10, 370, 30, 20);
        field.setBounds(50, 370, 60, 20);
        newValue.setBounds(120, 370, 300, 20);
        panel.add(welcome);
        panel.add(search);
        panel.add(result);
        panel.add(toAppCustomer);
        panel.add(bCommit);
        panel.add(bShowEmployees);
        panel.add(editInstructions1);
        panel.add(editInstructions2);
        panel.add(field);
        panel.add(fieldId);
        panel.add(newValue);
        panel.add(bSearch);
        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        actionlogin();
    }

    public void actionlogin() {
        toAppCustomer.addActionListener(ae -> {
            AppCustomer regFace = new AppCustomer();
            regFace.setVisible(true);
            dispose();
        });
        bSearch.addActionListener(ae -> {
            Connection con;
            String q1 = "select anstId, namn, Adress, tel1, tel2, tel3, månadslön, semesterdagar from inl1Base.anställda where namn REGEXP  ? ;";
            String input = "";
            if (checkInput(search.getText()) == false) {
                input = search.getText();
            }

            String ans = "";
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost/inl1Base", Login.uName, String.valueOf(Login.pWord));
                PreparedStatement pState = con.prepareStatement(q1);
                pState.setString(1, input);
                ResultSet rs = pState.executeQuery();
                while (rs.next())

                    ans = "\n" + "Anställnings-ID: " + (rs.getInt(1)
                            + "\n" + "Namn:                 " + rs.getString(2)
                            + "\n" + "Adress:               " + rs.getString(3)
                            + "\n" + "Telefon #1           " + rs.getInt(4)
                            + "\n" + "Telefon #2           " + rs.getInt(5)
                            + "\n" + "Telefon #3           " + rs.getInt(6)
                            + "\n" + "Månadslön           " + rs.getInt(7)
                            + "\n" + "Semesterdagar     " + rs.getInt(8));
                con.close();
                if (ans.equals("")) {
                    result.setText("No matches found");
                } else {
                    result.setText(ans);
                }

            } catch (SQLException throwables) {
                result.setText("invalid input. Please try again.");
                throwables.printStackTrace();
            }


        });
        bShowEmployees.addActionListener(ae -> {
            Connection con;
            String q1 = "select * from anställda;";
            String ans = "ID     | NAMN       | ADRESS                 | TEL 1 | TEL 2 | TEL 3 | MÅNADSLÖN | SEMESTERDAGAR";
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost/inl1Base", Login.uName, String.valueOf(Login.pWord));
                Statement state = con.prepareStatement(q1);
                ResultSet rs = state.executeQuery(q1);
                while (rs.next())
                    ans = ans + "\n " + (rs.getInt(1)
                            + "       |" + rs.getString(2)
                            + "   |" + rs.getString(3)
                            + " |" + rs.getInt(4)
                            + " |" + rs.getInt(5)
                            + " |" + rs.getInt(6)
                            + " |" + rs.getInt(7)
                            + " |" + rs.getInt(8));
                con.close();
                result.setText(ans);


            } catch (SQLException throwables) {

                throwables.printStackTrace();
            }


        });
        bCommit.addActionListener(ae -> {
            Connection con;

            String fieldString;
            int fieldInput = Integer.parseInt(field.getText());
            if (fieldInput == 1) {
                fieldString = "namn";
            } else if (fieldInput == 2) {
                fieldString = "Adress";
            } else if (fieldInput == 3) {
                fieldString = "tel1";
            } else if (fieldInput == 4) {
                fieldString = "tel2";
            } else if (fieldInput == 5) {
                fieldString = "tel3";
            } else if (fieldInput == 6) {
                fieldString = "månadslön";
            } else if (fieldInput == 7) {
                fieldString = "semesterdagar";
            } else {
                fieldString = "";
            }
            String newValueInput = newValue.getText();
            String q1 = "UPDATE anställda SET " + fieldString + " = ? WHERE anstID = ? ;";
            String inputId = fieldId.getText();
            String ans = fieldString + " succesfully changed to: " + newValueInput;
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost/inl1Base", Login.uName, String.valueOf(Login.pWord));
                PreparedStatement pState = con.prepareStatement(q1);

                pState.setString(1, newValueInput);
                pState.setString(2, inputId);
                pState.execute();

                con.close();
                result.setText(ans);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    public static boolean checkInput(String input) {
        Pattern p = Pattern.compile("[A-Z]([a-z]{1,25})");

        Matcher matcher = p.matcher(input);

        while (matcher.find()) {
            if (matcher.group().length() != 0) {
                return true;
            }

        }
        return false;

    }
}