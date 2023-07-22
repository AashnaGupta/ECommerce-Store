import java.util.ArrayList;
import java.io.*;

/*
notes -
seller.txt -- username,email,password
username.txt -- storename1
                storename2
                .
                .
                .
                storenameN
 */

/**
 * @author Aashna, Jiwon, Aysu, Mridu
 * @version 1.0
 */
public class Seller extends User {

    private ArrayList<Store> stores;
    private String allStoresFile;

    public Seller(String username, String email, String password) {
        super(username, email, password);
        this.allStoresFile = username + ".txt";
        this.stores = new ArrayList<>();
    }

    public void setUsername(String username) {
        super.setUsername(username);
        this.allStoresFile = username + ".txt";
    }

    public String getAllStoresFile() {
        return allStoresFile;
    }

    public void setAllStoresFile(String allStoresFile) {
        this.allStoresFile = allStoresFile;
    }

    public ArrayList<Store> getStores() {
        return stores;
    }

    public void setStores(ArrayList<Store> stores) {
        this.stores = stores;
    }

    public void addStore(Store store) {
        stores.add(store);
    }

    public void createStore(String storeName) {
        Store newStore = new Store(storeName);
        stores.add(newStore);
    }

    public void deleteStore(Store store) {
        for (int i = 0; i < stores.size(); i++) {
            if (store.getStoreName().equals(stores.get(i).getStoreName())) {
                File deleteFile = new File(stores.get(i).getStoreProductsFile());
                deleteFile.delete();
                stores.remove(i);
                break;
            }
        }
    }

    public void editStore(Store store, String storeName) {
        for (Store s : stores) {
            if (store.getStoreName().equals(s.getStoreName())) {
                s.setStoreName(storeName);
                break;
            }
        }
    }

    public void saveData() {
        try {
            File f = new File(allStoresFile);
            f.createNewFile();
            FileOutputStream fos = new FileOutputStream(f, false);
            PrintWriter pw = new PrintWriter(fos);
            for (Store store : stores) {
                store.saveProducts(); //saves products of each store to each store's storeProductFile
                pw.println(store.getStoreName()); //appends each store's name to the seller's allStoresFile
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
