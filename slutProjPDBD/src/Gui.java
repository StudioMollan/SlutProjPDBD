import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Gui extends JFrame {
    private JTextField tf;
    private JTextArea ta;
    private JPanel jp;
    private String name;

    public Gui(){


        tf = new JTextField("h",20);
        name = "-1";
        this.setTitle("SQL - Front");
        this.setSize(800, 800);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jp = new JPanel(new GridLayout(2,1));

        ActionListener al = e->{ this.name = tf.getText();
            //   System.out.println("Name set to " + name);
        };
        tf.addActionListener(al);
        tf.setBackground(Color.lightGray);

        ta = new JTextArea();
        jp.add(tf);
        jp.add(ta);

        this.add(jp);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    public String getVal() {
        if (this.name.equals("-1")){ return "-1"; }

        String n = name;
        name = "-1";
        //   System.out.println("Returning" +n);
        return n;
    }
    public void set(String text){
        this.ta.setText("ans: \n"+ text);

    }

}
