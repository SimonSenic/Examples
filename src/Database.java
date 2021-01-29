import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Database {
    private static String url="jdbc:mysql://itsovy.sk:3306/world_x";
    private static String username="mysqluser";
    private static String password="Kosice2021!";

    public static String query = "SELECT city.Name, CountryCode " +"FROM city " +"INNER JOIN country ON country.Code = city.CountryCode "
            +"WHERE country.Name LIKE ?";

    public static void printCities(String country){
        try{
            Connection con = DriverManager.getConnection(url, username, password);
            if(con!=null){
                System.out.println("Connected.");
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, country);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    String city = rs.getString("Name");
                    String code = rs.getString("CountryCode");
                    System.out.println(city +" " +code);
                }
                con.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        printCities("Nigeria");
    }
}
