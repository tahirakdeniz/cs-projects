public class Contact implements Comparable<Contact> {

    // Fields:
    private String socialSecurityNumber;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    // ===

    // Constructor:
    Contact(String[] arr) {
        phoneNumber = arr[0];
        firstName = arr[1];
        lastName = arr[2];
        socialSecurityNumber = arr[3];
    }
    // ===

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public int compareTo(Contact contact) {
        return phoneNumber.compareTo(contact.phoneNumber);
    }

    @Override
    public String toString() {
        return phoneNumber + ' '
                + firstName + ' '
                + lastName + ' '
                + socialSecurityNumber + '\n';
    }
}
