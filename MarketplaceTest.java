import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class MarketplaceTest {

    private final PrintStream originalOutput = System.out;
    private final InputStream originalSysin = System.in;

    @SuppressWarnings("FieldCanBeLocal")
    private ByteArrayInputStream testIn;

    @SuppressWarnings("FieldCanBeLocal")
    private ByteArrayOutputStream testOut;

    @Before
    public void outputStart() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @After
    public void restoreInputAndOutput() {
        System.setIn(originalSysin);
        System.setOut(originalOutput);
    }

    private String getOutput() {
        return testOut.toString();
    }

    @SuppressWarnings("SameParameterValue")
    private void receiveInput(String str) {
        testIn = new ByteArrayInputStream(str.getBytes());
        System.setIn(testIn);
    }

    @Test(timeout = 1000)
    public void testCustomerOptions() {
        String input = "";
        String expected = "";

        // attempt login to a customer user account that doesn't exist
        expected += "Welcome to Butter, your one stop for everything!\n" +
                "What would you like to do today?\n" +
                "1. Create new account\n" +
                "2. Login to an existing account\n" +
                "3. Exit marketplace\n";
        input += "2\n";

        expected += "Enter username: \n";
        input += "socrates\n";

        expected += "Enter password: \n";
        input += "socratesLovesYou\n";

        expected += "Login as:\n" +
                "1. Seller\n" +
                "2. Customer\n";
        input += "2\n";

        expected += "Invalid login!\n";

        // login to a customer account that exists
        expected += "Welcome to Butter, your one stop for everything!\n" +
                "What would you like to do today?\n" +
                "1. Create new account\n" +
                "2. Login to an existing account\n" +
                "3. Exit marketplace\n";
        input += "2\n";

        expected += "Enter username: \n";
        input += "aysusaglam\n";

        expected += "Enter password: \n";
        input += "aysu\n";

        expected += "Login as:\n" +
                "1. Seller\n" +
                "2. Customer\n";
        input += "2\n";

        expected += "Welcome back aysusaglam!\n" +
                "What next?\n" +
                "1. Edit my account\n" +
                "2. Delete my account\n" +
                "3. View more options :P\n" +
                "4. Logout\n";

        // view more options

        input += "3\n";

        expected += "Would you like to:\n" +
                "1. View available products\n" +
                "2. View purchase history\n" +
                "3. View store statistics\n" +
                "4. Go Back\n";

        // view available products

        input += "1\n";

        expected += "-------------\n" +
                "Product name: Luxury Medium Density Down-Alternative Bed Pillow - Set of 2\n" +
                " Store selling it: Target\n" +
                " Price: 44.49\n" +
                "\n" +
                "Product name: 4pk Wood Coaster Set\n" +
                " Store selling it: Target\n" +
                " Price: 12.00\n" +
                "\n" +
                "Product name: Battery Operated Rotating Tinsel Christmas Tree\n" +
                " Store selling it: Target\n" +
                " Price: 25.00\n" +
                "\n" +
                "Product name: Beats Studio Buds True Wireless Noise Cancelling Bluetooth Earbuds\n" +
                " Store selling it: Target\n" +
                " Price: 99.99\n" +
                "\n" +
                "Product name: MacBook Pro\n" +
                " Store selling it: Costco Wholesale\n" +
                " Price: 1349.99\n" +
                "\n" +
                "Product name: Bose Smart Soundbar 700\n" +
                " Store selling it: Costco Wholesale\n" +
                " Price: 549.99\n" +
                "\n" +
                "Product name: Dyson Cyclone V10 Animal + Cordless Vacuum Cleaner\n" +
                " Store selling it: Costco WholeSale\n" +
                " Price: 399.99\n" +
                "\n" +
                "Product name: Sorry! Kids Board Game\n" +
                " Store selling it: Walmart\n" +
                " Price: 6.00\n" +
                "\n" +
                "Product name: Instant Pot Duo 6-Quart 7-in-1 Electric Pressure Cooker\n" +
                " Store selling it: Walmart\n" +
                " Price: 50.00\n" +
                "\n" +
                "Product name: Google Nest Mini\n" +
                " Store selling it: Walmart\n" +
                " Price: 18.00\n" +
                "\n" +
                "Product name: Cozy Lounger Pet Bed Mattress\n" +
                " Store selling it: Walmart\n" +
                " Price: 25.98\n" +
                "\n" +
                "Product name: 3 Car Garage\n" +
                " Store selling it: Gepettos\n" +
                " Price: 29.99\n" +
                "\n" +
                "Product name: Balloon Powered Car\n" +
                " Store selling it: Gepettos\n" +
                " Price: 5.99\n" +
                "\n" +
                "Product name: Duncan Classic Yoyo\n" +
                " Store selling it: Gepettos\n" +
                " Price: 8.99\n" +
                "\n" +
                "Product name: Mega Rainbow Ball\n" +
                " Store selling it: Gepettos\n" +
                " Price: 12.99\n" +
                "\n" +
                "Product name: LotFancy 20 inch Teddy Bear Stuffed Animal\n" +
                " Store selling it: Toys R Us\n" +
                " Price: 10.99\n" +
                "\n" +
                "Product name: Magentic Building Blocks\n" +
                " Store selling it: Toys R Us\n" +
                " Price: 19.99\n" +
                "\n" +
                "Product name: Remote Control Stunt Car\n" +
                " Store selling it: Toys R Us\n" +
                " Price: 29.99\n" +
                "\n" +
                "Product name: Kidibeats Drum Set\n" +
                " Store selling it: Toys R Us\n" +
                " Price: 15.89\n" +
                "\n" +
                "Product name: Fine Paintbrush\n" +
                " Store selling it: Michaels\n" +
                " Price: 14.99\n" +
                "\n" +
                "Product name: Elmer's Glue\n" +
                " Store selling it: Michaels\n" +
                " Price: 9.99\n" +
                "\n" +
                "Product name: Basic 20cm Scissors\n" +
                " Store selling it: Michaels\n" +
                " Price: 7.99\n" +
                "\n" +
                "Product name: Crayola 16 Pack Crayons\n" +
                " Store selling it: Michaels\n" +
                " Price: 7.99\n" +
                "\n" +
                "Would you like to:\n" +
                "1. Search products by name\n" +
                "2. Search products by store\n" +
                "3. Search products by description\n" +
                "4. Sort products by price\n" +
                "5. Sort Products by quantity\n" +
                "6. Make a purchase\n" +
                "7. Back\n";

        // search by name

        input += "1\n";

        expected += "Enter search term (by product name):\n";
        input += "car\n";

        expected += "-------------\n" +
                "Product name: 3 Car Garage\n" +
                " Store selling it: Gepettos\n" +
                " Price: 29.99\n" +
                "\n" +
                "Product name: Balloon Powered Car\n" +
                " Store selling it: Gepettos\n" +
                " Price: 5.99\n" +
                "\n" +
                "Product name: Remote Control Stunt Car\n" +
                " Store selling it: Toys R Us\n" +
                " Price: 29.99\n" +
                "\n" +
                "Would you like to:\n" +
                "1. Search products by name\n" +
                "2. Search products by store\n" +
                "3. Search products by description\n" +
                "4. Sort products by price\n" +
                "5. Sort Products by quantity\n" +
                "6. Make a purchase\n" +
                "7. Back\n";

        // search by store

        input += "2\n";

        expected += "Enter search term (by store name):\n";
        input += "target\n";

        expected += "-------------\n" +
                "Product name: Luxury Medium Density Down-Alternative Bed Pillow - Set of 2\n" +
                " Store selling it: Target\n" +
                " Price: 44.49\n" +
                "\n" +
                "Product name: 4pk Wood Coaster Set\n" +
                " Store selling it: Target\n" +
                " Price: 12.00\n" +
                "\n" +
                "Product name: Battery Operated Rotating Tinsel Christmas Tree\n" +
                " Store selling it: Target\n" +
                " Price: 25.00\n" +
                "\n" +
                "Product name: Beats Studio Buds True Wireless Noise Cancelling Bluetooth Earbuds\n" +
                " Store selling it: Target\n" +
                " Price: 99.99\n" +
                "\n" +
                "Would you like to:\n" +
                "1. Search products by name\n" +
                "2. Search products by store\n" +
                "3. Search products by description\n" +
                "4. Sort products by price\n" +
                "5. Sort Products by quantity\n" +
                "6. Make a purchase\n" +
                "7. Back\n";

        // search by description

        input += "3\n";

        expected += "Enter search term (by product description):\n";
        input += "toy\n";

        expected += "-------------\n" +
                "Product name: Magentic Building Blocks\n" +
                " Store selling it: Toys R Us\n" +
                " Product description: A wonderful creative toy to cultivate STEM learning; compatible with major brands\n" +
                " Price: 19.99\n" +
                "\n" +
                "Would you like to:\n" +
                "1. Search products by name\n" +
                "2. Search products by store\n" +
                "3. Search products by description\n" +
                "4. Sort products by price\n" +
                "5. Sort Products by quantity\n" +
                "6. Make a purchase\n" +
                "7. Back\n";

        // sort by price, high to low

        input += "4\n";
        expected += "Please select:\n" +
                "1. High to Low\n" +
                "2. Low to High\n";

        input += "1\n";

        expected += "Sorting products by price (high to low)...\n" +
                "-------------\n" +
                "Product name: MacBook Pro\n" +
                " Store selling it: Costco Wholesale\n" +
                " Price: 1349.99\n" +
                "\n" +
                "Product name: Bose Smart Soundbar 700\n" +
                " Store selling it: Costco Wholesale\n" +
                " Price: 549.99\n" +
                "\n" +
                "Product name: Dyson Cyclone V10 Animal + Cordless Vacuum Cleaner\n" +
                " Store selling it: Costco WholeSale\n" +
                " Price: 399.99\n" +
                "\n" +
                "Product name: Beats Studio Buds True Wireless Noise Cancelling Bluetooth Earbuds\n" +
                " Store selling it: Target\n" +
                " Price: 99.99\n" +
                "\n" +
                "Product name: Instant Pot Duo 6-Quart 7-in-1 Electric Pressure Cooker\n" +
                " Store selling it: Walmart\n" +
                " Price: 50.00\n" +
                "\n" +
                "Product name: Luxury Medium Density Down-Alternative Bed Pillow - Set of 2\n" +
                " Store selling it: Target\n" +
                " Price: 44.49\n" +
                "\n" +
                "Product name: 3 Car Garage\n" +
                " Store selling it: Gepettos\n" +
                " Price: 29.99\n" +
                "\n" +
                "Product name: Remote Control Stunt Car\n" +
                " Store selling it: Toys R Us\n" +
                " Price: 29.99\n" +
                "\n" +
                "Product name: Cozy Lounger Pet Bed Mattress\n" +
                " Store selling it: Walmart\n" +
                " Price: 25.98\n" +
                "\n" +
                "Product name: Battery Operated Rotating Tinsel Christmas Tree\n" +
                " Store selling it: Target\n" +
                " Price: 25.00\n" +
                "\n" +
                "Product name: Magentic Building Blocks\n" +
                " Store selling it: Toys R Us\n" +
                " Price: 19.99\n" +
                "\n" +
                "Product name: Google Nest Mini\n" +
                " Store selling it: Walmart\n" +
                " Price: 18.00\n" +
                "\n" +
                "Product name: Kidibeats Drum Set\n" +
                " Store selling it: Toys R Us\n" +
                " Price: 15.89\n" +
                "\n" +
                "Product name: Fine Paintbrush\n" +
                " Store selling it: Michaels\n" +
                " Price: 14.99\n" +
                "\n" +
                "Product name: Mega Rainbow Ball\n" +
                " Store selling it: Gepettos\n" +
                " Price: 12.99\n" +
                "\n" +
                "Product name: 4pk Wood Coaster Set\n" +
                " Store selling it: Target\n" +
                " Price: 12.00\n" +
                "\n" +
                "Product name: LotFancy 20 inch Teddy Bear Stuffed Animal\n" +
                " Store selling it: Toys R Us\n" +
                " Price: 10.99\n" +
                "\n" +
                "Product name: Elmer's Glue\n" +
                " Store selling it: Michaels\n" +
                " Price: 9.99\n" +
                "\n" +
                "Product name: Duncan Classic Yoyo\n" +
                " Store selling it: Gepettos\n" +
                " Price: 8.99\n" +
                "\n" +
                "Product name: Basic 20cm Scissors\n" +
                " Store selling it: Michaels\n" +
                " Price: 7.99\n" +
                "\n" +
                "Product name: Crayola 16 Pack Crayons\n" +
                " Store selling it: Michaels\n" +
                " Price: 7.99\n" +
                "\n" +
                "Product name: Sorry! Kids Board Game\n" +
                " Store selling it: Walmart\n" +
                " Price: 6.00\n" +
                "\n" +
                "Product name: Balloon Powered Car\n" +
                " Store selling it: Gepettos\n" +
                " Price: 5.99\n" +
                "\n" +
                "Would you like to:\n" +
                "1. Search products by name\n" +
                "2. Search products by store\n" +
                "3. Search products by description\n" +
                "4. Sort products by price\n" +
                "5. Sort Products by quantity\n" +
                "6. Make a purchase\n" +
                "7. Back\n";

        // by price, low to high

        input += "4\n";

        expected += "Please select:\n" +
                "1. High to Low\n" +
                "2. Low to High\n";

        input += "2\n";

        expected += "Sorting products by price (low to high)...\n" +
                "-------------\n" +
                "Product name: Balloon Powered Car\n" +
                " Store selling it: Gepettos\n" +
                " Price: 5.99\n" +
                "\n" +
                "Product name: Sorry! Kids Board Game\n" +
                " Store selling it: Walmart\n" +
                " Price: 6.00\n" +
                "\n" +
                "Product name: Basic 20cm Scissors\n" +
                " Store selling it: Michaels\n" +
                " Price: 7.99\n" +
                "\n" +
                "Product name: Crayola 16 Pack Crayons\n" +
                " Store selling it: Michaels\n" +
                " Price: 7.99\n" +
                "\n" +
                "Product name: Duncan Classic Yoyo\n" +
                " Store selling it: Gepettos\n" +
                " Price: 8.99\n" +
                "\n" +
                "Product name: Elmer's Glue\n" +
                " Store selling it: Michaels\n" +
                " Price: 9.99\n" +
                "\n" +
                "Product name: LotFancy 20 inch Teddy Bear Stuffed Animal\n" +
                " Store selling it: Toys R Us\n" +
                " Price: 10.99\n" +
                "\n" +
                "Product name: 4pk Wood Coaster Set\n" +
                " Store selling it: Target\n" +
                " Price: 12.00\n" +
                "\n" +
                "Product name: Mega Rainbow Ball\n" +
                " Store selling it: Gepettos\n" +
                " Price: 12.99\n" +
                "\n" +
                "Product name: Fine Paintbrush\n" +
                " Store selling it: Michaels\n" +
                " Price: 14.99\n" +
                "\n" +
                "Product name: Kidibeats Drum Set\n" +
                " Store selling it: Toys R Us\n" +
                " Price: 15.89\n" +
                "\n" +
                "Product name: Google Nest Mini\n" +
                " Store selling it: Walmart\n" +
                " Price: 18.00\n" +
                "\n" +
                "Product name: Magentic Building Blocks\n" +
                " Store selling it: Toys R Us\n" +
                " Price: 19.99\n" +
                "\n" +
                "Product name: Battery Operated Rotating Tinsel Christmas Tree\n" +
                " Store selling it: Target\n" +
                " Price: 25.00\n" +
                "\n" +
                "Product name: Cozy Lounger Pet Bed Mattress\n" +
                " Store selling it: Walmart\n" +
                " Price: 25.98\n" +
                "\n" +
                "Product name: 3 Car Garage\n" +
                " Store selling it: Gepettos\n" +
                " Price: 29.99\n" +
                "\n" +
                "Product name: Remote Control Stunt Car\n" +
                " Store selling it: Toys R Us\n" +
                " Price: 29.99\n" +
                "\n" +
                "Product name: Luxury Medium Density Down-Alternative Bed Pillow - Set of 2\n" +
                " Store selling it: Target\n" +
                " Price: 44.49\n" +
                "\n" +
                "Product name: Instant Pot Duo 6-Quart 7-in-1 Electric Pressure Cooker\n" +
                " Store selling it: Walmart\n" +
                " Price: 50.00\n" +
                "\n" +
                "Product name: Beats Studio Buds True Wireless Noise Cancelling Bluetooth Earbuds\n" +
                " Store selling it: Target\n" +
                " Price: 99.99\n" +
                "\n" +
                "Product name: Dyson Cyclone V10 Animal + Cordless Vacuum Cleaner\n" +
                " Store selling it: Costco WholeSale\n" +
                " Price: 399.99\n" +
                "\n" +
                "Product name: Bose Smart Soundbar 700\n" +
                " Store selling it: Costco Wholesale\n" +
                " Price: 549.99\n" +
                "\n" +
                "Product name: MacBook Pro\n" +
                " Store selling it: Costco Wholesale\n" +
                " Price: 1349.99\n" +
                "\n" +
                "Would you like to:\n" +
                "1. Search products by name\n" +
                "2. Search products by store\n" +
                "3. Search products by description\n" +
                "4. Sort products by price\n" +
                "5. Sort Products by quantity\n" +
                "6. Make a purchase\n" +
                "7. Back\n";

        // by quantity

        input += "5\n";

        expected += "Please select:\n" +
                "1. High to Low\n" +
                "2. Low to High\n";
        input += "2\n";

        expected += "Sorting products by quantity (low to high)...\n" +
                "-------------\n" +
                "Product name: Kidibeats Drum Set\n" +
                " Store selling it: Toys R Us\n" +
                " Quantity available to buy: 5\n" +
                " Price: 15.89\n" +
                "\n" +
                "Product name: Duncan Classic Yoyo\n" +
                " Store selling it: Gepettos\n" +
                " Quantity available to buy: 10\n" +
                " Price: 8.99\n" +
                "\n" +
                "Product name: Crayola 16 Pack Crayons\n" +
                " Store selling it: Michaels\n" +
                " Quantity available to buy: 10\n" +
                " Price: 7.99\n" +
                "\n" +
                "Product name: 4pk Wood Coaster Set\n" +
                " Store selling it: Target\n" +
                " Quantity available to buy: 15\n" +
                " Price: 12.00\n" +
                "\n" +
                "Product name: 3 Car Garage\n" +
                " Store selling it: Gepettos\n" +
                " Quantity available to buy: 15\n" +
                " Price: 29.99\n" +
                "\n" +
                "Product name: Remote Control Stunt Car\n" +
                " Store selling it: Toys R Us\n" +
                " Quantity available to buy: 15\n" +
                " Price: 29.99\n" +
                "\n" +
                "Product name: Battery Operated Rotating Tinsel Christmas Tree\n" +
                " Store selling it: Target\n" +
                " Quantity available to buy: 20\n" +
                " Price: 25.00\n" +
                "\n" +
                "Product name: Beats Studio Buds True Wireless Noise Cancelling Bluetooth Earbuds\n" +
                " Store selling it: Target\n" +
                " Quantity available to buy: 20\n" +
                " Price: 99.99\n" +
                "\n" +
                "Product name: Google Nest Mini\n" +
                " Store selling it: Walmart\n" +
                " Quantity available to buy: 20\n" +
                " Price: 18.00\n" +
                "\n" +
                "Product name: LotFancy 20 inch Teddy Bear Stuffed Animal\n" +
                " Store selling it: Toys R Us\n" +
                " Quantity available to buy: 20\n" +
                " Price: 10.99\n" +
                "\n" +
                "Product name: Cozy Lounger Pet Bed Mattress\n" +
                " Store selling it: Walmart\n" +
                " Quantity available to buy: 25\n" +
                " Price: 25.98\n" +
                "\n" +
                "Product name: Balloon Powered Car\n" +
                " Store selling it: Gepettos\n" +
                " Quantity available to buy: 25\n" +
                " Price: 5.99\n" +
                "\n" +
                "Product name: Fine Paintbrush\n" +
                " Store selling it: Michaels\n" +
                " Quantity available to buy: 25\n" +
                " Price: 14.99\n" +
                "\n" +
                "Product name: Mega Rainbow Ball\n" +
                " Store selling it: Gepettos\n" +
                " Quantity available to buy: 30\n" +
                " Price: 12.99\n" +
                "\n" +
                "Product name: Luxury Medium Density Down-Alternative Bed Pillow - Set of 2\n" +
                " Store selling it: Target\n" +
                " Quantity available to buy: 35\n" +
                " Price: 44.49\n" +
                "\n" +
                "Product name: Sorry! Kids Board Game\n" +
                " Store selling it: Walmart\n" +
                " Quantity available to buy: 40\n" +
                " Price: 6.00\n" +
                "\n" +
                "Product name: Magentic Building Blocks\n" +
                " Store selling it: Toys R Us\n" +
                " Quantity available to buy: 40\n" +
                " Price: 19.99\n" +
                "\n" +
                "Product name: Instant Pot Duo 6-Quart 7-in-1 Electric Pressure Cooker\n" +
                " Store selling it: Walmart\n" +
                " Quantity available to buy: 50\n" +
                " Price: 50.00\n" +
                "\n" +
                "Product name: Elmer's Glue\n" +
                " Store selling it: Michaels\n" +
                " Quantity available to buy: 50\n" +
                " Price: 9.99\n" +
                "\n" +
                "Product name: Basic 20cm Scissors\n" +
                " Store selling it: Michaels\n" +
                " Quantity available to buy: 50\n" +
                " Price: 7.99\n" +
                "\n" +
                "Product name: Dyson Cyclone V10 Animal + Cordless Vacuum Cleaner\n" +
                " Store selling it: Costco WholeSale\n" +
                " Quantity available to buy: 75\n" +
                " Price: 399.99\n" +
                "\n" +
                "Product name: Bose Smart Soundbar 700\n" +
                " Store selling it: Costco Wholesale\n" +
                " Quantity available to buy: 100\n" +
                " Price: 549.99\n" +
                "\n" +
                "Product name: MacBook Pro\n" +
                " Store selling it: Costco Wholesale\n" +
                " Quantity available to buy: 200\n" +
                " Price: 1349.99\n" +
                "\n" +
                "Would you like to:\n" +
                "1. Search products by name\n" +
                "2. Search products by store\n" +
                "3. Search products by description\n" +
                "4. Sort products by price\n" +
                "5. Sort Products by quantity\n" +
                "6. Make a purchase\n" +
                "7. Back\n";

        // buy a product
        input += "6\n";

        expected += "Select a product:\n" +
                "1) Luxury Medium Density Down-Alternative Bed Pillow - Set of 2\n" +
                "2) 4pk Wood Coaster Set\n" +
                "3) Battery Operated Rotating Tinsel Christmas Tree\n" +
                "4) Beats Studio Buds True Wireless Noise Cancelling Bluetooth Earbuds\n" +
                "5) MacBook Pro\n" +
                "6) Bose Smart Soundbar 700\n" +
                "7) Dyson Cyclone V10 Animal + Cordless Vacuum Cleaner\n" +
                "8) Sorry! Kids Board Game\n" +
                "9) Instant Pot Duo 6-Quart 7-in-1 Electric Pressure Cooker\n" +
                "10) Google Nest Mini\n" +
                "11) Cozy Lounger Pet Bed Mattress\n" +
                "12) 3 Car Garage\n" +
                "13) Balloon Powered Car\n" +
                "14) Duncan Classic Yoyo\n" +
                "15) Mega Rainbow Ball\n" +
                "16) LotFancy 20 inch Teddy Bear Stuffed Animal\n" +
                "17) Magentic Building Blocks\n" +
                "18) Remote Control Stunt Car\n" +
                "19) Kidibeats Drum Set\n" +
                "20) Fine Paintbrush\n" +
                "21) Elmer's Glue\n" +
                "22) Basic 20cm Scissors\n" +
                "23) Crayola 16 Pack Crayons\n" +
                "\n";

        input += "15\n";

        expected += "-------------\n" +
                "Product name: Mega Rainbow Ball\n" +
                " Store selling it: Gepettos\n" +
                "  Product description: A giant rainbow bouncy ball perfect for outdoor activities :)\n" +
                " Quantity available to buy: 30\n" +
                " Price: 12.99\n" +
                "\n" +
                "Would you like to purchase this product?\n" +
                "1. Yes\n" +
                "2. Exit\n";

        input += "1\n";

        expected += "How much would you like to purchase?\n";

        input += "5\n";

        // exit and view purchase history
        expected += "Would you like to:\n" +
                "1. Search products by name\n" +
                "2. Search products by store\n" +
                "3. Search products by description\n" +
                "4. Sort products by price\n" +
                "5. Sort Products by quantity\n" +
                "6. Make a purchase\n" +
                "7. Back\n";

        input += "7\n";

        expected += "Would you like to:\n" +
                "1. View available products\n" +
                "2. View purchase history\n" +
                "3. View store statistics\n" +
                "4. Go Back\n";

        input += "2\n";

        expected += "Product name: Mega Rainbow Ball\n" +
                "Store Name: Gepettos\n" +
                "Description: A giant rainbow bouncy ball perfect for outdoor activities :)\n" +
                "Price when bought: 12.99\n" +
                "Quantity bought: 5\n" +
                "Total Cost: 64.95\n" +
                "Would you like to export purchase history?\n" +
                "1. Yes\n" +
                "2. No\n";

        // exit out of entire program
        input += "2\n";

        expected += "Would you like to:\n" +
                "1. View available products\n" +
                "2. View purchase history\n" +
                "3. View store statistics\n" +
                "4. Go Back\n";
        input += "4\n";

        expected += "Welcome back aysusaglam!\n" +
                "What next?\n" +
                "1. Edit my account\n" +
                "2. Delete my account\n" +
                "3. View more options :P\n" +
                "4. Logout\n";

        input += "4\n";

        expected += "Logging out...\n" +
                "\n" +
                "Welcome to Butter, your one stop for everything!\n" +
                "What would you like to do today?\n" +
                "1. Create new account\n" +
                "2. Login to an existing account\n" +
                "3. Exit marketplace\n";

        input += "3\n";

        expected += "See you soon :D\n";

        receiveInput(input);
        Marketplace.main(new String[0]);

        String actual = getOutput();
        actual = actual.replace("\r\n", "\n");

        Assert.assertEquals("Verify that the output matches!", expected, actual);
    }
    
    @Test(timeout = 1000)
    public void testAccDelete() {
        String input = "";
        String expected = "";

        // login to seller
        expected += "Welcome to Butter, your one stop for everything!\n" +
                "What would you like to do today?\n" +
                "1. Create new account\n" +
                "2. Login to an existing account\n" +
                "3. Exit marketplace\n";
        input += "2\n";

        expected += "Enter username: \n";
        input += "seojiwon04\n";

        expected += "Enter password: \n";
        input += "seojiwon04\n";

        expected += "Login as:\n" +
                "1. Seller\n" +
                "2. Customer\n";

        input += "1\n";

        expected += "Welcome back seojiwon04!\n" +
                "What next?\n" +
                "1. Edit my account\n" +
                "2. Delete my account\n" +
                "3. View more options :P\n" +
                "4. Logout\n";

        // delete acc

        input += "2\n";

        expected += "Please enter your password to confirm: \n";
        input += "seojiwon04\n";

        expected += "Your account was deleted successfully!\n" +
                "\n" +
                "Welcome to Butter, your one stop for everything!\n" +
                "What would you like to do today?\n" +
                "1. Create new account\n" +
                "2. Login to an existing account\n" +
                "3. Exit marketplace\n";

        // attempt login to deleted seller 
        
        input += "2\n";
        expected += "Enter username: \n";
        
        input += "seojiwon04\n";
        expected += "Enter password: \n";
        input += "seojiwon04\n";
        
        expected += "Login as:\n" +
                "1. Seller\n" +
                "2. Customer\n";
        
        input += "1\n";
        
        expected += "Invalid login!\n" +
                "Welcome to Butter, your one stop for everything!\n" +
                "What would you like to do today?\n" +
                "1. Create new account\n" +
                "2. Login to an existing account\n" +
                "3. Exit marketplace\n";
        
        // login to customer and view purchase history
        
        input += "2\n";

        expected += "Enter username: \n";

        input += "aysusaglam\n";
        expected += "Enter password: \n";
        input += "aysu\n";

        expected += "Login as:\n" +
                "1. Seller\n" +
                "2. Customer\n";

        input += "2\n";
        
        expected += "Welcome back aysusaglam!\n" +
                "What next?\n" +
                "1. Edit my account\n" +
                "2. Delete my account\n" +
                "3. View more options :P\n" +
                "4. Logout\n";
        
        input += "3\n";
        
        expected += "Would you like to:\n" +
                "1. View available products\n" +
                "2. View purchase history\n" +
                "3. View store statistics\n" +
                "4. Go Back\n";
        
        input += "2\n";

        expected += "Product name: 4pk Wood Coaster Set\n" +
                "Store Name: Target\n" +
                "Description: Acacia wood coaster set includes 4 round coasters\n" +
                "Price when bought: 12.00\n" +
                "Quantity bought: 3\n" +
                "Total Cost: 36.00\n" +
                "Would you like to export purchase history?\n" +
                "1. Yes\n" +
                "2. No\n";
        
        input += "2\n";
        
        expected += "Would you like to:\n" +
                "1. View available products\n" +
                "2. View purchase history\n" +
                "3. View store statistics\n" +
                "4. Go Back\n";
        
        input += "4\n";
        
        expected += "Welcome back aysusaglam!\n" +
                "What next?\n" +
                "1. Edit my account\n" +
                "2. Delete my account\n" +
                "3. View more options :P\n" +
                "4. Logout\n";
        
        input += "4\n";
        
        expected += "Logging out...\n" +
                "\n" +
                "Welcome to Butter, your one stop for everything!\n" +
                "What would you like to do today?\n" +
                "1. Create new account\n" +
                "2. Login to an existing account\n" +
                "3. Exit marketplace\n";
        
        input += "3\n";
        
        expected += "See you soon :D\n";

        receiveInput(input);
        Marketplace.main(new String[0]);

        String actual = getOutput();
        actual = actual.replace("\r\n", "\n");

        Assert.assertEquals("Verify that the output matches!", expected, actual);
    }

    @Test(timeout = 1000)
    public void testOne() {
        String input = "5\n1\nnewSeller\nemail@gmail.com\n1234\n1\n2\nnewSeller\n1234\n1\n3\n1\nnewStore\n" +
                "2\n1\n2\n4\nnewstoreImport.txt\n5\nnewstoreExport.txt\n6\n5\n4\n4\n3\n";
        String expected = "Welcome to Butter, your one stop for everything!\n" +
                "What would you like to do today?\n" +
                "1. Create new account\n" + "2. Login to an existing account\n" +
                "3. Exit marketplace\n" + "Welcome to Butter, your one stop for everything!\n" +
                "What would you like to do today?\n" + "1. Create new account\n" +
                "2. Login to an existing account\n" + "3. Exit marketplace\n" +
                "Enter a unique username: \n" + "Enter your email: \n" +
                "Enter a (strong) password: \n" + "What best describes you?\n" +
                "1. Seller\n" + "2. Customer\n" + "New account created successfully!\n" +
                "Welcome to Butter, your one stop for everything!\n" + "What would you like to do today?\n" +
                "1. Create new account\n" + "2. Login to an existing account\n" +
                "3. Exit marketplace\n" + "Enter username: \n" + "Enter password: \n" + "Login as:\n" +
                "1. Seller\n" + "2. Customer\n" + "Hello! What next?\n" + "1. Edit my account\n" +
                "2. Delete my account\n" + "3. View more options :P\n" + "4. Logout\n" +
                "What would you like to do?\n" + "1. Create a new store\n" +
                "2. Edit, delete, or view existing store\n" + "3. View list of sales\n" + "4. Go back\n" +
                "Enter the store name:\n" + "Store was successfully created!\n" +
                "What would you like to do?\n" + "1. Create a new store\n" +
                "2. Edit, delete, or view existing store\n" + "3. View list of sales\n" +
                "4. Go back\n" + "Choose a store:\n" + "1. newStore\n" + "What would you like to do?\n" +
                "1. Edit store name\n" + "2. Create/edit products in store\n" + "3. Delete store\n" +
                "4. View store stats\n" + "5. Go back\n" + "What would you like to do?\n" +
                "1. Create new unique product\n" + "2. Edit product\n" + "3. Delete product\n" +
                "4. Import products from file\n" + "5. Export product data to file\n" +
                "6. Go back\n" + "Enter filename that you want to import products from:\n" +
                "Products successfully imported into store!\n" + "What would you like to do?\n" +
                "1. Create new unique product\n" + "2. Edit product\n" + "3. Delete product\n" +
                "4. Import products from file\n" + "5. Export product data to file\n" +
                "6. Go back\n" + "Enter filename that you want to export product data to:\n" + "All product data has been successfully exported to!\n" +
                "What would you like to do?\n" + "1. Create new unique product\n" + "2. Edit product\n" +
                "3. Delete product\n" + "4. Import products from file\n" + "5. Export product data to file\n" +
                "6. Go back\n" + "What would you like to do?\n" +
                "1. Edit store name\n" + "2. Create/edit products in store\n" + "3. Delete store\n" +
                "4. View store stats\n" + "5. Go back\n" + "What would you like to do?\n" +
                "1. Create a new store\n" + "2. Edit, delete, or view existing store\n" + "3. View list of sales\n" +
                "4. Go back\n" + "Hello! What next?\n" + "1. Edit my account\n" + "2. Delete my account\n" +
                "3. View more options :P\n" + "4. Logout\n" + "Logging out...\n" + "\n" +
                "Welcome to Butter, your one stop for everything!\n" + "What would you like to do today?\n" +
                "1. Create new account\n" + "2. Login to an existing account\n" +
                "3. Exit marketplace\n" + "See you soon :D\n";

        receiveInput(input);
        Marketplace.main(new String[0]);

        String actual = getOutput();
        actual = actual.replace("\r\n", "\n");

        Assert.assertEquals("Verify that the output matches!", expected, actual);
    }

    @Test(timeout = 1000)
    public void testThree() {
        String input = "2\nmoliveme\nmoliveme\n1\n3\n2\n2\n4\n1\n2\n3\n5\n4\n4\n2\naysusaglam\naysu\n2\n3\n3\n" +
                "1\n2\n3\n4\n4\n3\n";
        String expected = "Welcome to Butter, your one stop for everything!\n" +
                "What would you like to do today?\n" +
                "1. Create new account\n" +
                "2. Login to an existing account\n" +
                "3. Exit marketplace\n" + "Enter username: \n" + "Enter password: \n" + "Login as:\n" +
                "1. Seller\n" +
                "2. Customer\n" + "Hello! What next?\n" +
                "1. Edit my account\n" +
                "2. Delete my account\n" +
                "3. View more options :P\n" +
                "4. Logout\n" + "What would you like to do?\n" +
                "1. Create a new store\n" +
                "2. Edit, delete, or view existing store\n" +
                "3. View list of sales\n" +
                "4. Go back \n" + "Choose a store:\n" +
                "1. Target\n" +
                "2. Costco Wholesale\n" +
                "3. Walmart\n" + "What would you like to do?\n" +
                "1. Edit store name\n" +
                "2. Create/edit products in store\n" +
                "3. Delete store\n" +
                "4. View store stats\n" +
                "5. Go back \n" + "-------------\n" +
                "Number of items each customer has bought from this store:\n" +
                "aashna-gupta2 - 0 purchases\n" +
                "aysusaglam - 2 purchases\n" +
                "-------------\n" +
                "Number of sales each product has made from this store:\n" +
                "MacBook Pro - 2 sales\n" +
                "Bose Smart Soundbar 700 - 0 sales\n" +
                "Dyson Cyclone V10 Animal + Cordless Vacuum Cleaner - 0 sales\n" + "Would you like to:\n" +
                "1. Sort and view data from high to low\n" +
                "2. Sort and view data from low to high\n" +
                "3. Go back \n" + "-------------\n" +
                "Number of items each customer has bought from this store:\n" +
                "aysusaglam - 2 purchases\n" +
                "aashna-gupta2 - 0 purchases\n" +
                "-------------\n" +
                "Number of sales each product has made from this store:\n" +
                "MacBook Pro - 2 sales\n" +
                "Bose Smart Soundbar 700 - 0 sales\n" +
                "Dyson Cyclone V10 Animal + Cordless Vacuum Cleaner - 0 sales\n" +
                "Would you like to:\n" +
                "1. Sort and view data from high to low\n" +
                "2. Sort and view data from low to high\n" +
                "3. Go back \n" + "-------------\n" +
                "Number of items each customer has bought from this store:\n" +
                "aashna-gupta2 - 0 purchases\n" +
                "aysusaglam - 2 purchases\n" +
                "-------------\n" +
                "Number of sales each product has made from this store:\n" +
                "Bose Smart Soundbar 700 - 0 sales\n" +
                "Dyson Cyclone V10 Animal + Cordless Vacuum Cleaner - 0 sales\n" +
                "MacBook Pro - 2 sales\n" +
                "Would you like to:\n" +
                "1. Sort and view data from high to low\n" +
                "2. Sort and view data from low to high\n" +
                "3. Go back \n" + "What would you like to do?\n" +
                "1. Edit store name\n" +
                "2. Create/edit products in store\n" +
                "3. Delete store\n" +
                "4. View store stats\n" +
                "5. Go back \n" + "What would you like to do?\n" +
                "1. Create a new store\n" +
                "2. Edit, delete, or view existing store\n" +
                "3. View list of sales\n" +
                "4. Go back \n" + "Hello! What next?\n" +
                "1. Edit my account\n" +
                "2. Delete my account\n" +
                "3. View more options :P\n" +
                "4. Logout\n" + "Logging out...\n" +
                "\n" +
                "Welcome to Butter, your one stop for everything!\n" +
                "What would you like to do today?\n" +
                "1. Create new account\n" +
                "2. Login to an existing account\n" +
                "3. Exit marketplace\n" + "Enter username: \n" + "Enter password: \n" + "Login as:\n" +
                "1. Seller\n" +
                "2. Customer\n" + "Hello! What next?\n" +
                "1. Edit my account\n" +
                "2. Delete my account\n" +
                "3. View more options :P\n" +
                "4. Logout\n" + "Would you like to:\n" +
                "1. View available products\n" +
                "2. View purchase history\n" +
                "3. View store statistics\n" +
                "4. Go Back \n" + "-------------\n" +
                "Stores by number of products sold:\n" +
                "Target - 4 products available\n" +
                "Seller name: moliveme\n" +
                "Costco Wholesale - 3 products available\n" +
                "Seller name: moliveme\n" +
                "Walmart - 4 products available\n" +
                "Seller name: moliveme\n" +
                "Gepettos - 4 products available\n" +
                "Seller name: seojiwon04\n" +
                "Toys R Us - 4 products available\n" +
                "Seller name: seojiwon04\n" +
                "Michaels - 4 products available\n" +
                "Seller name: seojiwon04\n" +
                "-------------\n" +
                "Stores by number of products purchased from that store\n" +
                "0 purchases made from Target\n" +
                "Seller name: moliveme\n" +
                "1 purchase made from Costco Wholesale\n" +
                "Seller name: moliveme\n" +
                "0 purchases made from Walmart\n" +
                "Seller name: moliveme\n" +
                "0 purchases made from Gepettos\n" +
                "Seller name: seojiwon04\n" +
                "0 purchases made from Toys R Us\n" +
                "Seller name: seojiwon04\n" +
                "0 purchases made from Michaels\n" +
                "Seller name: seojiwon04\n" +
                "Would you like to:\n" +
                "1. Sort and view data from high to low\n" +
                "2. Sort and view data from low to high\n" +
                "3. Go back \n" + "-------------\n" +
                "Stores by number of products sold:\n" +
                "Target - 4 products available\n" +
                "Seller name: moliveme\n" +
                "Walmart - 4 products available\n" +
                "Seller name: moliveme\n" +
                "Gepettos - 4 products available\n" +
                "Seller name: seojiwon04\n" +
                "Toys R Us - 4 products available\n" +
                "Seller name: seojiwon04\n" +
                "Michaels - 4 products available\n" +
                "Seller name: seojiwon04\n" +
                "Costco Wholesale - 3 products available\n" +
                "Seller name: moliveme\n" +
                "-------------\n" +
                "Stores by number of products purchased from that store\n" +
                "1 purchase made from Costco Wholesale\n" +
                "Seller name: moliveme\n" +
                "0 purchases made from Target\n" +
                "Seller name: moliveme\n" +
                "0 purchases made from Walmart\n" +
                "Seller name: moliveme\n" +
                "0 purchases made from Gepettos\n" +
                "Seller name: seojiwon04\n" +
                "0 purchases made from Toys R Us\n" +
                "Seller name: seojiwon04\n" +
                "0 purchases made from Michaels\n" +
                "Seller name: seojiwon04\n" +
                "Would you like to:\n" +
                "1. Sort and view data from high to low\n" +
                "2. Sort and view data from low to high\n" +
                "3. Go back \n" + "-------------\n" +
                "Stores by number of products sold:\n" +
                "Costco Wholesale - 3 products available\n" +
                "Seller name: moliveme\n" +
                "Target - 4 products available\n" +
                "Seller name: moliveme\n" +
                "Walmart - 4 products available\n" +
                "Seller name: moliveme\n" +
                "Gepettos - 4 products available\n" +
                "Seller name: seojiwon04\n" +
                "Toys R Us - 4 products available\n" +
                "Seller name: seojiwon04\n" +
                "Michaels - 4 products available\n" +
                "Seller name: seojiwon04\n" +
                "-------------\n" +
                "Stores by number of products purchased from that store\n" +
                "0 purchases made from Target\n" +
                "Seller name: moliveme\n" +
                "0 purchases made from Walmart\n" +
                "Seller name: moliveme\n" +
                "0 purchases made from Gepettos\n" +
                "Seller name: seojiwon04\n" +
                "0 purchases made from Toys R Us\n" +
                "Seller name: seojiwon04\n" +
                "0 purchases made from Michaels\n" +
                "Seller name: seojiwon04\n" +
                "1 purchases made from Costco Wholesale\n" +
                "Seller name: moliveme\n" +
                "Would you like to:\n" +
                "1. Sort and view data from high to low\n" +
                "2. Sort and view data from low to high\n" +
                "3. Go back \n" + "Would you like to:\n" +
                "1. View available products\n" +
                "2. View purchase history\n" +
                "3. View store statistics\n" +
                "4. Go Back \n" + "Hello! What next?\n" +
                "1. Edit my account\n" +
                "2. Delete my account\n" +
                "3. View more options :P\n" +
                "4. Logout\n" + "Logging out...\n" +
                "\n" +
                "Welcome to Butter, your one stop for everything!\n" +
                "What would you like to do today?\n" +
                "1. Create new account\n" +
                "2. Login to an existing account\n" +
                "3. Exit marketplace\n" + "See you soon :D\n";

        receiveInput(input);
        Marketplace.main(new String[0]);

        String actual = getOutput();
        actual = actual.replace("\r\n", "\n");

        Assert.assertEquals("Verify that the output matches!", expected, actual);
    }
}
