import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static String uName;
    private static String pWord;

//    private static String username = "testUser";
//    private static String password ="$A$005$i:H3'~;=^xs02QXa6Ng/7ouNrBJfggFKTCdL4zYhPaxOXMEMLtubz9";

    public static void main(String[] args) {

        Login frameTabel = new Login();

//        GuiLogin guiLogin = new GuiLogin();
//        uName = guiLogin.getUser();
//        pWord = guiLogin.getPass();
        System.out.println("from main " + uName);
        System.out.println("from main " + pWord);
//        guiLogin.setStatus("status from main");

        String ans = "";
        String query = "select * from Persons where LastName = ? ;";
        String input = "-1";
        while (!input.equals("q")) {

            if (input.equals("-1")) {
//                input = gui.getVal();
//                uName = guiLogin.getUser();
//                pWord = guiLogin.getPass();
                if (checkInput(input)) {

                    try {
                        System.out.println("Test inne i try");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/inl1Base", "root", "janne");
                        Statement stmt = con.createStatement();
                        // ResultSet rs = stmt.executeQuery("select * from Persons where LastName = " + input + ";");// +person);

                        PreparedStatement p = con.prepareStatement(query);
                        p.setString(1, input);

                        con.close();

                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                input = "-1";
            }
//            gui.set(ans);
        }
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

    public static void setPword(String s) {
        pWord = s;

    }

    public static void setUname(String s) {
        uName = s;

    }

}

