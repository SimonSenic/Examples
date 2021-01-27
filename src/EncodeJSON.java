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
        //arr.add()
        obj.put("sport", "Football");
        System.out.println(obj);

        readPlaces();
    }

    public static void readPlaces(){
        JSONParser parser = new JSONParser();
        try{
            BufferedReader read = new BufferedReader(new FileReader("resources/places.json"));
            JSONObject obj = (JSONObject)parser.parse(read);
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
