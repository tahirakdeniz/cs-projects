import java.io.*;
import java.util.*;

public class Kingdom {

    private final List<List<Integer>> directedAdjList = new ArrayList<>();
    private final List<List<Integer>> undirectedAdjList = new ArrayList<>();

    private int vertexNumber = 0;

    private boolean[] marked;

    // TODO: You should add appropriate instance variables.
    public void initializeKingdom(String filename) {
        try {
            // Open the input file
            File source = new File(filename);
            Scanner scanner = new Scanner(source);
            vertexNumber = scanner.nextLine().split(" ").length; // The number of vertices in the graph

            // Initialize the adjacency lists for the directed and undirected graphs
            for (int i = 0; i < vertexNumber; i++) {
                directedAdjList.add(new ArrayList<>());
                undirectedAdjList.add(new ArrayList<>());
            }

            scanner = new Scanner(source); // update scanner to read from scratch.

            // Read the adjacency matrix and fill the adjacency lists
            for (int i = 0; i < vertexNumber; i++) {
                for (int j = 0; j < vertexNumber; j++) {
                    int edgeWeight = scanner.nextInt();
                    if (edgeWeight > 0) {
                        // If there is an edge from vertex i to vertex j
                        directedAdjList.get(i).add(j); // Add j to the i-th list in the directedAdjList

                        // TODO can be error here, multiple items can be added to the same list.
                        undirectedAdjList.get(i).add(j); // Add j to the i-th list in the undirectedAdjList
                        undirectedAdjList.get(j).add(i); // Add i to the j-th list in the undirectedAdjList
                    }
                }
            }

            // Close the scanner
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }

    public List<Colony> getColonies() {
        List<Colony> colonies = new ArrayList<>();

        marked = new boolean[vertexNumber];
        for (int v = 0; v < vertexNumber; v++)
        {
            if (!marked[v])
            {
                Colony colony = new Colony();
                dfs(colony, v);
                colonies.add(colony);
            }
        }
        return colonies;
    }

    private void dfs(Colony colony, int vertex){
        marked[vertex] = true;
        for (int w : undirectedAdjList.get(vertex))
            if (!marked[w]){
                dfs(colony, w);
            }

        ArrayList<Integer> newDirectedList = new ArrayList<>();
        for (int w : directedAdjList.get(vertex)) {
            newDirectedList.add(w + 1); // every vertex number made +1 because in colony vertexes are 1 indexed.
        }

        colony.roadNetwork.put(vertex + 1, newDirectedList);
        colony.cities.add(vertex + 1); // TODO cities are not ordered check them.
    }

    public void printColonies(List<Colony> discoveredColonies) {
        System.out.println("Discovered colonies are: ");
        for (int i = 0, discoveredColoniesSize = discoveredColonies.size(); i < discoveredColoniesSize; i++) {
            Colony colony = discoveredColonies.get(i);
            Collections.sort(colony.cities);
            System.out.println("Colony " + (i+1) + ": " + Arrays.toString(colony.cities.toArray()));
        }
    }
}
