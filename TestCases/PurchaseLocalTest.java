/**
 * @author Aashna, Jiwon, Aysu, Mridu
 * @version 1.0
 */
public class PurchaseLocalTest {

    public static void main (String[] args) {
        Product test1 = new Product("Hair Brush", "Hair Supply Co.",
                "High-end hair brush with soft bristles", 5, 15.0);
        Purchase test = new Purchase(test1, "aysusaglam", 3);
        String expected = "Product name: Hair Brush\nStore Name: Hair Supply Co.\nDescription:" +
                " High-end hair brush with soft bristles\nPrice when bought: 15.00\n" +
                "Quantity bought: 3\nTotal Cost: 45.00";
        String actual = test.printDataForCustomer();
        if (expected.equals(actual)) {
            System.out.println("'Purchase' printDataForCustomer() test passed!");
        } else {
            System.out.println("'Purchase' printDataForCustomer() test failed");
        }
    }
}
