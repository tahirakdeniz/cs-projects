import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.*;

public class TravelMap {

    // Maps a single ID to a single Location.
    public Map<Integer, Location> locationMap = new HashMap<>();

    // List of locations, read in the given order
    public List<Location> locations = new ArrayList<>();

    // List of trails, read in the given order
    public List<Trail> trails = new ArrayList<>();

    // TODO: You are free to add more variables if necessary.

    public void initializeMap(String filename) {
        try {
            File inputFile = new File(filename);

            // Create a DocumentBuilder
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            // Parse the input file
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // Parse the locations
            NodeList locationList = doc.getElementsByTagName("Location");
            for (int i = 0; i < locationList.getLength(); i++) {
                Node locationNode = locationList.item(i);
                if (locationNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element locationElement = (Element) locationNode;
                    int id = Integer.parseInt(locationElement.getElementsByTagName("Id").item(0).getTextContent());
                    String name = locationElement.getElementsByTagName("Name").item(0).getTextContent();
                    Location location = new Location(name, id);
                    locations.add(location);
                    locationMap.put(id, location);
                }
            }

            // Parse the trails
            NodeList trailList = doc.getElementsByTagName("Trail");
            for (int i = 0; i < trailList.getLength(); i++) {
                Node trailNode = trailList.item(i);
                if (trailNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element trailElement = (Element) trailNode;
                    int sourceId = Integer.parseInt(trailElement.getElementsByTagName("Source").item(0).getTextContent());
                    int destId = Integer.parseInt(trailElement.getElementsByTagName("Destination").item(0).getTextContent());
                    int danger = Integer.parseInt(trailElement.getElementsByTagName("Danger").item(0).getTextContent());
                    Trail trail = new Trail(locationMap.get(sourceId), locationMap.get(destId), danger);
                    trails.add(trail);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Trail> getSafestTrails() {
        List<Trail> safestTrails = new ArrayList<>();

        // Sort the trails by danger level
        trails.sort(Comparator.comparingInt(t -> t.danger));

        int numLocations = locations.size();

        // Use disjoint-set to keep track of connected components
        int[] parent = new int[numLocations];
        for (int i = 0; i < numLocations; i++) {
            parent[i] = i;
        }

        int numEdgesAdded = 0;
        for (Trail trail : trails) {
            int sourceParent = find(parent, trail.source.id);
            int destParent = find(parent, trail.destination.id);
            if (sourceParent != destParent) {
                safestTrails.add(trail);
                numEdgesAdded++;
                if (numEdgesAdded == numLocations - 1) {
                    break;
                }
                parent[sourceParent] = destParent;
            }
        }

        return safestTrails;
    }

    private int find(int[] parent, int node) {
        if (parent[node] == node) {
            return node;
        }
        parent[node] = find(parent, parent[node]);
        return parent[node];
    }

    public void printSafestTrails(List<Trail> safestTrails) {
        System.out.println("The Path of the Warrior");
        System.out.println("#######################");
        System.out.println("Safest trails are:");

        int totalDanger = 0;
        for (Trail trail : safestTrails) {
            System.out.printf("The trail from %s to %s with danger %d%n",
                    trail.source.name, trail.destination.name, trail.danger);
            totalDanger += trail.danger;
        }

        System.out.printf("Total danger: %d%n", totalDanger);
    }
}
