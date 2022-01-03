import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Scanner;

public class AddressBookMain {
    private  Map<Integer, String[]> map_address_book= new HashMap<>();
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

    /*
    Use case 3: Edit existing contact person using their name
    */
    public void editExistingContact(){
        System.out.println("Enter the name of the person whose details you "
                + "want to be changed");
        Scanner sc = new Scanner(System.in);
        String search_pers = sc.next();
        int key = searchExistingContact(search_pers);
        System.out.println("Found the name at key "+ key+ " Kindly enter new details ");
        // Ask for the new details
        String[] contact = enterContactDetails();

        // Modify the values in the Address Book
        map_address_book.replace(key,contact);

        // Print all the contacts inside the address book
        String [] cntct1 = map_address_book.get(key);
        for (String s:cntct1){
            System.out.println(s);
        }

    }
    // Returns the key of contact details of a person in address book using his/her name
    public Integer searchExistingContact(String search_pers){
        int key = -1;
        for (Integer i: map_address_book.keySet()){
            // find key of contact in which the given name is there
            if (map_address_book.get(i)[0].equals(search_pers)){
                // if name is found
                key = i;
                break;
            }
        }
        return key;
    }

    /*
    Use case 4: Delete an existing person contact using her/his name in address book
    */
    public void deleteExistingContact(){
        System.out.println("Enter the name of the person whose details you "
                + "want to delete");
        Scanner sc = new Scanner(System.in);
        String search_pers = sc.next();
        // Fetch the key of person in address book
        int key = searchExistingContact(search_pers);
        // delete the details of the person
        if (map_address_book.remove(key,map_address_book.get(key))){
            System.out.println("Deleted successfully.");
        } else{
            System.out.println("Given contact not deleted.");
        }
    }

    /*
    Use case 5 :Add multiple person to address book
    */
    public void addMultiplePerson(){
        System.out.println("Enter the number of persons whose details you want "
                + "to add to the address book");
        Scanner sc = new Scanner(System.in);
        int no_of_person = sc.nextInt();
        for (int i=1;i<=no_of_person;i++){
            // call addition method each time
            addContact();
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Address Book Program!");
        AddressBookMain abm = new AddressBookMain();
        abm.addContact();
        //abm.editExistingContact();
        abm.deleteExistingContact();
    }
}
