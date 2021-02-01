package sk.kosickaacademic.simon.examples.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Database {
    private static String url="jdbc:mysql://itsovy.sk:3306/world_x";
    private static String username="mysqluser";
    private static String password="Kosice2021!";

    public static String query = "SELECT city.Name, JSON_EXTRACT(Info,'$.Population') AS Population "
            +"FROM city " +"INNER JOIN country ON country.Code = city.CountryCode "
            +"WHERE country.Name LIKE ? ORDER BY Population DESC";

    public static ArrayList<City> getCities(String country){
        ArrayList<City> list = new ArrayList<>();
        try{
            Connection con = DriverManager.getConnection(url, username, password);
            if(con!=null){
                System.out.println("Connected.");
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, country);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    String name = rs.getString("Name");
                    int population = rs.getInt("Population");
                    City city = new City(name, population);
                    list.add(city);
                }
                con.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public static void printCities(ArrayList<City> list){
        for(City temp : list)
            System.out.println(temp.name +" (" +temp.population +")");
    }

    public static void main(String[] args) {
        ArrayList<City> list = getCities("Nigeria");
        printCities(list);
    }
}
