package sk.kosickaacademic.simon.examples.database;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    private static String url="jdbc:mysql://itsovy.sk:3306/world_x";
    private static String username="mysqluser";
    private static String password="Kosice2021!";

    public static ArrayList<City> getCities(String country){
        ArrayList<City> list = new ArrayList<>();
        String query = "SELECT city.Name, JSON_EXTRACT(Info,'$.Population') AS Population "
                +"FROM city " +"INNER JOIN country ON country.Code = city.CountryCode "
                +"WHERE country.Name LIKE ? ORDER BY Population DESC";
        try{
            Connection con = getConnection();
            if(con!=null){
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

    public static Country getCountryInfo(String country){
        Country countryInfo = null;
        String query = "SELECT country.Name, country.Code, city.Name, JSON_UNQUOTE(JSON_EXTRACT(doc,'$.geography.Continent')) AS Continent, "
                +"JSON_EXTRACT(doc,'$.geography.SurfaceArea') AS Area FROM country "
                +"INNER JOIN countryinfo ON country.Code = countryinfo._id INNER JOIN city ON country.Capital = city.ID "
                +"WHERE country.Name LIKE ?";
        try{
            Connection con = getConnection();
            if(con!=null){
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, country);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    String code3 = rs.getString("country.Code");
                    String capital = rs.getString("city.Name");
                    String continent = rs.getString("Continent");
                    int area = rs.getInt("Area");
                    countryInfo = new Country(country, code3, capital, continent, area);
                }
                con.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return countryInfo;
    }

    public static ArrayList<CapitalCity> getCapitalCities(String continent){
        ArrayList<CapitalCity> list = new ArrayList<>();
        String query="SELECT city.Name, JSON_EXTRACT(doc, '$.geography.Continent') AS Continent, "
                +"JSON_EXTRACT(doc, '$.demographics.Population') AS Population FROM country "
                +"INNER JOIN countryinfo ON country.Code = countryinfo._id "
                +"WHERE Continent LIKE ?";
        try{
            Connection con = getConnection();
            if(con!=null){
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, continent);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    String name = rs.getString("");
                    String country = rs.getString("");
                    int population = rs.getInt("Population");
                    CapitalCity capital = new CapitalCity(name, country, population);
                    list.add(capital);
                }
            }
            con.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String getCountryCode(String country){
        if(country==null || country.equals("")) return null;
        String query = "SELECT Code FROM country WHERE Name LIKE ?";
        try{
            Connection con = getConnection();
            if(con!=null){
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, country);
                ResultSet rs = ps.executeQuery();
                if(rs.next()) return rs.getString("Code");
                con.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection(url, username, password);
        return con;
    }

    public static void printCities(ArrayList<City> list){
        System.out.println();
        System.out.println("Cities: ");
        for(City temp : list)
            System.out.println(temp.getName() +" (" +temp.getPopulation() +")");
    }

    public static void printCountryInfo(Country countryInfo){
        System.out.println("Country: " +countryInfo.getName() +" (" +countryInfo.getCode3() +")" +'\n'
                +"Capital City: " +countryInfo.getCapital() +'\n'
                +"Continent: " +countryInfo.getContinent() +'\n'
                +"Area: " +countryInfo.getArea());
    }

    public static void insertCity(City city){
        String country = city.getCountry();
        String code3 = getCountryCode(country);
        city.setCode3(code3);
        if(code3!=null){
            String query = "INSERT INTO city (Name, CountryCode, District, Info) "
                    +"VALUES(?, ?, ?, ?)";
            try{
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, city.getName());
                ps.setString(2, city.getCode3());
                ps.setString(3, city.getDistrict());
                ps.setString(4, "{\"Population\":" +city.getPopulation() +"}");
                int result = ps.executeUpdate();
                if(result==1) System.out.println("Successful insert.");
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void updatePopulation(String country, String city, int population){
        if(population<=0){ System.out.println("Wrong population number!"); return; }
        String query = "UPDATE city INNER JOIN country ON city.CountryCode = country.Code "
                +"SET Info = JSON_SET(Info, '$.Population', ?) "
                +"WHERE country.Name LIKE ? AND city.Name LIKE ?";
        try{
            Connection con = getConnection();
            if(con!=null){
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, population);
                ps.setString(2, country);
                ps.setString(3, city);
                int result = ps.executeUpdate();
                if(result==3) System.out.println("Changes updated.");
                con.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String country = "Nigeria";
        Country countryInfo = getCountryInfo(country);
        ArrayList<City> list = getCities(country);
        printCountryInfo(countryInfo);
        printCities(list);
        //insertCity(new City("Humenne", "Slovakia", "Prešov", 23000));
        //updatePopulation("Slovakia", "Humenne", 24200);
    }
}
