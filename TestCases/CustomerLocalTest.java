import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Aashna, Jiwon, Aysu, Mridu
 * @version 1.0
 */
public class CustomerLocalTest {
    public static void main(String[] args) throws Exception {
        Customer test = new Customer("eylulsaglam", "esaglam@purdue.edu", "pass");
        test.resetPurchases();
        //Making a purchase w/ makeAPurchase() (uses methods addPurchase() and writeToPurchase()
        Store testStore = new Store("Target");
        Product testProduct = new Product("Luxury Medium Density Down-Alternative Bed Pillow - Set of 2", "Target", "Constructed with 100% cotton casing and 10% polyester cluster fiber fill", 35, 44.49);
        Purchase testPurchase = test.makeAPurchase(testStore, testProduct, 2);
        //Check that item has been added to ArrayList with total purchases
        if (test.getAllPurchases().size() > 0) {
            System.out.println("'Customer' makeAPurchase() and addPurchase() tests passed!");
        } else {
            System.out.println("'Customer' makeAPurchase() and addPurchase() tests failed");
        }

        //Check that contents of file are equivalent to the array list
        FileReader fr = new FileReader("purchaseLog.txt");
        BufferedReader bfr = new BufferedReader(fr);
        int i = 0;
        String line = bfr.readLine();
        while (line != null && i < test.getAllPurchases().size()) {
            if (line.equals(test.getAllPurchases().get(i).toString())) {
                System.out.println("'Customer' writeToPurchase() test " + (i + 1) + " passed!");
                i++;
            } else {
                System.out.println("'Customer' writeToPurchase() test " + (i + 1) + " failed");
                i++;
            }
            line = bfr.readLine();
        }

        //Tests the exportPurchases() method
        test.exportPurchases("ExportTest.csv");
        //Tests equality between the lines of ExportTest.csv and allPurchases array list
        fr = new FileReader("ExportTest.csv");
        bfr = new BufferedReader(fr);
        i = 0;
        line = bfr.readLine();
        while (line != null && i < test.getAllPurchases().size()) {
            if (line.equals(test.getAllPurchases().get(i).toString())) {
                System.out.println("'Customer' exportPurchases() test " + (i + 1) + " passed!");
                i++;
            } else {
                System.out.println("'Customer' exportPurchases() test " + (i + 1) + " failed");
                i++;
            }
            line = bfr.readLine();
        }
    }
}
