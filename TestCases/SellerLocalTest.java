import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Aashna, Jiwon, Aysu, Mridu
 * @version 1.0
 */
public class SellerLocalTest {

    public static void main(String[] args) throws IOException {
        Seller test = new Seller("eylulsaglam", "esaglam@purdue.edu", "pass");
        //Creating/ adding a store with createStore()
        test.createStore("Costco2");
        //Adding a store using addStore()
        Store testStore = new Store("Target2");
        test.addStore(testStore);
        //Saving stores to eylulsaglam.txt
        test.saveData();
        FileReader fr = new FileReader(test.getAllStoresFile());
        BufferedReader bfr = new BufferedReader(fr);
        int i = 0;
        String line = bfr.readLine();
        while (line != null && i < test.getStores().size()) {
            if (line.equals(test.getStores().get(i).toString())) {
                System.out.println("'Seller' addStore(), createStore(), saveStore() test " + (i + 1) + " passed!");
                i++;
            } else {
                System.out.println("'Store' addStore(), createStore(), saveStore() test " + (i + 1) + " failed");
                i++;
            }
            line = bfr.readLine();
        }


        //Deleting store from eylulsaglam using deleteStore
        test.deleteStore(testStore);
        if (test.getStores().size() == 1) {
            System.out.println("'Seller' deleteStore() test passed!");
        } else {
            System.out.println("'Seller' deleteStore() test failed");
        }

        Store testStore2 = new Store("Name");
        //Testing editStore()
        test.editStore(testStore2, "EditedName");
        //Check that storeName has been updated
        System.out.println(testStore2.getStoreName());
        if (testStore2.getStoreName().equals("EditedName")) {
            System.out.println("'Seller' editStore() test passed!");
        } else {
            System.out.println("'Seller' editStore() test failed");
        }
    }
}
