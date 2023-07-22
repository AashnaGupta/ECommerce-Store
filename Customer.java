import java.util.ArrayList;
import java.io.*;

/**
 * @author Aashna, Jiwon, Aysu, Mridu
 * @version 1.0
 */
public class Customer extends User {
    private ArrayList<Purchase> allPurchases;

    public Customer(String username, String email, String password) {
        super(username, email, password);
        allPurchases = new ArrayList<>();
    }

    public void addPurchase(Purchase purchase) {
        allPurchases.add(purchase);
    }

    public Purchase makeAPurchase(Store store, Product product, int quantity) throws IllegalArgumentException {
        if (quantity > product.getQuantity()) {
            throw new IllegalArgumentException("We don't have that much");
        } else {
            Purchase p = new Purchase(product, this.getUsername(), quantity);
            allPurchases.add(p);
            writeToPurchase(p);
            store.addPurchase(p);
            for (int i = 0; i < store.getAvailableProducts().size(); i++) {
                if (store.getAvailableProducts().get(i).getProductName().equals(product.getProductName())) {
                    store.getAvailableProducts().get(i).setQuantity(store.getAvailableProducts().get(i).getQuantity() - quantity);
                    break;
                }
            }
            return p;
        }
    }

    public void writeToPurchase(Purchase purchase) {
        try {
            File f = new File("purchaseLog.txt");
            f.createNewFile();
            FileOutputStream fos = new FileOutputStream(f, true);
            PrintWriter pw = new PrintWriter(fos);
            pw.write(purchase.toString());
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resetPurchases() {
        allPurchases = new ArrayList<>();
    }

    public ArrayList<Purchase> getAllPurchases() {
        return allPurchases;
    }

    public void exportPurchases(String filename) throws Exception {
        File f = new File(filename);
        try {
            if (!f.createNewFile()) {
                throw new Exception("Sorry, filename is taken!");
            } else {
                FileOutputStream fos = new FileOutputStream(f, false);
                PrintWriter pw = new PrintWriter(fos);

                for (Purchase purchase : allPurchases) {
                    pw.println(purchase.printDataForCustomer());
                }
                pw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
