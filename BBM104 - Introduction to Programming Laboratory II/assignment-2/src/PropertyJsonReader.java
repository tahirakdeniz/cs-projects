/*
* Class To Read Property Json File
* */

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;


public class PropertyJsonReader {

    // Fields:
    private final ArrayList<String[]> LAND_DATA_LIST = new ArrayList<>();
    private final ArrayList<String[]> RAILROAD_DATA_LIST = new ArrayList<>();
    private final ArrayList<String[]> COMPANY_DATA_LIST = new ArrayList<>();
    // ---

    // Constructor:
     public PropertyJsonReader(String fileName){
         JSONParser processor = new JSONParser();
         try (Reader file = new FileReader(fileName)){
             JSONObject jsonfile = (JSONObject) processor.parse(file);

             // Read Land
             JSONArray Land = (JSONArray) jsonfile.get("1");
             for(Object i:Land){
				 LAND_DATA_LIST.add(new String[]{
                         (String) ((JSONObject) i).get("id"),
                         (String) ((JSONObject) i).get("name"),
                         (String) ((JSONObject)i).get("cost"),
                 });
             }
             // ---

             // Read RailRoad
             JSONArray RailRoad = (JSONArray) jsonfile.get("2");
             for(Object i:RailRoad){
                 RAILROAD_DATA_LIST.add(new String[]{
                         (String) ((JSONObject) i).get("id"),
                         (String) ((JSONObject) i).get("name"),
                         (String) ((JSONObject)i).get("cost"),
                 });
             }
             // ---

             // Read Company
             JSONArray Company = (JSONArray) jsonfile.get("3");
             for(Object i:Company){
                 COMPANY_DATA_LIST.add(new String[]{
                         (String) ((JSONObject) i).get("id"),
                         (String) ((JSONObject) i).get("name"),
                         (String) ((JSONObject)i).get("cost"),
                 });
             }
             // ---
             
         } catch (Exception e){
             e.printStackTrace();
         }
     }
    // ---

    // Accessors:
     public ArrayList<String[]> getCOMPANY_DATA_LIST() {
         return COMPANY_DATA_LIST;
     }

    public ArrayList<String[]> getLAND_DATA_LIST() {
        return LAND_DATA_LIST;
    }

    public ArrayList<String[]> getRAILROAD_DATA_LIST() {
        return RAILROAD_DATA_LIST;
    }
    // ---
}