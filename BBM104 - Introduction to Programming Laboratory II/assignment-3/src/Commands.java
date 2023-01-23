import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A class to read file and get commands.
 * */
public class Commands extends FileCreator{

    private String playerName;
    private Iterator<Index> indexIterator;

    protected Commands(String filePath) {
        super(filePath);

        List<Index> indexList = new ArrayList<>();

        while (getScanner().hasNextLine()) {

            String[] arr = getScanner().nextLine().split(" ");

            // If the line has two string, the line gives an Index:
            if (arr.length == 2) {
                Index index = new Index(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
                indexList.add(index);
            }

            // If the line is last line of file, the line gives name of player (playerName):
            else if (getScanner().hasNextLine()) {
                playerName = getScanner().nextLine().split(" ")[0];
            }
        }

        indexIterator = indexList.iterator();
    }

    /**
     * @return an Iterator of index in command file.
     */
    public Iterator<Index> getIndexIterator() {
        return indexIterator;
    }

    public String getPlayerName() {
        return playerName;
    }

}
