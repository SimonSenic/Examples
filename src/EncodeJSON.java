import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class EncodeJSON {
    public static void main(String[] args) {
        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();
        obj.put("name", "Jozin");
        obj.put("age", 32);
        //arr.add()
        obj.put("sport", "Football");
        System.out.println(obj);
    }
}
