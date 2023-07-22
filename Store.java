import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.io.*;

/**
 * @author Aashna, Jiwon, Aysu, Mridu
 * @version 1.0
 * Assumptions - storeName will always be unique when created
 */

public class Store {

    private String storeName;
    private String storeProductsFile;
    private ArrayList<Product> availableProducts;
    private ArrayList<Purchase> allPurchases;

    public Store(String storeName) {
        this.storeName = storeName;
        this.storeProductsFile = storeName.replace(" ", "_") + ".txt";
        this.availableProducts = new ArrayList<>();
        this.allPurchases = new ArrayList<>();
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreProductsFile() {
        return storeProductsFile;
    }

    public ArrayList<Product> getAvailableProducts() {
        return availableProducts;
    }

    public void setStoreProductsFile() {
        this.storeProductsFile = this.storeName.replace(" ", "_") + ".txt";
    }

    public void addProduct(Product product) {
        availableProducts.add(product);
    }

    public void setAvailableProducts(ArrayList<Product> availableProducts) {
        this.availableProducts.addAll(availableProducts);
    }

    public void createProduct(String productName, String description, int quantity, double price) {
        availableProducts.add(new Product(productName, this.storeName, description, quantity, price));
    }

    public void deleteProduct(String productName) {
        for (int i = 0; i < availableProducts.size(); i++) {
            if (availableProducts.get(i).getProductName().equalsIgnoreCase(productName)) {
                availableProducts.remove(i);
                break;
            }
        }
    }

    public void resetPurchases() {
        allPurchases = new ArrayList<>();
    }

    public ArrayList<Purchase> getAllPurchases() {
        return allPurchases;
    }

    public void addPurchase(Purchase purchase) {
        allPurchases.add(purchase);
    }

    public void saveProducts() {
        try {
            File f = new File(storeProductsFile);
            f.createNewFile();
            FileOutputStream fos = new FileOutputStream(f, false);
            PrintWriter pw = new PrintWriter(fos);
            for (Product product : availableProducts) {
                pw.println(product.toString());
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return storeName;
    }

    public String viewAllPurchases() {
        String result = "";
        result += String.format("All purchases from %s\n", storeName);
        for (Purchase purchase : allPurchases) {
            result = String.format("Customer name: %s%n" + "Product Name: %s%n" + "Product description: %s%n" +
                            "Quantity sold: %d%n" + "Price for one: %.2f%n" + "Total revenue: %.2f%n",
                    purchase.getCustomerUsername(), purchase.getProductName(), purchase.getDescription(),
                    purchase.getQuantity(), purchase.getPrice(), purchase.getRevenue());
        }
        return result;
    }

    //import products from a file (csv file) filename
    public void importProducts(String filename) throws IOException, ArrayIndexOutOfBoundsException {
        File f = new File(filename);
        if (!f.exists()) {
            throw new FileNotFoundException("File does not exist!");
        }

        FileReader fr = new FileReader(f);
        BufferedReader bfr = new BufferedReader(fr);

        String line = bfr.readLine();
        while (line != null) {
            String[] lineAr = line.split(",(?=([^\"]|\"[^\"]*\")*$)");
            if (lineAr.length != 5) {
                throw new ArrayIndexOutOfBoundsException("Invalid file format!");
            }
            availableProducts.add(new Product(lineAr[0].substring(1, lineAr[0].length() - 1),
                    lineAr[1].substring(1, lineAr[1].length() - 1), lineAr[2].substring(1, lineAr[2].length() - 1),
                    Integer.parseInt(lineAr[3]), Double.parseDouble(lineAr[4])));
            line = bfr.readLine();
        }
    }

    //export product as a csv file to filename given
    public void exportProducts(String filename) throws Exception {
        File f = new File(filename);
        try {
            if (!f.createNewFile()) {
                throw new Exception("Sorry, filename is taken!");
            }

            FileOutputStream fos = new FileOutputStream(f, false);
            PrintWriter pw = new PrintWriter(fos);

            for (Product product : availableProducts) {
                pw.println(product.toString());
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
