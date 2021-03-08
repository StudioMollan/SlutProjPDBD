import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GuiLogin extends JFrame {


    private JTextArea loginStatus;
    private JTextField userName;
    private JPasswordField passWord;
    private JButton userSubmit;
    private JPanel jp;
    private String userInput;
    private String passInput;
    private String status = "status default";

    public GuiLogin() {

        this.setTitle("SQL - Front - Login");
        this.setSize(400, 200);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jp = new JPanel(new GridLayout(2, 1));
        userName = new JTextField("REPLACE WITH USERNAME", 20);
        passWord = new JPasswordField("REPLACE WITH PASSWORD", 20);
        userSubmit = new JButton("PRESS TO SUBMIT");
        loginStatus = new JTextArea(status);
        jp.add(userName);
        jp.add(passWord);
        jp.add(userSubmit);
        jp.add(loginStatus);
        ActionListener al = e -> {
            userInput = userName.getText();
            passInput = passWord.getText();
            loginStatus.setText(status);
            Main.setUname(userInput);
            Main.setPword(passInput);
            System.out.println(passWord.getText());
            System.out.println(userName.getText());
        };
        userSubmit.addActionListener(al);
        userName.setBackground(Color.lightGray);

        this.add(jp);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    public String getUser() {
//        if (userInput.equals("-1")) {
//            return "-1";
//        }

        String n = userInput;
        userInput = "-1";
        return n;
    }
    public String getPass() {
//        if (passInput.equals("-1")) {
//            return "-1";
//        }

        String n = passInput;
        passInput = "-1";
        return n;
    }
    public void setStatus(String text){
        System.out.println(status);
        status =text;
        System.out.println(status);
    }
}
