import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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


    public static void main(String[] args) {
        System.out.println("Welcome to Address Book Program!");
        
    }
}
