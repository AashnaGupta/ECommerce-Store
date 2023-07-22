import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * @author Aashna, Jiwon, Aysu, Mridu
 * @version 1.0
 */
public class StoreLocalTest {

    public static void main(String[] args) throws Exception {
        //Creating store and file
        Store test = new Store("Michaels2");
        test.setStoreProductsFile();
        test.setAvailableProducts(new ArrayList<>());
        //Creating/ adding product with createProduct()
        test.createProduct("Brush Pen", "Colorful calligraphy pen", 3, 15.00);
        //Adding products using addProduct()
        Product testProduct = new Product("Fabric", "Michaels2", "Assortment of primary colored fabrics", 5, 20.00);
        test.addProduct(testProduct);
        //Saving products to Michaels2.txt using saveProducts()
        test.saveProducts();
        FileReader fr = new FileReader(test.getStoreProductsFile());
        BufferedReader bfr = new BufferedReader(fr);
        int i = 0;
        String line = bfr.readLine();
        while (line != null && i < test.getAvailableProducts().size()) {
            if (line.equals(test.getAvailableProducts().get(i).toString())) {
                System.out.println("'Store' addProduct(), createProduct(), saveProducts() test " + (i + 1) + " passed!");
                i++;
            } else {
                System.out.println("'Store' addProduct(), createProduct(), saveProducts() test " + (i + 1) + " failed");
                i++;
            }
            line = bfr.readLine();
        }

        //Deleting products from Michael2.txt/availableProducts using deleteProduct()
        test.deleteProduct("Fabric");
        if (test.getAvailableProducts().size() == 1) {
            System.out.println("'Store' deleteProduct() test passed!");
        } else {
            System.out.println("'Store' deleteProduct() test failed");
        }

        //Testing purchase-related methods
        test.resetPurchases();
        Purchase testPurchase = new Purchase(testProduct, "aysusaglam", 2);
        test.addPurchase(testPurchase);
        //viewAllPurchases()
        String expected = "All purchaces from Michaels2:\nCustomer name: aysusaglam\nProduct Name: Fabric\nProduct description: Assortment of primary colored fabrics" +
                "\nQuantity sold: 2\nPrice for one: 20.00\nTotal revenue: 40.00";
        String actual = test.viewAllPurchases();
        if (expected.equals(actual)) {
            System.out.println("'Store' viewAllPurchases() passed!");
        } else {
            System.out.println("'Store' viewAllPurchases() failed");
        }
        //Testing importProducts()
        test.importProducts("ImportTest.csv");
        //Checks if array list of available products has increased
        if (test.getAvailableProducts().size() > 2) {
            test.saveProducts();
            fr = new FileReader("Michaels2.txt");
            bfr = new BufferedReader(fr);
            i = 0;
            line = bfr.readLine();
            while (line != null && i < test.getAvailableProducts().size()) {
                if (line.equals(test.getAvailableProducts().get(i).toString())) {
                    System.out.println("'Store' importProducts() test " + (i + 1) + " passed!");
                    i++;
                } else {
                    System.out.println("'Store' importProducts() test " + (i + 1) + " failed");
                    i++;
                }
                line = bfr.readLine();
            }
        }

        //Testing exportProducts()
        test.exportProducts("ExportTest.csv");
        //Tests equality between the lines of ExportTest.csv and availableProducts array list
        test.saveProducts();
        fr = new FileReader("ExportTest.csv");
        bfr = new BufferedReader(fr);
        i = 0;
        line = bfr.readLine();
        while (line != null && i < test.getAvailableProducts().size()) {
            if (line.equals(test.getAvailableProducts().get(i).toString())) {
                System.out.println("'Store' exportProducts() test " + (i + 1) + " passed!");
                i++;
            } else {
                System.out.println("'Store' exportProducts() test " + (i + 1) + " failed");
                i++;
            }
            line = bfr.readLine();
        }
    }
}
