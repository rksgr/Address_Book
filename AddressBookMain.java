import java.util.*;

public class AddressBookMain {
    private Map<String, Map> address_book_system = new HashMap<>();

    /*
    Use Case 1: Create contacts in address book
    */
    public Map<Integer, String[]> createContacts(){
        Map<Integer, String[]> map_address_book= new HashMap<>();
        return map_address_book;
    }

    /*
    Use case 2: Add a new contact to address book
    */
    public Map<Integer, String[]> addContact(Map<Integer, String[]> map_address_book){
        String[] contact = enterContactDetails();
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

        // Store the new element in the Map.
        map_address_book.put(new_key,contact);
        return map_address_book;
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
    public void editExistingContact(Map<Integer, String[]> map_address_book){
        System.out.println("Enter the name of the person whose details you "
                + "want to be changed");
        Scanner sc = new Scanner(System.in);
        String search_pers = sc.next();
        int key = searchExistingContact(search_pers, map_address_book);
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
    public Integer searchExistingContact(String search_pers, Map<Integer, String[]> map_address_book){
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
    public void deleteExistingContact(Map<Integer, String[]> map_address_book){
        System.out.println("Enter the name of the person whose details you "
                + "want to delete");
        Scanner sc = new Scanner(System.in);
        String search_pers = sc.next();
        // Fetch the key of person in address book
        int key = searchExistingContact(search_pers, map_address_book);
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
    public Map<Integer, String[]> addMultiplePerson(Map<Integer, String[]> map_address_book){
        System.out.println("Enter the number of persons whose details you want "
                + "to add to the address book");
        Scanner sc = new Scanner(System.in);
        int no_of_person = sc.nextInt();
        // create address book
        Map<Integer, String[]> address_book = new HashMap<>();
        for (int i=1;i<=no_of_person;i++){
            // call addition method each time
            addContact(map_address_book);
        }
        return map_address_book;
    }

    /*
    Use case 6: Add multiple address book to system
    */
    public void addAddressBook() {
        System.out.println("Enter the number of address books you want to add to the address book system.");
        Scanner sc1 = new Scanner(System.in);
        int no_of_addr_books = sc1.nextInt();
        for (int i=0;i<no_of_addr_books;i++){
            System.out.println("Add the name of the new address book: ");
            Scanner sc = new Scanner(System.in);
            String addr_book_name = sc.next();

            // create a new address book and add multiple contacts
            Map<Integer, String[]> map_address_book = createContacts();
            addMultiplePerson(map_address_book);
            // Add the new address book into Address Book system using its name as key
            address_book_system.put(addr_book_name,map_address_book);
        }
        // Print the contents of all the address books
        Set<String> addr_set = address_book_system.keySet();
        System.out.println("address_book_system length: "+address_book_system.size());
        for (String a:addr_set){
            Map<Integer,String[]> my_addr_book = address_book_system.get(a);
            System.out.println("address_book length: "+ my_addr_book.size());
            Set<Integer> contct_set = my_addr_book.keySet();

            for (int b:contct_set){
                String[] str_contact = my_addr_book.get(b);

                for (String element:str_contact){
                    System.out.print(element + " ");
                }
            }
        }
    }

    /*
    Use Case 7: Ensure there is no duplicate entry of the same person in a particular address book
     */
    public void preventDuplicateEntry(Map<Integer, String[]> map_address_book){
        String [] contact = enterContactDetails();
        // search for the name in the existing address book
        if (searchExistingContact(contact[0], map_address_book) == -1){
            System.out.println("The name "+ contact[0] +" doesn't exist in the address book. " +
                    "So it is added to the address book.");
            createContacts();
        }else if (searchExistingContact(contact[0], map_address_book) != -1){
            System.out.println("The name "+ contact[0]+" is already present in the address book. So it cannot be added again!");
        }
    }
    /*
    Use Case 8: Search person in a city or state across multiple Address book
     */
    public void searchPersonAcrossAddressBooks(){
        System.out.println("Enter the name of the person whom you want to search: ");
        Scanner sc = new Scanner(System.in);
        String person_name = sc.next();

        System.out.println("Enter the name of the city in which you want to search for the person: ");
        Scanner sc1 = new Scanner(System.in);
        String city_name = sc.next();

        int  contct_key = -1;
        String addr_book_name = "";
        Set<String> addr_set = address_book_system.keySet();

        // Iterate through each address book
        for (String a:addr_set) {
            Map<Integer, String[]> my_addr_book = address_book_system.get(a);
            for (Integer i : my_addr_book.keySet()) {

                // Search for the given person in the given city
                if (my_addr_book.get(i)[3].equals(city_name)) {

                    // if city is found, search for person name
                    if (my_addr_book.get(i)[0].equals(person_name)) {
                        contct_key = i;
                        addr_book_name = a;
                        break;
                    }
                }
            }
        }
        if ((addr_book_name.equals("")) && (contct_key==-1)){
            System.out.println("The person named "+person_name+ "has not been found in "+ city_name+
                    " in any address book");
        }else {
            System.out.println("The person named " + person_name + " has been found in " + city_name + "" +
                    " in address book " + addr_book_name + " with key of contact: " + contct_key);
        }
    }
    public static void main(String[] args) {
        System.out.println("Welcome to Address Book Program!");
        AddressBookMain abm = new AddressBookMain();
        Map<Integer, String[]> map_address_book= abm.createContacts();
        // abm.addContact(map_address_book);
        //abm.addContact();
        //abm.editExistingContact();
        //abm.deleteExistingContact();
        abm.addAddressBook();
        //abm.preventDuplicateEntry();
        abm.searchPersonAcrossAddressBooks();
        //abm.viewPersonsByCityOrState();
        //abm.getNumOfPersons();
    }
}
