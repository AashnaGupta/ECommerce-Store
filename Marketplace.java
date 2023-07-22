import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

/**
 * @author Aashna, Jiwon, Aysu, Mridu
 * @version 1.0
 */

public class Marketplace {

    // files and arrays associated with those files
    private static final String sellerFile = "sellerFile.txt";
    private static ArrayList<Seller> sellerArray;
    private static final String customerFile = "customerFile.txt";
    private static ArrayList<Customer> customerArray;
    private static final String purchaseLog = "purchaseLog.txt";
    private static ArrayList<Purchase> allPurchases;

    // output strings
    private static final String WELCOME_MESSAGE = "Welcome to Butter, your one stop for everything!\n" +
            "What would you like to do today?";
    private static final String LOGIN_OR_CREATE = """
            1. Create new account
            2. Login to an existing account
            3. Exit marketplace""";
    private static final String LOGIN_SELLER_OR_CUSTOMER = """
            Login as:
            1. Seller
            2. Customer""";
    private static final String SELLER_OR_CUSTOMER = """
            What best describes you?
            1. Seller
            2. Customer""";

    private static final String INVALID_OPTION = "Please choose a valid option!";
    private static final String USERNAME_PROMPT = "Enter username: ";
    private static final String PASSWORD_PROMPT = "Enter password: ";
    private static final String INVALID_LOGIN = "Invalid login!";
    private static final String EDIT_OR_DELETE_ACC_OR_SURF = """
            What next?
            1. Edit my account
            2. Delete my account
            3. View more options :P
            4. Logout""";
    private static final String CONFIRM_ACTION = "Please enter your password to confirm: "; //?? can use this while buying products etc too?
    private static final String CONFIRM_PASSWORD_CHANGE = "Please enter your old password to confirm: ";
    private static final String ACTION_UNALLOWED = "Wrong password! Try again later :/";
    private static final String EDIT_ACC_OPTIONS = """
            What would you like to do?
            1. Edit username
            2. Update email
            3. Change password
            4. Go back""";
    private static final String SELLER_OPTIONS = """
            What would you like to do?
            1. Create a new store
            2. Edit, delete, or view existing store
            3. View list of sales
            4. Go back""";
    private static final String SEPARATOR = "-------------";

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        while (true) {

            // load data from files into ArrayLists
            // a. call method that fills in sellerArray (in seller, fills stores and in store, fills availableProducts), customerArray
            // (use sellerFile, allStoresFile, storeProductsFile, customerFile)
            readSellers();
            readCustomers();

            // b. fill in all the purchase data for stores and customers
            // (use purchaseLog)
            loadPurchasesFromFile();
            loadPurchaseDataForStoresAndCustomers();

            // outer loop with 3 options:
            // 1. create new account
            // 2. login to existing account
            // 3. exit program (loop ends)

            // welcome
            System.out.println(WELCOME_MESSAGE);
            System.out.println(LOGIN_OR_CREATE);

            String choice = scan.nextLine();

            if (choice.equals("1")) { // create new user

                // a. declare variables needed to create user
                String usernameInput;
                String emailInput;
                String passwordInput;
                boolean isSeller;
                boolean validUsername;

                // b. check if username is unique, else ask for
                // username till unique
                do {
                    System.out.println("Enter a unique username: ");
                    usernameInput = scan.nextLine();
                    validUsername = isValidUsername(usernameInput);

                    if (!validUsername) {
                        System.out.println("That username is taken!");
                    }
                } while (!validUsername);

                // c. get other login details
                do {
                    System.out.println("Enter your email: ");
                    emailInput = scan.nextLine();

                    if (!isValidString(emailInput)) {
                        System.out.println("Please enter a valid email!");
                    }

                } while (!isValidString(emailInput));

                do {
                    System.out.println("Enter a (strong) password: ");
                    passwordInput = scan.nextLine();

                    if (!isValidString(passwordInput)) {
                        System.out.println("Please enter a valid password!");
                    }

                } while (!isValidString(passwordInput));

                // d. loop to make sure user chooses the right role
                // (seller and customer are the only valid roles)
                while (true) {
                    System.out.println(SELLER_OR_CUSTOMER);
                    choice = scan.nextLine();
                    if (choice.equals("1")) {
                        isSeller = true;
                        break;
                    } else if (choice.equals("2")) {
                        isSeller = false;
                        break;
                    } else {
                        System.out.println(INVALID_OPTION);
                    }
                }

                // e. create user and add to respective arraylist
                if (isSeller) {
                    sellerArray.add(new Seller(usernameInput, emailInput, passwordInput));
                } else {
                    customerArray.add(new Customer(usernameInput, emailInput, passwordInput));
                }
                System.out.println("New account created successfully!");
                saveData();

            } else if (choice.equals("2")) {           // login to existing account

                // validate login
                User user;

                System.out.println(USERNAME_PROMPT);
                String username = scan.nextLine();
                System.out.println(PASSWORD_PROMPT);
                String password = scan.nextLine();

                while (true) {
                    System.out.println(LOGIN_SELLER_OR_CUSTOMER);
                    choice = scan.nextLine();
                    if (choice.equals("1")) {
                        user = validateSellerLogin(username, password); // returns null if no valid acc
                        break;
                    } else if (choice.equals("2")) {
                        user = validateCustomerLogin(username, password); // returns null if no valid acc
                        break;
                    } else {
                        System.out.println(INVALID_OPTION);
                    }
                }

                if (user == null) {
                    System.out.println(INVALID_LOGIN);
                } else {

                    // loop with 4 options:
                    // 1. Edit account
                    // 2. Delete account
                    // 3. View more options :P
                    // 4. Logout
                    while (true) {

                        // hello + list 4 options
                        System.out.printf("Welcome back %s!\n", user.getUsername());
                        System.out.println(EDIT_OR_DELETE_ACC_OR_SURF);

                        choice = scan.nextLine();

                        if (choice.equals("1")) {          // edit account

                            String newUsername;
                            String newEmail;
                            String newPassword;

                            // loop with 4 options:
                            // 1. Edit username
                            // 2. Update email
                            // 3. Change password
                            // 4. Go back
                            while (true) {
                                System.out.println(EDIT_ACC_OPTIONS);
                                choice = scan.nextLine();

                                if (choice.equals("4")) {  // exit
                                    break;

                                } else if (choice.equals("1")) {   // edit username

                                    System.out.println("Enter your new username: ");
                                    newUsername = scan.nextLine();
                                    boolean newUsernameIsValid = isValidUsername(newUsername);

                                    if (!newUsernameIsValid) {
                                        System.out.println("Sorry, that username is taken!");
                                    } else {
                                        System.out.print(CONFIRM_ACTION);
                                        String confirm = scan.nextLine();
                                        if (!confirm.equals(user.getPassword())) {
                                            System.out.println(ACTION_UNALLOWED);
                                        } else {
                                            if (user instanceof Customer) {
                                                for (Purchase purchase : allPurchases) {
                                                    if (purchase.getCustomerUsername().equals(user.getUsername())) {
                                                        purchase.setCustomerUsername(newUsername);
                                                    }
                                                }
                                            }
                                            user.setUsername(newUsername);
                                            if (user instanceof Customer) {
                                                loadPurchaseDataForStoresAndCustomers();
                                            }
                                            System.out.println("Username changed successfully!\n");
                                        }
                                    }

                                } else if (choice.equals("2")) { // edit email

                                    System.out.println("Enter your new email: ");
                                    newEmail = scan.nextLine();

                                    System.out.print(CONFIRM_ACTION);
                                    String confirm = scan.nextLine();
                                    if (!confirm.equals(user.getPassword())) {
                                        System.out.println(ACTION_UNALLOWED);
                                    } else {
                                        user.setEmail(newEmail);
                                        System.out.println("Email ID updated successfully!\n");
                                    }
                                } else if (choice.equals("3")) {  // edit password

                                    System.out.println("Enter your new password: ");
                                    newPassword = scan.nextLine();

                                    System.out.println(CONFIRM_PASSWORD_CHANGE);
                                    String confirm = scan.nextLine();
                                    if (!confirm.equals(user.getPassword())) {
                                        System.out.println(ACTION_UNALLOWED);
                                    } else {
                                        user.setPassword(newPassword);
                                        System.out.println("Password was changed successfully!\n");
                                    }
                                } else {
                                    System.out.println(INVALID_OPTION);
                                }
                            }

                        } else if (choice.equals("2")) {   // delete account
                            System.out.println(CONFIRM_ACTION);
                            String pwd = scan.nextLine();

                            if (pwd.equals(user.getPassword())) {
                                deleteUser(user);
                                System.out.println("Your account was deleted successfully!\n");
                                break;
                            } else {
                                System.out.println(ACTION_UNALLOWED);
                            }

                        } else if (choice.equals("4")) {   // logout
                            System.out.println("Logging out...\n");
                            saveData();
                            savePurchases();
                            break;

                        } else if (choice.equals("3")) {   // seller/customer specific operations

                            if (user instanceof Seller) {
                                Seller seller = (Seller) user;

                                // loop with 4 options:
                                // 1) create store
                                // 2) edit/delete store
                                // 3) view sales
                                // 4) exit
                                while (true) {

                                    // 4 seller options
                                    System.out.println(SELLER_OPTIONS);

                                    String option = scan.nextLine();
                                    if (option.equals("4")) {
                                        break;

                                    } else if (option.equals("1")) {                       // create new store
                                        System.out.println("Enter the store name:");
                                        String name = scan.nextLine();
                                        seller.createStore(name);

                                        System.out.println("Store was successfully created!");

                                    } else if (option.equals("2")) {                       // edit/delete store
                                        if (seller.getStores().size() == 0) {
                                            System.out.println("There are no stores to edit!");
                                        } else {
                                            //prompt user to choose a store to edit
                                            String storesStr = "";
                                            for (int i = 0; i < seller.getStores().size(); i++) {
                                                storesStr += String.format("%d. %s\n", i + 1, seller.getStores().get(i).getStoreName());
                                            }
                                            Store store;
                                            do {
                                                try {
                                                    System.out.println("Choose a store:");
                                                    System.out.print(storesStr);
                                                    int storeChoice = Integer.parseInt(scan.nextLine());
                                                    store = seller.getStores().get(storeChoice - 1);
                                                    break;
                                                } catch (Exception e) {
                                                    System.out.println(INVALID_OPTION);
                                                }
                                            } while (true);

                                            // loop with 5 options
                                            while (true) {
                                                System.out.println("What would you like to do?\n" +
                                                        "1. Edit store name\n" +
                                                        "2. Create/edit products in store\n" +
                                                        "3. Delete store\n" +
                                                        "4. View store stats\n" +
                                                        "5. Go back");
                                                option = scan.nextLine();

                                                if (option.equals("5")) {               // exit
                                                    break;

                                                } else if (option.equals("1")) {        // edit store name
                                                    System.out.println("Enter new store name:");
                                                    String newStoreName = scan.nextLine();
                                                    for (Purchase purchase : allPurchases) {
                                                        if (purchase.getStoreName().equals(store.getStoreName())) {
                                                            purchase.setStoreName(newStoreName);
                                                        }
                                                    }
                                                    seller.editStore(store, newStoreName);
                                                    loadPurchaseDataForStoresAndCustomers();

                                                    System.out.println("Store name was successfully edited!");

                                                } else if (option.equals("2")) {        // create/edit products or delete product

                                                    // loop with 4 options:
                                                    // 1. create a product
                                                    // 2. edit product
                                                    // 3. delete product
                                                    // 4. import products from file
                                                    // 5. export product data to a file
                                                    // 6. exit
                                                    while (true) {
                                                        System.out.println("What would you like to do?\n" +
                                                                "1. Create new unique product\n" +
                                                                "2. Edit product\n" +
                                                                "3. Delete product\n" +
                                                                "4. Import products from file\n" +
                                                                "5. Export product data to file\n" +
                                                                "6. Go back");
                                                        option = scan.nextLine();

                                                        if (option.equals("6")) {               // exit
                                                            break;

                                                        } else if (option.equals("1")) {               // create new product
                                                            System.out.println("Enter product name:");
                                                            String productName = scan.nextLine();
                                                            System.out.println("Enter description:");
                                                            String description = scan.nextLine();
                                                            System.out.println("Enter quantity");
                                                            int quantity = scan.nextInt();
                                                            scan.nextLine();
                                                            System.out.println("Enter new price");
                                                            double price = scan.nextDouble();
                                                            store.createProduct(productName, description, quantity, price);

                                                            System.out.println("Product was successfully created!");

                                                        } else if (option.equals("4")) {  //import products from file
                                                            System.out.println("Enter filename that you want to import products from:");
                                                            String filename = scan.nextLine();
                                                            try {
                                                                store.importProducts(filename);
                                                                System.out.println("Products successfully imported into store!");
                                                            } catch (IOException | ArrayIndexOutOfBoundsException e) {
                                                                System.out.println(e.getMessage());
                                                                System.out.println("Import failed");
                                                            }
                                                        } else if (option.equals("5")) {
                                                            System.out.println("Enter filename that you want to export product data to:");
                                                            String filename = scan.nextLine();
                                                            try {
                                                                store.exportProducts(filename);
                                                                System.out.println("All product data has been successfully exported to!");
                                                            } catch (Exception e) {
                                                                System.out.println(e.getMessage());
                                                                System.out.println("Export failed");
                                                            }
                                                        } else if (option.equals("2") || option.equals("3")) {                                // edit/delete product

                                                            //prompt user to choose a product
                                                            String products = "";
                                                            for (int i = 0; i < store.getAvailableProducts().size(); i++) {
                                                                products += String.format("%d. %s%n", i + 1, store.getAvailableProducts().get(i).getProductName());
                                                            }

                                                            Product product;
                                                            do {
                                                                try {
                                                                    System.out.println("Choose a product:");
                                                                    System.out.print(products);
                                                                    int productChoice = Integer.parseInt(scan.nextLine());
                                                                    product = store.getAvailableProducts().get(productChoice - 1);
                                                                    break;
                                                                } catch (Exception e) {
                                                                    System.out.println(INVALID_OPTION);
                                                                }
                                                            } while (true);


                                                            if (option.equals("2")) {           // edit product

                                                                // loop with 5 options:
                                                                // 1. Edit product name
                                                                // 2. Edit product description
                                                                // 3. Change quantity available
                                                                // 4. Change product's price
                                                                // 5. Exit
                                                                while (true) {

                                                                    System.out.println("Choose a task:\n" +
                                                                            "1. Edit product name\n" +
                                                                            "2. Edit product description\n" +
                                                                            "3. Change quantity available\n" +
                                                                            "4. Change product's price\n" +
                                                                            "5. Go back");

                                                                    choice = scan.nextLine();

                                                                    if (choice.equals("5")) {          // exit
                                                                        break;

                                                                    } else if (choice.equals("1")) {   // edit product name
                                                                        System.out.println("Enter new name");
                                                                        String name = scan.nextLine();
                                                                        for (Purchase purchase : allPurchases) {
                                                                            if (purchase.getProductName().equals(product.getProductName())) {
                                                                                purchase.setProductName(name);
                                                                            }
                                                                        }
                                                                        product.setProductName(name);
                                                                        loadPurchaseDataForStoresAndCustomers();

                                                                    } else if (choice.equals("2")) {  // edit product description
                                                                        System.out.println("Enter new description:");
                                                                        String description = scan.nextLine();
                                                                        for (Purchase purchase : allPurchases) {
                                                                            if (purchase.getProductName().equals(product.getProductName())) {
                                                                                purchase.setDescription(description);
                                                                            }
                                                                        }
                                                                        product.setDescription(description);
                                                                        loadPurchaseDataForStoresAndCustomers();

                                                                    } else if (choice.equals("3")) {   // edit quantity
                                                                        System.out.println("Enter new quantity");
                                                                        int quantity = Integer.parseInt(scan.nextLine());
                                                                        product.setQuantity(quantity);

                                                                    } else if (choice.equals("4")) {   // edit price
                                                                        System.out.println("Enter new price");
                                                                        double price = Double.parseDouble(scan.nextLine());
                                                                        product.setPrice(price);

                                                                    } else {
                                                                        System.out.println(INVALID_OPTION);
                                                                    }

                                                                    store.saveProducts();
                                                                }

                                                            } else if (option.equals("3")) {           // delete product
                                                                Product temp = product;
                                                                allPurchases.removeIf(purchase -> purchase.getProductName().equals(temp.getProductName()));
                                                                store.deleteProduct(product.getProductName());
                                                                loadPurchaseDataForStoresAndCustomers();
                                                            }
                                                        } else {
                                                            System.out.println(INVALID_OPTION);
                                                        }
                                                    }
                                                } else if (option.equals("3")) {// delete store
                                                    Store temp = store;
                                                    allPurchases.removeIf(purchase -> purchase.getStoreName().equals(temp.getStoreName()));
                                                    seller.deleteStore(store);
                                                    loadPurchaseDataForStoresAndCustomers();
                                                    System.out.println("Store successfully deleted");
                                                    break;

                                                } else if (option.equals("4")) {
                                                    //arrays to show a list of customers with the number of items that they have purchased
                                                    String[] customerUsernames = new String[customerArray.size()];
                                                    int[] purchasesMadeByUser = new int[customerUsernames.length];

                                                    for (int i = 0; i < customerArray.size(); i++) {
                                                        customerUsernames[i] = customerArray.get(i).getUsername();
                                                    }
                                                    for (int i = 0; i < purchasesMadeByUser.length; i++) {
                                                        purchasesMadeByUser[i] = 0;
                                                    }

                                                    //arrays to show a list of products by sales
                                                    String[] productNames = new String[store.getAvailableProducts().size()];
                                                    int[] salesByProduct = new int[productNames.length];

                                                    for (int i = 0; i < store.getAvailableProducts().size(); i++) {
                                                        productNames[i] = store.getAvailableProducts().get(i).getProductName();
                                                    }
                                                    for (int i = 0; i < salesByProduct.length; i++) {
                                                        salesByProduct[i] = 0;
                                                    }

                                                    for (Purchase purchase : store.getAllPurchases()) {
                                                        for (int i = 0; i < customerUsernames.length; i++) {
                                                            if (purchase.getCustomerUsername().equals(customerUsernames[i])) {
                                                                purchasesMadeByUser[i] += purchase.getQuantity();
                                                            }
                                                        }

                                                        for (int i = 0; i < productNames.length; i++) {
                                                            if (purchase.getProductName().equals(productNames[i])) {
                                                                salesByProduct[i] += purchase.getQuantity();
                                                            }
                                                        }
                                                    }

                                                    System.out.println(SEPARATOR);
                                                    System.out.println("Number of items each customer has bought from this store:");

                                                    for (int i = 0; i < customerUsernames.length; i++) {
                                                        if (purchasesMadeByUser[i] == 1) {
                                                            System.out.println(customerUsernames[i] + " - " + purchasesMadeByUser[i] + " purchase");
                                                        } else {
                                                            System.out.println(customerUsernames[i] + " - " + purchasesMadeByUser[i] + " purchases");
                                                        }
                                                    }

                                                    System.out.println(SEPARATOR);
                                                    System.out.println("Number of sales each product has made from this store:");

                                                    for (int i = 0; i < productNames.length; i++) {
                                                        if (salesByProduct[i] == 1) {
                                                            System.out.println(productNames[i] + " - " + salesByProduct[i] + " sale");
                                                        } else {
                                                            System.out.println(productNames[i] + " - " + salesByProduct[i] + " sales");
                                                        }
                                                    }

                                                    while (true) {
                                                        System.out.println("Would you like to:\n" +
                                                                "1. Sort and view data from high to low\n" +
                                                                "2. Sort and view data from low to high\n" +
                                                                "3. Go back ");
                                                        String sort = scan.nextLine();
                                                        if (sort.equals("3")) {
                                                            break;
                                                        } else if (sort.equals("1")) {
                                                            boolean sorting = true;
                                                            String tempCustomerUsername;
                                                            int tempCustomerPurchases;

                                                            while (sorting) {
                                                                sorting = false;
                                                                for (int i = 0; i < purchasesMadeByUser.length - 1; i++) {
                                                                    if (purchasesMadeByUser[i] < purchasesMadeByUser[i + 1]) {
                                                                        tempCustomerUsername = customerUsernames[i];
                                                                        tempCustomerPurchases = purchasesMadeByUser[i];

                                                                        purchasesMadeByUser[i] = purchasesMadeByUser[i + 1];
                                                                        customerUsernames[i] = customerUsernames[i + 1];
                                                                        purchasesMadeByUser[i + 1] = tempCustomerPurchases;
                                                                        customerUsernames[i + 1] = tempCustomerUsername;
                                                                        sorting = true;
                                                                    }
                                                                }
                                                            }

                                                            sorting = true;
                                                            String tempProductName;
                                                            int tempProductSales;

                                                            while (sorting) {
                                                                sorting = false;
                                                                for (int i = 0; i < salesByProduct.length - 1; i++) {
                                                                    if (salesByProduct[i] < salesByProduct[i + 1]) {
                                                                        tempProductName = productNames[i];
                                                                        tempProductSales = salesByProduct[i];

                                                                        salesByProduct[i] = salesByProduct[i + 1];
                                                                        productNames[i] = productNames[i + 1];
                                                                        salesByProduct[i + 1] = tempProductSales;
                                                                        productNames[i + 1] = tempProductName;
                                                                        sorting = true;
                                                                    }
                                                                }
                                                            }

                                                            //printing after sorting
                                                            System.out.println(SEPARATOR);
                                                            System.out.println("Number of items each customer has bought from this store:");

                                                            for (int i = 0; i < customerUsernames.length; i++) {
                                                                if (purchasesMadeByUser[i] == 1) {
                                                                    System.out.println(customerUsernames[i] + " - " + purchasesMadeByUser[i] + " purchase");
                                                                } else {
                                                                    System.out.println(customerUsernames[i] + " - " + purchasesMadeByUser[i] + " purchases");
                                                                }
                                                            }

                                                            System.out.println(SEPARATOR);
                                                            System.out.println("Number of sales each product has made from this store:");

                                                            for (int i = 0; i < productNames.length; i++) {
                                                                if (salesByProduct[i] == 1) {
                                                                    System.out.println(productNames[i] + " - " + salesByProduct[i] + " sale");
                                                                } else {
                                                                    System.out.println(productNames[i] + " - " + salesByProduct[i] + " sales");
                                                                }
                                                            }
                                                        } else if (sort.equals("2")) {
                                                            boolean sorting = true;
                                                            String tempCustomerUsername;
                                                            int tempCustomerPurchases;

                                                            while (sorting) {
                                                                sorting = false;
                                                                for (int i = 0; i < purchasesMadeByUser.length - 1; i++) {
                                                                    if (purchasesMadeByUser[i] > purchasesMadeByUser[i + 1]) {
                                                                        tempCustomerUsername = customerUsernames[i];
                                                                        tempCustomerPurchases = purchasesMadeByUser[i];

                                                                        purchasesMadeByUser[i] = purchasesMadeByUser[i + 1];
                                                                        customerUsernames[i] = customerUsernames[i + 1];
                                                                        purchasesMadeByUser[i + 1] = tempCustomerPurchases;
                                                                        customerUsernames[i + 1] = tempCustomerUsername;
                                                                        sorting = true;
                                                                    }
                                                                }
                                                            }

                                                            sorting = true;
                                                            String tempProductName;
                                                            int tempProductSales;

                                                            while (sorting) {
                                                                sorting = false;
                                                                for (int i = 0; i < salesByProduct.length - 1; i++) {
                                                                    if (salesByProduct[i] > salesByProduct[i + 1]) {
                                                                        tempProductName = productNames[i];
                                                                        tempProductSales = salesByProduct[i];

                                                                        salesByProduct[i] = salesByProduct[i + 1];
                                                                        productNames[i] = productNames[i + 1];
                                                                        salesByProduct[i + 1] = tempProductSales;
                                                                        productNames[i + 1] = tempProductName;
                                                                        sorting = true;
                                                                    }
                                                                }
                                                            }

                                                            //printing after sorting low to high
                                                            System.out.println(SEPARATOR);
                                                            System.out.println("Number of items each customer has bought from this store:");

                                                            for (int i = 0; i < customerUsernames.length; i++) {
                                                                if (purchasesMadeByUser[i] == 1) {
                                                                    System.out.println(customerUsernames[i] + " - " + purchasesMadeByUser[i] + " purchase");
                                                                } else {
                                                                    System.out.println(customerUsernames[i] + " - " + purchasesMadeByUser[i] + " purchases");
                                                                }
                                                            }

                                                            System.out.println(SEPARATOR);
                                                            System.out.println("Number of sales each product has made from this store:");

                                                            for (int i = 0; i < productNames.length; i++) {
                                                                if (salesByProduct[i] == 1) {
                                                                    System.out.println(productNames[i] + " - " + salesByProduct[i] + " sale");
                                                                } else {
                                                                    System.out.println(productNames[i] + " - " + salesByProduct[i] + " sales");
                                                                }
                                                            }
                                                        } else {
                                                            System.out.println(INVALID_OPTION);
                                                        }
                                                    }
                                                } else {
                                                    System.out.println(INVALID_OPTION);
                                                }
                                            }
                                        }
                                    } else if (option.equals("3")) {                                   // view sales
                                        if (seller.getStores().size() == 0) {
                                            System.out.println("There are no stores!");
                                        } else {

                                            //prompt user to choose a store to view sales of
                                            String stores = "";
                                            for (int i = 0; i < seller.getStores().size(); i++) {
                                                stores += String.format("%d. %s%n", i + 1, seller.getStores().get(i).getStoreName());
                                            }
                                            Store store;
                                            do {
                                                try {
                                                    System.out.println("Choose a store:");
                                                    System.out.print(stores);
                                                    int storeChoice = Integer.parseInt(scan.nextLine());
                                                    store = seller.getStores().get(storeChoice - 1);
                                                    break;
                                                } catch (Exception e) {
                                                    System.out.println(INVALID_OPTION);
                                                }
                                            } while (true);
                                            if (store.getAllPurchases().size() == 0) {
                                                System.out.println("No sale history to show");
                                            } else {
                                                String result = store.viewAllPurchases();
                                                System.out.print(result);
                                                ;
                                            }
                                        }
                                    } else {
                                        System.out.println(INVALID_OPTION);
                                    }
                                }
                            }

                            //instanceof Customer
                            if (user instanceof Customer) {
                                //Creates ArrayList of all available products
                                Customer customer = (Customer) user;
                                ArrayList<Product> allProducts = new ArrayList<>();
                                for (Seller seller : sellerArray) {
                                    for (Store store : seller.getStores()) {
                                        allProducts.addAll(store.getAvailableProducts());
                                    }
                                }
                                while (true) {
                                    System.out.println("Would you like to:\n" +
                                            "1. View available products\n" +
                                            "2. View purchase history\n" +
                                            "3. View store statistics\n" +
                                            "4. Go Back");
                                    String customerChoice = scan.nextLine();

                                    if (customerChoice.equals("1")) {

                                        System.out.println(SEPARATOR);
                                        for (Product product : allProducts) {
                                            System.out.println(product.getProductDataLanding());
                                        }

                                        while (true) {
                                            //View available products
                                            //Landing page
                                            System.out.println("Would you like to:\n" +
                                                    "1. Search products by name\n" +
                                                    "2. Search products by store\n" +
                                                    "3. Search products by description\n" +
                                                    "4. Sort products by price\n" +
                                                    "5. Sort Products by quantity\n" +
                                                    "6. Make a purchase\n" +
                                                    "7. Back");
                                            String sortChoice = scan.nextLine();
                                            if (sortChoice.equals("1")) {
                                                System.out.println("Enter search term (by product name):");
                                                String term = scan.nextLine();
                                                ArrayList<Product> byProductName = new ArrayList<>(filterByProductName(term, allProducts));
                                                if (byProductName.size() == 0) {
                                                    System.out.println("No matching results!");
                                                } else {
                                                    System.out.println(SEPARATOR);
                                                    for (Product product : byProductName) {
                                                        System.out.println(product.getProductDataLanding());
                                                    }
                                                }
                                            } else if (sortChoice.equals("2")) {
                                                System.out.println("Enter search term (by store name):");
                                                String term = scan.nextLine();
                                                ArrayList<Product> byStoreName = new ArrayList<>(filterByStoreName(term, allProducts));
                                                if (byStoreName.size() == 0) {
                                                    System.out.println("No matching results!");
                                                } else {
                                                    System.out.println(SEPARATOR);
                                                    for (Product product : byStoreName) {
                                                        System.out.println(product.getProductDataLanding());
                                                    }
                                                }
                                            } else if (sortChoice.equals("3")) {
                                                System.out.println("Enter search term (by product description):");
                                                String term = scan.nextLine();
                                                ArrayList<Product> byDescription = new ArrayList<>(filterByDescription(term, allProducts));

                                                if (byDescription.size() == 0) {
                                                    System.out.println("No matching results!");
                                                } else {
                                                    System.out.println(SEPARATOR);
                                                    for (Product product : byDescription) {
                                                        System.out.println(product.getProductDataByDesc());
                                                    }
                                                }
                                            } else if (sortChoice.equals("4")) {
                                                System.out.println("Please select:\n" +
                                                        "1. High to Low\n" +
                                                        "2. Low to High");
                                                int highOrLow;
                                                while (true) {
                                                    highOrLow = Integer.parseInt(scan.nextLine());
                                                    if (highOrLow == 1 || highOrLow == 2) {
                                                        break;
                                                    } else {
                                                        System.out.println(INVALID_OPTION); //prompt user to make a new choice, loop again
                                                    }
                                                }

                                                if (highOrLow == 1) {
                                                    System.out.println("Sorting products by price (high to low)...");
                                                } else {
                                                    System.out.println("Sorting products by price (low to high)...");
                                                }

                                                ArrayList<Product> byPrice = new ArrayList<>(sortPrice(highOrLow, allProducts));
                                                System.out.println(SEPARATOR);
                                                for (Product product : byPrice) {
                                                    System.out.println(product.getProductDataLanding());
                                                }
                                            } else if (sortChoice.equals("5")) {
                                                System.out.println("Please select:\n" +
                                                        "1. High to Low\n" +
                                                        "2. Low to High");
                                                int highOrLow;
                                                while (true) {
                                                    highOrLow = Integer.parseInt(scan.nextLine());
                                                    if (highOrLow == 1 || highOrLow == 2) {
                                                        break;
                                                    } else {
                                                        System.out.println(INVALID_OPTION); //prompt user to make a new choice, loop again
                                                    }
                                                }

                                                if (highOrLow == 1) {
                                                    System.out.println("Sorting products by quantity (high to low)...");
                                                } else {
                                                    System.out.println(("Sorting products by quantity (low to high)..."));
                                                }

                                                ArrayList<Product> byQuantity = new ArrayList<>(sortQuantity(highOrLow, allProducts));
                                                System.out.println(SEPARATOR);
                                                for (Product product : byQuantity) {
                                                    System.out.println(product.getProductDataByQuantity());
                                                }
                                            } else if (sortChoice.equals("6")) {
                                                String allProductsInfo = "";
                                                for (int i = 0; i < allProducts.size(); i++) {
                                                    allProductsInfo += String.format("%d) %s%n", i + 1, allProducts.get(i).getProductName());
                                                }
                                                System.out.println("Select a product:");
                                                System.out.println(allProductsInfo);
                                                int selectProduct = 0;

                                                try {
                                                    selectProduct = Integer.parseInt(scan.nextLine());
                                                    if (selectProduct > allProducts.size()) {
                                                        System.out.println(INVALID_OPTION);
                                                    }
                                                } catch (NumberFormatException e) {
                                                    System.out.println(INVALID_OPTION);
                                                }

                                                Product product = allProducts.get(selectProduct - 1);

                                                System.out.println(SEPARATOR);
                                                System.out.println(product.displayProductPage());

                                                System.out.println("Would you like to purchase this product?\n" +
                                                        "1. Yes\n" +
                                                        "2. Exit");
                                                int toBuy;
                                                while (true) {
                                                    toBuy = Integer.parseInt(scan.nextLine());
                                                    if (toBuy == 1 || toBuy == 2) {
                                                        break;
                                                    } else {
                                                        //prompt user to make a new choice, loop again
                                                        System.out.println(INVALID_OPTION);
                                                    }
                                                }
                                                if (toBuy == 1) {
                                                    Store buyStore = null;
                                                    for (Seller seller : sellerArray) {
                                                        for (Store store : seller.getStores()) {
                                                            if (store.getStoreName().equals(product.getStoreName())) {
                                                                buyStore = store;
                                                            }
                                                        }
                                                    }

                                                    while (true) {
                                                        try {
                                                            System.out.println("How much would you like to purchase?");
                                                            int quantity;
                                                            quantity = Integer.parseInt(scan.nextLine());
                                                            Purchase purchase = customer.makeAPurchase(buyStore, product, quantity);
                                                            allPurchases.add(purchase);
                                                            break;
                                                        } catch (NumberFormatException e) {
                                                            System.out.println(INVALID_OPTION);
                                                        } catch (IllegalArgumentException e) {
                                                            System.out.println(e.getMessage());
                                                        }
                                                    }

                                                } else {
                                                    break;
                                                }
                                            } else if (sortChoice.equals("7")) {
                                                break;
                                            } else {
                                                System.out.println(INVALID_OPTION);
                                            }
                                        }
                                    } else if (customerChoice.equals("2")) {
                                        //View purchase history
                                        ArrayList<Purchase> purchaseHist = customer.getAllPurchases();
                                        if (purchaseHist.size() == 0) {
                                            System.out.println("No purchase history");
                                        } else {
                                            for (Purchase purchase : purchaseHist) {
                                                System.out.println(purchase.printDataForCustomer());
                                            }
                                            //if customer purchase history exists, prompt customer if they want to export purchase history
                                            while (true) {
                                                System.out.println("Would you like to export purchase history?\n1. Yes\n2. No");
                                                String purchaseHistoryExport = scan.nextLine();
                                                //export customer's purchase history data
                                                if (purchaseHistoryExport.equals("1")) {
                                                    System.out.println("Enter a filename to export purchase history: ");
                                                    String filename = scan.nextLine();
                                                    try {
                                                        customer.exportPurchases(filename);
                                                        System.out.println("Purchase data successfully exported!");
                                                    } catch (Exception e) {
                                                        System.out.println(e.getMessage());
                                                        System.out.println("Purchase data export failed");
                                                    }
                                                    break;
                                                } else if (purchaseHistoryExport.equals("2")) {
                                                    //customer would not like to export data
                                                    break;
                                                } else {
                                                    //any other invalid option is entered other than 1 or 2, loop back
                                                    System.out.println(INVALID_OPTION);
                                                }
                                            }
                                        }
                                    } else if (customerChoice.equals("3")) {
                                        ArrayList<String> storeNames = new ArrayList<>();
                                        ArrayList<String> sellerNameByStore = new ArrayList<>();
                                        ArrayList<Integer> productsAvailableByStore = new ArrayList<>();
                                        ArrayList<Integer> purchasesByStore = new ArrayList<>();

                                        for (Seller seller : sellerArray) {
                                            for (Store store : seller.getStores()) {
                                                storeNames.add(store.getStoreName());
                                                sellerNameByStore.add(seller.getUsername());
                                                productsAvailableByStore.add(store.getAvailableProducts().size());
                                                int purchasesMadeByCustomerCount = 0;
                                                for (Purchase purchase : store.getAllPurchases()) {
                                                    if (purchase.getCustomerUsername().equals(customer.getUsername())) {
                                                        purchasesMadeByCustomerCount++;
                                                    }
                                                }
                                                purchasesByStore.add(purchasesMadeByCustomerCount);
                                            }
                                        }

                                        //printing dashboard
                                        System.out.println(SEPARATOR);
                                        System.out.println("Stores by number of products sold:");
                                        for (int i = 0; i < storeNames.size(); i++) {
                                            if (productsAvailableByStore.get(i) == 1) {
                                                System.out.println(storeNames.get(i) + " - " +
                                                        productsAvailableByStore.get(i) + " product available");
                                            } else {
                                                System.out.println(storeNames.get(i) + " - " +
                                                        productsAvailableByStore.get(i) + " products available");
                                            }
                                            System.out.println("Seller name: " + sellerNameByStore.get(i));
                                        }

                                        System.out.println(SEPARATOR);
                                        System.out.println("Stores by number of products purchased from that store");
                                        for (int i = 0; i < storeNames.size(); i++) {
                                            if (purchasesByStore.get(i) == 1) {
                                                System.out.println(purchasesByStore.get(i) +
                                                        " purchase made from " + storeNames.get(i));
                                            } else {
                                                System.out.println(purchasesByStore.get(i) +
                                                        " purchases made from " + storeNames.get(i));
                                            }
                                            System.out.println("Seller name: " + sellerNameByStore.get(i));
                                        }
                                        while (true) {
                                            System.out.println("Would you like to:\n" +
                                                    "1. Sort and view data from high to low\n" +
                                                    "2. Sort and view data from low to high\n" +
                                                    "3. Go back ");
                                            String sort = scan.nextLine();
                                            if (sort.equals("3")) {
                                                break;
                                            } else if (sort.equals("1")) {
                                                //sort and print data from high to low
                                                boolean sorting = true;
                                                String tempStoreName;
                                                String tempSellerName;
                                                int tempProductsAvailable;
                                                int tempPurchasesFromStore;
                                                while (sorting) {
                                                    sorting = false;
                                                    for (int i = 0; i < storeNames.size() - 1; i++) {
                                                        if (productsAvailableByStore.get(i) < productsAvailableByStore.get(i + 1)) {
                                                            tempStoreName = storeNames.get(i);
                                                            tempSellerName = sellerNameByStore.get(i);
                                                            tempProductsAvailable = productsAvailableByStore.get(i);
                                                            tempPurchasesFromStore = purchasesByStore.get(i);

                                                            storeNames.set(i, storeNames.get(i + 1));
                                                            sellerNameByStore.set(i, sellerNameByStore.get(i + 1));
                                                            productsAvailableByStore.set(i, productsAvailableByStore.get(i + 1));
                                                            purchasesByStore.set(i, purchasesByStore.get(i + 1));

                                                            storeNames.set(i + 1, tempStoreName);
                                                            sellerNameByStore.set(i + 1, tempSellerName);
                                                            productsAvailableByStore.set(i + 1, tempProductsAvailable);
                                                            purchasesByStore.set(i + 1, tempPurchasesFromStore);
                                                            sorting = true;
                                                        }
                                                    }
                                                }

                                                System.out.println(SEPARATOR);
                                                System.out.println("Stores by number of products sold:");
                                                for (int i = 0; i < storeNames.size(); i++) {
                                                    if (productsAvailableByStore.get(i) == 1) {
                                                        System.out.println(storeNames.get(i) + " - " +
                                                                productsAvailableByStore.get(i) + " product available");
                                                    } else {
                                                        System.out.println(storeNames.get(i) + " - " +
                                                                productsAvailableByStore.get(i) + " products available");
                                                    }
                                                    System.out.println("Seller name: " + sellerNameByStore.get(i));
                                                }

                                                sorting = true;
                                                while (sorting) {
                                                    sorting = false;
                                                    for (int i = 0; i < storeNames.size() - 1; i++) {
                                                        if (purchasesByStore.get(i) < purchasesByStore.get(i + 1)) {
                                                            tempStoreName = storeNames.get(i);
                                                            tempSellerName = sellerNameByStore.get(i);
                                                            tempProductsAvailable = productsAvailableByStore.get(i);
                                                            tempPurchasesFromStore = purchasesByStore.get(i);

                                                            storeNames.set(i, storeNames.get(i + 1));
                                                            sellerNameByStore.set(i, sellerNameByStore.get(i + 1));
                                                            productsAvailableByStore.set(i, productsAvailableByStore.get(i + 1));
                                                            purchasesByStore.set(i, purchasesByStore.get(i + 1));

                                                            storeNames.set(i + 1, tempStoreName);
                                                            sellerNameByStore.set(i + 1, tempSellerName);
                                                            productsAvailableByStore.set(i + 1, tempProductsAvailable);
                                                            purchasesByStore.set(i + 1, tempPurchasesFromStore);
                                                            sorting = true;
                                                        }
                                                    }
                                                }

                                                System.out.println(SEPARATOR);
                                                System.out.println("Stores by number of products purchased from that store");
                                                for (int i = 0; i < storeNames.size(); i++) {
                                                    if (purchasesByStore.get(i) == 1) {
                                                        System.out.println(purchasesByStore.get(i) +
                                                                " purchase made from " + storeNames.get(i));
                                                    } else {
                                                        System.out.println(purchasesByStore.get(i) +
                                                                " purchases made from " + storeNames.get(i));
                                                    }
                                                    System.out.println("Seller name: " + sellerNameByStore.get(i));
                                                }

                                            } else if (sort.equals("2")) {
                                                boolean sorting = true;
                                                String tempStoreName;
                                                String tempSellerName;
                                                int tempProductsAvailable;
                                                int tempPurchasesFromStore;
                                                while (sorting) {
                                                    sorting = false;
                                                    for (int i = 0; i < storeNames.size() - 1; i++) {
                                                        if (productsAvailableByStore.get(i) > productsAvailableByStore.get(i + 1)) {
                                                            tempStoreName = storeNames.get(i);
                                                            tempSellerName = sellerNameByStore.get(i);
                                                            tempProductsAvailable = productsAvailableByStore.get(i);
                                                            tempPurchasesFromStore = purchasesByStore.get(i);

                                                            storeNames.set(i, storeNames.get(i + 1));
                                                            sellerNameByStore.set(i, sellerNameByStore.get(i + 1));
                                                            productsAvailableByStore.set(i, productsAvailableByStore.get(i + 1));
                                                            purchasesByStore.set(i, purchasesByStore.get(i + 1));

                                                            storeNames.set(i + 1, tempStoreName);
                                                            sellerNameByStore.set(i + 1, tempSellerName);
                                                            productsAvailableByStore.set(i + 1, tempProductsAvailable);
                                                            purchasesByStore.set(i + 1, tempPurchasesFromStore);
                                                            sorting = true;
                                                        }
                                                    }
                                                }

                                                System.out.println(SEPARATOR);
                                                System.out.println("Stores by number of products sold:");
                                                for (int i = 0; i < storeNames.size(); i++) {
                                                    if (productsAvailableByStore.get(i) == 1) {
                                                        System.out.println(storeNames.get(i) + " - " +
                                                                productsAvailableByStore.get(i) + " product available");
                                                    } else {
                                                        System.out.println(storeNames.get(i) + " - " +
                                                                productsAvailableByStore.get(i) + " products available");
                                                    }
                                                    System.out.println("Seller name: " + sellerNameByStore.get(i));
                                                }

                                                sorting = true;
                                                while (sorting) {
                                                    sorting = false;
                                                    for (int i = 0; i < storeNames.size() - 1; i++) {
                                                        if (purchasesByStore.get(i) > purchasesByStore.get(i + 1)) {
                                                            tempStoreName = storeNames.get(i);
                                                            tempSellerName = sellerNameByStore.get(i);
                                                            tempProductsAvailable = productsAvailableByStore.get(i);
                                                            tempPurchasesFromStore = purchasesByStore.get(i);

                                                            storeNames.set(i, storeNames.get(i + 1));
                                                            sellerNameByStore.set(i, sellerNameByStore.get(i + 1));
                                                            productsAvailableByStore.set(i, productsAvailableByStore.get(i + 1));
                                                            purchasesByStore.set(i, purchasesByStore.get(i + 1));

                                                            storeNames.set(i + 1, tempStoreName);
                                                            sellerNameByStore.set(i + 1, tempSellerName);
                                                            productsAvailableByStore.set(i + 1, tempProductsAvailable);
                                                            purchasesByStore.set(i + 1, tempPurchasesFromStore);
                                                            sorting = true;
                                                        }
                                                    }
                                                }

                                                System.out.println(SEPARATOR);
                                                System.out.println("Stores by number of products purchased from that store");
                                                for (int i = 0; i < storeNames.size(); i++) {
                                                    System.out.println(purchasesByStore.get(i) +
                                                            " purchases made from " + storeNames.get(i));
                                                    System.out.println("Seller name: " + sellerNameByStore.get(i));
                                                }

                                            } else {
                                                System.out.println(INVALID_OPTION);
                                            }
                                        }

                                    } else if (customerChoice.equals("4")) {
                                        break;
                                    } else {
                                        System.out.println(INVALID_OPTION);
                                    }
                                }
                            }
                        } else {
                            System.out.println(INVALID_OPTION);
                        }
                    }
                }
            } else if (choice.equals("3")) {           // exit
                System.out.println("See you soon :D");
                break;
            }
        }
    }

    public static Seller validateSellerLogin(String username, String password) {
        for (Seller seller : sellerArray) {
            if (seller.getUsername().equals(username) && seller.getPassword().equals(password)) {
                return seller;
            }
        }
        return null;
    }

    public static Customer validateCustomerLogin(String username, String password) {
        for (Customer customer : customerArray) {
            if (customer.getUsername().equals(username) && customer.getPassword().equals(password)) {
                return customer;
            }
        }
        return null;
    }

    public static void deleteUser(User user) {

        if (user instanceof Seller seller) {
            //remove purchase from allpurchases arraylist if storename equals purchase storename
            for (Store store : seller.getStores()) {
                allPurchases.removeIf(purchase -> store.getStoreName().equals(purchase.getStoreName()));
            }
            sellerArray.remove(seller);

        } else {
            Customer customer = (Customer) user;
            //remove purchase from allpurchases arraylist if customerUsername equals customer's username
            allPurchases.removeIf(purchase -> purchase.getCustomerUsername().equals(customer.getUsername()));
            customerArray.remove(customer);
        }
        loadPurchaseDataForStoresAndCustomers();
        savePurchases();
        saveData();
    }

    public static boolean isValidString(String string) {
        return !string.contains(",");
    }

    public static boolean isValidUsername(String username) {
        if (username.contains(",")) {
            return false;
        }
        for (Seller seller : sellerArray) {
            if (username.equals(seller.getUsername())) {
                return false;
            }
        }
        for (Customer customer : customerArray) {
            if (username.equals(customer.getUsername())) {
                return false;
            }
        }
        return true;
    }

    public static ArrayList<Product> filterByProductName(String productName, ArrayList<Product> allProducts) {
        ArrayList<Product> filtered = new ArrayList<>();
        for (Product product : allProducts) {
            if (product.getProductName().toLowerCase().contains(productName.toLowerCase())) {
                filtered.add(product);
            }
        }
        return filtered;
    }

    public static ArrayList<Product> filterByStoreName(String storeName, ArrayList<Product> allProducts) {
        ArrayList<Product> filtered = new ArrayList<>();
        for (Product product : allProducts) {
            if (product.getStoreName().toLowerCase().contains(storeName.toLowerCase())) {
                filtered.add(product);
            }
        }
        return filtered;
    }

    public static ArrayList<Product> filterByDescription(String description, ArrayList<Product> allProducts) {
        ArrayList<Product> filtered = new ArrayList<>();
        for (Product product : allProducts) {
            if (product.getDescription().toLowerCase().contains(description.toLowerCase())) {
                filtered.add(product);
            }
        }
        return filtered;
    }

    public static ArrayList<Product> sortPrice(int choice, ArrayList<Product> allProducts) {
        ArrayList<Product> filtered = new ArrayList<>(allProducts);
        boolean sorting = true;
        Product placeHolder;
        if (choice == 1) { //high to low
            while (sorting) {
                sorting = false;
                for (int i = 0; i < filtered.size() - 1; i++) {
                    if (filtered.get(i).getPrice() < filtered.get(i + 1).getPrice()) {
                        placeHolder = filtered.get(i);
                        filtered.set(i, filtered.get(i + 1));
                        filtered.set(i + 1, placeHolder);
                        sorting = true;
                    }
                }
            }
        } else { //low to high
            while (sorting) {
                sorting = false;
                for (int i = 0; i < filtered.size() - 1; i++) {
                    if (filtered.get(i).getPrice() > filtered.get(i + 1).getPrice()) {
                        placeHolder = filtered.get(i);
                        filtered.set(i, filtered.get(i + 1));
                        filtered.set(i + 1, placeHolder);
                        sorting = true;
                    }
                }
            }
        }
        return filtered;
    }

    public static ArrayList<Product> sortQuantity(int choice, ArrayList<Product> allProducts) {
        ArrayList<Product> filtered = new ArrayList<>(allProducts);
        boolean sorting = true;
        Product placeHolder;
        if (choice == 1) { //high to low
            while (sorting) {
                sorting = false;
                for (int i = 0; i < filtered.size() - 1; i++) {
                    if (filtered.get(i).getQuantity() < filtered.get(i + 1).getQuantity()) {
                        placeHolder = filtered.get(i);
                        filtered.set(i, filtered.get(i + 1));
                        filtered.set(i + 1, placeHolder);
                        sorting = true;
                    }
                }
            }
        } else { //low to high
            while (sorting) {
                sorting = false;
                for (int i = 0; i < filtered.size() - 1; i++) {
                    if (filtered.get(i).getQuantity() > filtered.get(i + 1).getQuantity()) {
                        placeHolder = filtered.get(i);
                        filtered.set(i, filtered.get(i + 1));
                        filtered.set(i + 1, placeHolder);
                        sorting = true;
                    }
                }
            }
        }
        return filtered;
    }

    public static void readSellers() {
        try {
            sellerArray = new ArrayList<>();
            File f = new File(sellerFile);
            boolean fileCreated = f.createNewFile();
            if (!fileCreated) {
                FileReader fr = new FileReader(f);
                BufferedReader bfr = new BufferedReader(fr);

                String seller = bfr.readLine();

                while (seller != null) {
                    String[] split = seller.split(",");
                    Seller newSeller = new Seller(split[0], split[1], split[2]);

                    File allStores = new File(newSeller.getAllStoresFile()); //username.txt
                    allStores.createNewFile();
                    FileReader allStoresReader = new FileReader(allStores);
                    BufferedReader allStoresBFR = new BufferedReader(allStoresReader);
                    String storeName = allStoresBFR.readLine(); //reading store names

                    while (storeName != null) {
                        Store store = new Store(storeName);
                        File storeProducts = new File(store.getStoreProductsFile());
                        storeProducts.createNewFile();

                        FileReader storeProductsReader = new FileReader(storeProducts);
                        BufferedReader storeProductsBFR = new BufferedReader(storeProductsReader);

                        String productData = storeProductsBFR.readLine(); //read product data

                        while (productData != null) {
                            String[] productDataSplit = productData.split(",(?=([^\"]|\"[^\"]*\")*$)");
                            Product product = new Product(productDataSplit[0].substring(1, productDataSplit[0].length() - 1),
                                    productDataSplit[1].substring(1, productDataSplit[1].length() - 1),
                                    productDataSplit[2].substring(1, productDataSplit[2].length() - 1),
                                    Integer.parseInt(productDataSplit[3]), Double.parseDouble(productDataSplit[4]));
                            store.addProduct(product);
                            productData = storeProductsBFR.readLine();
                        }

                        newSeller.addStore(store); //add completed store to stores arraylist for seller
                        storeName = allStoresBFR.readLine(); //move onto next store
                    }
                    sellerArray.add(newSeller);
                    seller = bfr.readLine(); //onto next seller
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readCustomers() {
        try {
            customerArray = new ArrayList<>();
            File f = new File(customerFile);
            boolean fileCreated = f.createNewFile();
            if (!fileCreated) {
                FileReader fr = new FileReader(f);
                BufferedReader bfr = new BufferedReader(fr);

                String customerData = bfr.readLine();

                while (customerData != null) {
                    String[] split = customerData.split(",(?=([^\"]|\"[^\"]*\")*$)");
                    Customer customer = new Customer(split[0], split[1], split[2]);
                    customerArray.add(customer);
                    customerData = bfr.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadPurchasesFromFile() {
        try {
            allPurchases = new ArrayList<>();
            File f = new File(purchaseLog);
            boolean fileCreated = f.createNewFile();
            if (!fileCreated) {
                FileReader fr = new FileReader(f);
                BufferedReader bfr = new BufferedReader(fr);

                String purchaseData = bfr.readLine();
                while (purchaseData != null) {
                    String[] split = purchaseData.split(",(?=([^\"]|\"[^\"]*\")*$)");
                    Purchase purchase = new Purchase(split[0].substring(1, split[0].length() - 1),
                            split[1].substring(1, split[1].length() - 1), split[2].substring(1, split[2].length() - 1),
                            Integer.parseInt(split[3]), Double.parseDouble(split[4]),
                            split[5], Double.parseDouble(split[6]));
                    allPurchases.add(purchase);
                    purchaseData = bfr.readLine();
                }
                bfr.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading in purchases!");
        }
    }

    public static void loadPurchaseDataForStoresAndCustomers() {
        for (Seller seller : sellerArray) {
            for (Store store : seller.getStores()) {
                store.resetPurchases(); //new method in store
                if (!allPurchases.isEmpty()) {
                    for (Purchase purchase : allPurchases) {
                        if (store.getStoreName().equals(purchase.getStoreName())) {
                            store.addPurchase(purchase);
                        }
                    }
                }
            }
        }
        for (Customer customer : customerArray) {
            customer.resetPurchases();
            if (!allPurchases.isEmpty()) {
                for (Purchase purchase : allPurchases) {
                    if (customer.getUsername().equals(purchase.getCustomerUsername())) {
                        customer.addPurchase(purchase);
                    }
                }
            }
        }
    }

    public static void savePurchases() {
        try {
            File f = new File(purchaseLog);
            FileOutputStream fos = new FileOutputStream(f, false);
            PrintWriter pw = new PrintWriter(fos);

            for (Purchase purchase : allPurchases) {
                pw.println(purchase.toString());
            }

            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveData() {
        try {
            File seller = new File(sellerFile);

            FileOutputStream sellerFOS = new FileOutputStream(seller, false);
            PrintWriter sellerWriter = new PrintWriter(sellerFOS);

            for (Seller s : sellerArray) {
                s.saveData();
                sellerWriter.println(s.toString());
            }
            sellerWriter.close();

            File customer = new File(customerFile);

            FileOutputStream customerFOS = new FileOutputStream(customer, false);
            PrintWriter customerWriter = new PrintWriter(customerFOS);
            for (Customer c : customerArray) {
                customerWriter.println(c.toString());
            }
            customerWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
