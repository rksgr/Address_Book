import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Scanner;

public class AddressBookMain {
    Map<Integer, String[]> map_address_book= new HashMap<>();
    /*
    Use Case 1: Create contacts in address book
    */
    public void createContacts(String []contact){
        // store the new entry with a key one higher than maximum pre-existing key
        Set<Integer> keys = map_address_book.keySet();
        int max_key = -1;

        // Get the highest key
        for (int key:keys){
            if (max_key<key){
                max_key = key;
            }
        }
        int new_key = max_key +1;

        // Store the element in the Map.
        map_address_book.put(new_key,contact);
    }

    /*
    Use case 2: Add a new contact to address book
    */
    public void addContact(){
        String[] contact = enterContactDetails();
        createContacts(contact);
    }
    // Get all the details of person from console
    public String[] enterContactDetails(){
        System.out.println("Enter the first name: ");
        Scanner sc1 = new Scanner(System.in);
        String first_name = sc1.next();

        System.out.println("Enter the last name: ");
        Scanner sc2 = new Scanner(System.in);
        String last_name = sc2.next();

        System.out.println("Enter the address: ");
        Scanner sc3 = new Scanner(System.in);
        String address = sc3.next();

        System.out.println("Enter the city name: ");
        Scanner sc4 = new Scanner(System.in);
        String city = sc4.next();

        System.out.println("Enter the state's name: ");
        Scanner sc5 = new Scanner(System.in);
        String state = sc5.next();

        System.out.println("Enter the zip: ");
        Scanner sc6 = new Scanner(System.in);
        String zip = sc6.next();

        System.out.println("Enter the phone number: ");
        Scanner sc7 = new Scanner(System.in);
        String phone_num = sc7.next();

        System.out.println("Enter the email ID: ");
        Scanner sc8 = new Scanner(System.in);
        String email = sc8.next();

        String[] contact = new String[]{first_name,last_name,address,city,state,zip,phone_num,email};
        return contact;
    }
    public static void main(String[] args) {
        System.out.println("Welcome to Address Book Program!");
        AddressBookMain abm = new AddressBookMain();
        abm.addContact();
    }
}
