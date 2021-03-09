import javax.swing.*;
import java.sql.*;

public class Login extends JFrame {
    public static String uName;
    public static char[] pWord;

    public static void main(String[] args) {
        Login login = new Login();
    }

    JLabel welcome = new JLabel("Please enter username and password below.");
    JButton blogin = new JButton("Login");
    JPanel panel = new JPanel();
    JTextField inputUser = new JTextField("Username", 15);
    JPasswordField inputPass = new JPasswordField("**********", 15);
    JLabel info1 = new JLabel("ADMIN:           Username = testAdmin | Password = 456");
    JLabel info2 = new JLabel("CUSTOMER:     Username = testUser    | Password = 123");
    Login() {
        super("Login Autentification");
        setSize(500, 150);
        setLocation(500, 20);
        panel.setLayout(null);
        inputUser.setBounds(50, 40, 150, 20);
        inputPass.setBounds(210, 40, 150, 20);
        blogin.setBounds(370, 40, 80, 20);
        welcome.setBounds(120, 10, 500, 20);
        info1.setBounds(50, 90, 500, 20);
        info2.setBounds(50, 70, 500, 20);
        panel.add(blogin);
        panel.add(inputUser);
        panel.add(inputPass);
        panel.add(welcome);
        panel.add(info1);
        panel.add(info2);
        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        actionlogin();
    }

    public void actionlogin() {
        blogin.addActionListener(ae -> {
            uName = inputUser.getText();
            pWord = inputPass.getPassword();
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/inl1Base", uName, String.valueOf(pWord));
                Fork regFace = new Fork();
                regFace.setVisible(true);
                dispose();
                con.close();
            } catch (SQLException throwables) {
                JOptionPane.showMessageDialog(null, "Wrong Password / Username or no access to DataBase");

                inputUser.setText("");
                inputPass.setText("");
                inputUser.requestFocus();
                throwables.printStackTrace();
            }
        });
    }
}