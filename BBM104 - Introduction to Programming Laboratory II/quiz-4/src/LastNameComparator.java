import java.util.Comparator;

public class LastNameComparator implements Comparator<Contact> {

    @Override
    public int compare(Contact contact1, Contact contact2) {
        return contact1.getLastName().compareTo(contact2.getLastName());
    }

}
