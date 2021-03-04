import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class WhiteBlackList {

    public static void main(String[] args) {


        Gui gui = new Gui();
        String ans = "";
        String query = "select * from Persons where LastName = ? ;";
        String input = "-1";

        while (!input.equals("q")) {

            if (input.equals("-1")) {
                input = gui.getVal();
                if (checkInput(input)) {

                    try {
                        System.out.println("Test");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/inl1Base", "root", "janne");
                        Statement stmt = con.createStatement();
                        // ResultSet rs = stmt.executeQuery("select * from Persons where LastName = " + input + ";");// +person);

                        PreparedStatement p = con.prepareStatement(query);
                        p.setString(1, input);

                        ResultSet rs = p.executeQuery();
                        while (rs.next())
                            ans = ans + "\n" + (rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5));

                        con.close();

                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                input = "-1";
            }
            gui.set(ans);
        }
    }

    public static boolean checkInput(String input){
        Pattern p = Pattern.compile("[A-Z]([a-z]{1,25})");

        Matcher matcher = p.matcher(input);

        while (matcher.find()){
            if(matcher.group().length() != 0){
                return true;
            }

        }
        return false;

    }
}
