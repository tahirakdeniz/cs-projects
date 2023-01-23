import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.Reader;
import java.util.LinkedList;

/* Class to read json files. */

public class ListJsonReader {

    // Fields:
    private final LinkedList<String> CHANCE_LIST = new LinkedList<>();
    private final LinkedList<String> COMMUNITY_CHEST_LIST = new LinkedList<>();
    // ---

    // Constructor:
    public ListJsonReader(String fileName){
        JSONParser processor = new JSONParser();

        try (Reader file = new FileReader(fileName)){
            JSONObject jsonfile = (JSONObject) processor.parse(file);

            // Read Chance List
            JSONArray chanceList = (JSONArray) jsonfile.get("chanceList");
            for(Object i:chanceList)
				CHANCE_LIST.add((String)((JSONObject)i).get("item"));
            // ---

            // Read Community Chest List
            JSONArray communityChestList = (JSONArray) jsonfile.get("communityChestList");
            for(Object i:communityChestList)
                COMMUNITY_CHEST_LIST.add((String)((JSONObject)i).get("item"));
            // ---

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LinkedList<String> getCHANCE_LIST() {
        return CHANCE_LIST;
    }

    public LinkedList<String> getCOMMUNITY_CHEST_LIST() {
        return COMMUNITY_CHEST_LIST;
    }
}

