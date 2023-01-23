import java.util.*;

public class Main {
    public static void main(String[] args) {
        FileIO.setReadFilePath(args[0]);

        // ArrayList
        ArrayList<Contact> contactArrayList = (ArrayList<Contact>) FileIO.readFileTo(new ArrayList<>());
        FileIO.writeOutput(contactArrayList, "contactsArrayList.txt");

        // Sort:
        Collections.sort(contactArrayList, new LastNameComparator());
        FileIO.writeOutput(contactArrayList, "contactsArrayListOrderByLastName.txt");
        // =

        // ===

        // HashSet
        HashSet<Contact> contactHashSet = (HashSet<Contact>) FileIO.readFileTo(new HashSet<>());
        FileIO.writeOutput(contactHashSet, "contactsHashSet.txt");
        // ===

        // TreeSet
        TreeSet<Contact> contactTreeSet = (TreeSet<Contact>) FileIO.readFileTo(new TreeSet<>()) ;
        FileIO.writeOutput(contactTreeSet, "contactsTreeSet.txt");
        // ===

        // TreeSetOrderByLastName
        TreeSet<Contact> contactTreeSet1 = (TreeSet<Contact>) FileIO.readFileTo(new TreeSet<>(new LastNameComparator()));
        FileIO.writeOutput(contactTreeSet1, "contactsTreeSetOrderByLastName.txt");
        // ===

        //HashMap
        HashMap<String, Contact> hashMap = FileIO.readFileTo(new HashMap<>());
        FileIO.writeOutput(hashMap.values(), "contactsHashMap.txt");
        // ===

        //

    }
}
