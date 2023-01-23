import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * a class to keep jewelTypes in a map.
 */
class JewelTypeMap {

    private Map<String, JewelType> hashMap = new HashMap<>();

    JewelTypeMap(JewelType... jewelTypes) {
        for (JewelType j : jewelTypes)
            hashMap.put(j.getSymbol(), j);
    }

    public JewelType getJewelType(String symbol) {
        return hashMap.get(symbol);
    }

    public List<JewelType> getJewelType(String... symbols) {

        List<JewelType> jewelTypeList = new ArrayList<>();

        for (String symbol:
                symbols) {
            jewelTypeList.add(getJewelType(symbol));
        }

        return jewelTypeList;
    }
}