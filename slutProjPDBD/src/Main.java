import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Gui gui = new Gui();
        String ans ="";

        String input = "-1";

        while(!input.equals("q")) {

            if (input.equals("-1")) {
                input = gui.getVal();


                try {

                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/inl1Base", "root", "janne");
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("select * from Persons where PersonID = " + input + ";");// +person);

                    while (rs.next())
                        ans =ans +"\n"+ (rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + " " + rs.getString(4) +" "+rs.getString(5));

                    con.close();

                } catch (Exception e) {
                    System.out.println(e);
                }
                input = "-1";
            }
            gui.set(ans);
        }
    }

}
