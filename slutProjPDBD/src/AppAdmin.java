import javax.swing.*;


public class AppAdmin extends JFrame {

    public static void main(String[] args) {
        AppAdmin frameTabel = new AppAdmin();
    }

    JLabel welcome = new JLabel("Welcome to a Admin App, enter searched information below.");
    JPanel panel = new JPanel();
    JTextField search = new JTextField("Search");
    JTextArea result = new JTextArea("Result");
    JButton toAppCustomer = new JButton("to Customer App");
    JButton bSearch = new JButton("Search");

    AppAdmin() {
        super("Admin App");
        setSize(500, 500);
        setLocation(500, 20);
        panel.setLayout(null);
        search.setBounds(10, 80, 250, 20);
        result.setBounds(10, 120, 480, 60);
        welcome.setBounds(10, 50, 500, 20);
        toAppCustomer.setBounds(10, 10, 120, 20);
        bSearch.setBounds(390, 450, 100, 20);
        panel.add(welcome);
        panel.add(search);
        panel.add(result);
        panel.add(toAppCustomer);
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

    }
}