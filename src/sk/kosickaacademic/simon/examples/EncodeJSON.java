package sk.kosickaacademic.simon.examples;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.FileReader;

public class EncodeJSON {
    public static void main(String[] args) {
        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();
        obj.put("name", "Jozin");
        obj.put("age", 32);
        arr.add("Football");
        arr.add("Ice Hockey");
        arr.add("Tennis");
        obj.put("sport", arr);
        System.out.println(obj);

        readWeatherConditions();
        readPlaces();
    }

    public static void readWeatherConditions(){
        JSONParser parser = new JSONParser();
        try{
            BufferedReader read = new BufferedReader(new FileReader("resources/weather.json"));
            JSONObject obj = (JSONObject) parser.parse(read);
            JSONObject main = (JSONObject) obj.get("main");
            Double temp = (Double) main.get("temp");
            Long pressure = (Long) main.get("pressure");
            Long humidity = (Long) main.get("humidity");
            Long visibility = (Long) obj.get("visibility");
            System.out.println("Temperature(C): " +(temp-272.15) +", Pressure(hPa): " +pressure +", Humidity(%): " +humidity +", Visibility(m): " +visibility);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void readPlaces(){
        JSONParser parser = new JSONParser();
        try{
            BufferedReader read = new BufferedReader(new FileReader("resources/places.json"));
            JSONObject obj = (JSONObject) parser.parse(read);
            JSONObject data = (JSONObject) obj.get("data");
            for(int i=0; i<data.size(); i++){
                JSONObject index = (JSONObject) data.get(String.valueOf(i));
                String city = (String) index.get("city");
                String region = (String) index.get("region");
                String wikiDataId = (String) index.get("wikiDataId");
                Double latitude = (Double) index.get("latitude");
                Double longitude = (Double) index.get("longitude");
                System.out.println("City: " +city +", Region: " +region +", WikiDataID: " +wikiDataId +", Latitude: " +latitude +", Longitude: " +longitude);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
