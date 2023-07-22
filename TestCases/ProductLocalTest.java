/**
 * @author Aashna, Jiwon, Aysu, Mridu
 * @version 1.0
 */
public class ProductLocalTest {

    public static void main(String[] args) {
        Product test1 = new Product("Hair Brush", "Hair Supply Co.",
                "High-end hair brush with soft bristles", 5, 15.0);
        Product test2 = new Product("Hair Brush", "Hair Supply Co.",
                "High-end hair brush with soft bristles", 5, 15.0);
        Product test3 = new Product("Shampoo", "Hair Supply Co.",
                "Sulfate-free shampoo with floral scent", 5, 20.0);

        //Testing getProductDataLanding
        String expected = "Product name: Hair Brush\n Store selling it: Hair Supply Co.\n Price: 15.00\n";
        String actual = test1.getProductDataLanding();
        if (expected.equals(actual)) {
            System.out.println("'Product' getProductDataLanding() test passed!");
        } else {
            System.out.println("'Product' getProductDataLanding() test failed");
        }

        //Testing getProductDataByDesc
        expected = "Product name: Hair Brush\n Store selling it: Hair Supply Co.\n Product description: " +
                "High-end hair brush with soft bristles\n Price: 15.00\n";
        actual = test1.getProductDataByDesc();
        if (expected.equals(actual)) {
            System.out.println("'Product' getProductDataByDesc() test passed!");
        } else {
            System.out.println("'Product' getProductDataByDesc() test failed");
        }

        //Testing get ProductDataByQuantity
        expected = "Product name: Hair Brush\n Store selling it: Hair Supply Co.\n Quantity available to buy: 5\n Price: 15.00\n";
        actual = test1.getProductDataByQuantity();
        if (expected.equals(actual)) {
            System.out.println("'Product' getProductDataByQuantity() test passed!");
        } else {
            System.out.println("'Product' getProductDataByQuantity() test failed");
        }

        //Testing getProductDataProductPage() Method
        expected = "Product name: Hair Brush\n Store selling it: Hair Supply Co.\n  Product description: " +
                "High-end hair brush with soft bristles\n Quantity available to buy: 5\n Price: 15.00\n";
        actual = test1.getProductDataProductPage();
        if (expected.equals(actual)) {
            System.out.println("'Product' getProductDataProductPage() test passed!");
        } else {
            System.out.println("'Product' getProductDataProductPage() test failed");
        }

        //Testing equals() Method
        if (test1.equals(test2)) {
            System.out.println("'Product' equals() test 1 passed!");
        } else {
            System.out.println("'Product' equals() test 1 failed");
        }

        if (!test1.equals(test3)) {
            System.out.println("'Product' equals() test 2 passed!");
        } else {
            System.out.println("'Product' equals() test 2 failed");
        }

        //Testing toString() Method
        expected = "\"Hair Brush\",\"Hair Supply Co.\",\"High-end hair brush with soft bristles\",5,15.00";
        actual = test1.toString();
        if (expected.equals(actual)) {
            System.out.println("'Product' toString() passed!");
        } else {
            System.out.println("'Product' toString() failed");
        }
    }
}
