import java.util.LinkedList;

/* Class to keep Cards in a List
* Methods to get next item, (first in first out principle)
* All methods and variables are static (Utility Class)
* */

public class CardCollection {

    // Fields:
    private final static LinkedList<String> CHANCE_LIST;
    private final static LinkedList<String> COMMUNITY_CHEST_LIST;
    private static int chanceIndex;
    private static int communityChestIndex;
    // ---

    // To initialize static variables:
    static {
        final String LIST_FILE_NAME = "list.json";
        ListJsonReader listJsonReader = new ListJsonReader(LIST_FILE_NAME);
        CHANCE_LIST = listJsonReader.getCHANCE_LIST();
        COMMUNITY_CHEST_LIST = listJsonReader.getCOMMUNITY_CHEST_LIST();
        chanceIndex = 0;
        communityChestIndex = 0;
    }
    // ---

    // Methods:
    public static String nextChanceCard() {
        String chanceCard = CHANCE_LIST.get(chanceIndex);
        chanceIndex = (chanceIndex+1)%CHANCE_LIST.size();
        return chanceCard;
    }

    public static String nextCommunityChestCard() {
        String communityChestCard = COMMUNITY_CHEST_LIST.get(communityChestIndex);
        communityChestIndex = (communityChestIndex+1)%COMMUNITY_CHEST_LIST.size();
        return communityChestCard;
    }
    // ---
}
