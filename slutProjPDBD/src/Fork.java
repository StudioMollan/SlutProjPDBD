import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Fork extends JFrame {

    public static void main(String[] args) {
        Fork frameTabel = new Fork();
    }

    JLabel welcome = new JLabel("Please select which App to use below");
    JPanel panel = new JPanel();
    JButton bCustomer = new JButton("Customer");
    JButton bAdmin = new JButton("Admin");

    Fork() {
        super("App select");
        setSize(500, 100);
        setLocation(500, 20);
        panel.setLayout(null);
        bCustomer.setBounds(90, 40, 150, 20);
        bAdmin.setBounds(260, 40, 150, 20);
        welcome.setBounds(120, 10, 500, 20);
        panel.add(bCustomer);
        panel.add(bAdmin);
        panel.add(welcome);
        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        actionlogin();
    }

    public void actionlogin() {
        bCustomer.addActionListener(ae -> {
            AppCustomer regFace = new AppCustomer();
            regFace.setVisible(true);
            dispose();
        });
        bAdmin.addActionListener(ae -> {
            AppAdmin regFace = new AppAdmin();
            regFace.setVisible(true);
            dispose();
        });
    }


}
