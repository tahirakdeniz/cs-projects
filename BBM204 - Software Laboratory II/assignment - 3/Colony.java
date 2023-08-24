import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Colony {
    // List of cities in the current colony
    public List<Integer> cities = new ArrayList<>();

    // roadNetwork is a map of lists which represents the network of the colony in the adjacency list format
    // E.g. roadNetwork[x] is a list of cities to which the city x has a road to.
    public HashMap<Integer, List<Integer>> roadNetwork = new HashMap<>();
}
