import java.util.*;

public class TrapLocator {

    public List<Colony> colonies;

    private final List<HashMap<Integer, Boolean>> marked = new ArrayList<>();
    private final List<HashMap<Integer, Boolean>> onFunc = new ArrayList<>();
    private final List<HashMap<Integer, Integer>> edgeTo = new ArrayList<>();

    private final boolean[] hasCycle;

    private List<Integer> cycle = new ArrayList<>();
    // for colonies


    public TrapLocator(List<Colony> colonies) {
        this.colonies = colonies;
        int size = colonies.size();
        hasCycle = new boolean[size];

        for (int i = 0; i < size; i++) {
            marked.add(new HashMap<>());
        }

        for (int i = 0; i < size; i++) {
            onFunc.add(new HashMap<>());
        }

        for (int i = 0; i < size; i++) {
            edgeTo.add(new HashMap<>());
        }
    }

    public List<List<Integer>> revealTraps() {

        List<List<Integer>> traps = new ArrayList<>();

        for (int colonyIndex = 0; colonyIndex < colonies.size(); colonyIndex++) {
            Colony colony = colonies.get(colonyIndex);
            for (int vertex: colony.cities) {
                HashMap<Integer, Boolean> cMarked = marked.get(colonyIndex);
                boolean isMarked = cMarked.get(vertex) != null && cMarked.get(vertex);
                if(!isMarked){
                    dfs(colonyIndex, vertex);
                }
            }
            traps.add(cycle);
            cycle = new ArrayList<>();
        }

        // Identify the time traps and save them into the traps variable and then return it.
        // TODO: Your code here

        return traps;
    }

    private void dfs(int colonyIndex, int vertex){
        HashMap<Integer, Boolean> cMarked = marked.get(colonyIndex);
        HashMap<Integer, Boolean> cOnFunc = onFunc.get(colonyIndex);
        HashMap<Integer, Integer> cEdgeTo = edgeTo.get(colonyIndex);

        cMarked.put(vertex, true);
        cOnFunc.put(vertex, true);

        List<Integer> adjListOfV = colonies.get(colonyIndex).roadNetwork.get(vertex);

        for (int adj: adjListOfV) {
            if(hasCycle[colonyIndex])
                return;

            boolean isMarked = cMarked.get(adj) != null && cMarked.get(adj);
            if(!isMarked){
                cEdgeTo.put(adj, vertex);
                dfs(colonyIndex, adj);
            }

            else if(cOnFunc.get(adj) != null && cOnFunc.get(adj)){ // if marked and onFunc cycle detected

                for (int x = vertex; x != adj; x = cEdgeTo.get(x)){
                       cycle.add(x);
                } // add vertex to stack.
                cycle.add(adj);
                Collections.sort(cycle);
            }
        }

        cOnFunc.put(vertex, false);
    }

    public void printTraps(List<List<Integer>> traps){
        System.out.println("Danger exploration conclusions:");

        for (int i = 0, trapsSize = traps.size(); i < trapsSize; i++) {
            List<Integer> arr = traps.get(i);
            if (arr.isEmpty())
                System.out.println("Colony " + (i+1) + ": Safe");
            else {
                Collections.sort(arr);
                System.out.println("Colony " + (i+1) + ": Dangerous. Cities on the dangerous path: " + Arrays.toString(arr.toArray()));
            }
        }
    }

}
