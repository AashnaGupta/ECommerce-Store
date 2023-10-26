# Project 4 - Ecommerce Shopping Platform

## **Instructions on how to compile and run the project**

Note: You are not required to create any files while compiling/running the project. All necesary text files and java files are included in this repository. <br />

### a)    How to run the menu of the marketplace interface

Step 1: Compile all java classes 
<br />Step 2: Run main method housed in Marketplace.java
<br />Step 3: Give inputs into the console as prompted
<br />Step 4: Outputs will be displayed through the console

### b)    How to run junit tests to test Marketplace.java (the main menu interface)

1. Run MarketplaceTest.java
<br />There are 4 tests in MarketplaceTest.java. More information about these tests can be found in Detailed descriptions of each class > 7. Marketplace.java > b) Testing

### c)    How to run tests to test the other six classes (User.java, Purchase.java, Product.java, Customer.java, Seller.java, Store.java)

1. Run &lt;class name&gt;LocalTest.java for each class, where &lt;class name&gt; is replaced with the name of the class being tested.
<br />For example, User.java is tested by running the main method housed in UserLocalTest.java.<br />


## **Detailed descriptions of each class**

This includes for every class a) functionality and relationship to other classes, b) testing, c) edit history (to be moved outside this repository before submitting project 5)

### 1. User.java

#### a) Functionality and relationship to other classes

##### Fields

| Modifiers | Type | Name | Description |
| ------------- | ------------- | ------------- | ------------- |
| `private` | `String`  | `username` | stores user's username (a unique identifier) |
| `private`  | `String`  | `email` | stores user's email ID |
| `private`  | `String`  | `password` | stores user's account password |

##### Constructor(s)

| Modifiers | Parameters | Description |
| ------------- | ------------- | ------------- |
| `public` | `String username, String email, String password` | sets values of fields username, email, and password using input arguments |

##### Methods

| Modifiers | Return type | Name | Parameters | Description |
| ------------- | ------------- | ------------- | ------------- | ------------- |
| `public` | `String`  | `getUsername` | `None` | return's user's account username |
| `public`  | `String`  | `getEmail` | `None` | returns user's email ID |
| `public`  | `String`  | `getPassword` | `None` | returns user's account password |
| `public` | `void`  | `setUsername` | `String username` | sets user's username to input argument |
| `public`  | `void`  | `setEmail` | `String email` | sets user's email ID to input argument |
| `public`  | `void`  | `setPassword` | `String password` | sets user's password to input argument |
| `public`  | `String`  | `toString` | `None` | returns "username,email,password" to be written into the sellerFile or customerFile as a line |

#### b) Testing
#### UserLocalTest.java Description
    UserLocalTest.java acts as a testing class for User.java by implementing a main method. 
    Within this main method a User is first instantiated. The only method from User.java being 
    tested within this class is the 'toString()' method.
    
#### c) Edit History

| Edit number (unique, serialized) | Name of method created/deleted/edited | Edit number of previous version of method | Final version of method in this edit |
| ------------- | ------------- | ------------- | ------------- |
| 1 | `toString()` | - | _copy paste edited method here_ |
| 2 | `setPassword()` | - | _copy paste edited method here_ |
| 3 | `toString()` | 1 | _copy paste edited method here_ |
| 4 | `toString()` | 3 | _copy paste edited method here_ |
| 5 | `setPassword()` | 2 | _copy paste edited method here_ |

### 2. Customer.java

#### a) Functionality and relationship to other classes

Note: Customer.java extends User.java

##### Fields

| Modifiers | Type | Name | Description |
| ------------- | ------------- | ------------- | ------------- |
| `private` | `ArrayList<Purchase>`  | `allPurchases` | stores all the purchases made by the customer |

##### Constructor(s)

| Modifiers | Parameters | Description |
| ------------- | ------------- | ------------- |
| `public` | `String username, String email, String password` | sets values of fields username, email, and password using input arguments and initializes allPurchases to a new arraylist |

##### Methods

| Modifiers | Return type | Name | Parameters | Description |
| ------------- | ------------- | ------------- | ------------- | ------------- |
| `public` | `void`  | `addPurchase` | `Purchase purchase` | adds a purchase to allPurchases |
| `public`  | `Purchase`  | `makeAPurchase` | `Store store, Product product, int quantity` | processes user buying a product. throws IllegalArgumentException if amount requested for buying is more than amount of product available. adds product to allPurchases, updates product's quantity |
| `public`  | `void`  | `writeToPurchase` | `Purchase purchase` | appends purchase to "purchaseLog.txt" (file declared within Marketplace.java) |
| `public` | `void`  | `resetPurchases` | `None` | resets allPurchases to blank arraylist |
| `public`  | `ArrayList<Purchase>`  | `getAllPurchases` | `None` | returns allPurchases |
| `public`  | `void`  | `exportPurchases` | `String filename` | writes contents of allPurchases into a new file with name = input filename. throws FileAlreadyExistsException if file already exists. |

#### b) Testing
#### CustomerLocalTest.java Description
    CustomerLocalTest.java acts as a testing class for Customer.java by implementing a main method. 
    Within this main method a customer is first instantiated. The first methods that will be tested 
    are 'makeAPurchase()' as well as 'addPurchase()' and 'writeToPurchase()', which are both implemented 
    within 'makeAPurchase()'. In order to test these methods a Store and Product must be instantiated as well. 
    In order to verify 'addPurchase()' and 'makeAPurchase()', the program compares ArrayList sizes to indicate 
    whether or not a Purchase has been added. Then the program reads through the "purchaseLog.txt" to confirm
    that changes have been reflected on the file.
    Following this, the program tests 'exportPurchases()' by reading through the indicated export 
    destination("ExportTest.csv"), and confirming whether or not the lines of this file reflect the contents of the ArrayList.
    
#### c) Edit History

| Edit number (unique, serialized) | Name of method created/deleted/edited | Edit number of previous version of method | Final version of method in this edit |
| ------------- | ------------- | ------------- | ------------- |
| 1 | `toString()` | - | _copy paste edited method here_ |
| 2 | `setPassword()` | - | _copy paste edited method here_ |
| 3 | `toString()` | 1 | _copy paste edited method here_ |
| 4 | `toString()` | 3 | _copy paste edited method here_ |
| 5 | `setPassword()` | 2 | _copy paste edited method here_ |    

### 3. Seller.java

#### a) Functionality and relationship to other classes

Note: Seller.java extends User.java

##### Fields

| Modifiers | Type | Name | Description |
| ------------- | ------------- | ------------- | ------------- |
| `private` | `ArrayList<Store>`  | `stores` | holds all the stores owned by the seller |
| `private`  | `String`  | `allStoresFile` | name of file that holds all the stores owned by the seller |

##### Constructor(s)

| Modifiers | Parameters | Description |
| ------------- | ------------- | ------------- |
| `public` | `String username, String email, String password` | sets values of fields username, email, and password using input arguments. allStoresFile is a string that equals username + ".txt". initializes stores to a new arraylist |

##### Methods

| Modifiers | Return type | Name | Parameters | Description |
| ------------- | ------------- | ------------- | ------------- | ------------- |
| `public` | `void`  | `setUsername` | `String username` | uses input to call super() and initializes allStoresFile to (input).txt |
| `public` | `void`  | `setAllStoresFile` | `String allStoresFile` | sets allStoresFiled |
| `public` | `void`  | `setStores` | `ArrayList<Store> stores` | sets stores |
| `public` | `String`  | `getAllStoresFile` | `None` | return allStoresFile |
| `public` | `ArrayList<Store>`  | `getStores` | `None` | return stores |
| `public` | `void`  | `addStore` | `Store store` | add input to stores |
| `public` | `void`  | `createStore` | `String storeName` | create a new Store object, add it to stores, and create a new file for the store, including the StoreProductsFile |
| `public` | `void`  | `deleteStore` | `Store store` | removes the input store from stores and deletes its StoreProductsFile |
| `public` | `void`  | `editStore` | `Store store, String storeName` | find the store in stores and replace its name with storeName input |
| `public` | `void`  | `saveData` | `None` | write the store and product information to files |

#### b) Testing
#### SellerLocalTest.java Description
    SellerLocalTest.java acts as a testing class forSeller.java by implementing a main method. 
    Within this main method a Seller is first instantiated. The program first tests the addition
    of new stores by utilizing the following methods: 'createStore()'  and 'addStore().' 
    Both require different parameters, however both result in a Store being added to the ArrayList. 
    Following this the program calls the 'saveData' method to save this data on file. The result
    of this test is determined by achieving the contents of the file to those of the ArrayList.
    Next, the 'deletStore()' method is tested. The result of this test is determined by the change
    in length of the ArrayList.
    The final method to be tested is the 'editStore()' method, which requires that the 
    initial storeName is compared to the new storeName.

#### c) Edit History

| Edit number (unique, serialized) | Name of method created/deleted/edited | Edit number of previous version of method | Final version of method in this edit |
| ------------- | ------------- | ------------- | ------------- |
| 1 | `toString()` | - | _copy paste edited method here_ |
| 2 | `setPassword()` | - | _copy paste edited method here_ |
| 3 | `toString()` | 1 | _copy paste edited method here_ |
| 4 | `toString()` | 3 | _copy paste edited method here_ |
| 5 | `setPassword()` | 2 | _copy paste edited method here_ |
    
### 4. Store.java

#### a) Functionality and relationship to other classes

##### Fields

| Modifiers | Type | Name | Description |
| ------------- | ------------- | ------------- | ------------- |
| `private` | `String`  | `storeName` | stores seller's store name |
| `private`  | `String`  | `storeProductsFile` | stores all the products in the store |
| `private`  | `ArrayList<Product>`  | `availableProducts` | stores available products in store |
| `private`  | `ArrayList<Purchases>`  | `allPurchases` | stores purchases made from the store |

##### Constructor(s)

| Modifiers | Parameters | Description |
| ------------- | ------------- | ------------- |
| `public` | `String storeName` | sets values of fields storeName using arguments and initializes storeProductsFile (accounting for any spaces in file names), availableProducts, and allPurchases |

##### Methods

| Modifiers | Return type | Name | Parameters | Description |
| ------------- | ------------- | ------------- | ------------- | ------------- |
| `public` | `String`  | `getStoreName` | `None` | returns store's name |
| `public`  | `String`  | `getStoreProductsFile` | `None` | returns the file containing store products |
| `public`  | `String`  | `getAvailableProducts` | `None` | returns arraylist of available products |
| `public`  | `ArrayList<Purchase>`  | `getAllPurchases` | `None` | returns allPurchases |
| `public` | `void`  | `setStoreName` | `String storeName` | sets store name to input argument |
| `public`  | `void`  | `setStoreProductsFile` | `None` | replaces spaces in file name with underscore and sets file name |
| `public`  | `void`  | `setAvailableProducts` | `ArrayList<Product> availableProducts` | sets arraylist with all available products |
| `public`  | `void`  | `addProduct` | `Product product` | adds input product to availableProducts ArrayList<Product> |
| `public`  | `void`  | `createProduct` | `String productName, String description, int quantity, double price` | creates new product using inputs and adds product to availbleProducts ArrayList<Product> |
| `public`  | `void`  | `deleteProduct` | `String productName` | finds productName in availbleProducts ArrayList<Product> and removes it |
| `public`  | `void`  | `resetPurchases` | `None` | sets allPurchases equal to a new empty ArrayList |
| `public`  | `void`  | `addPurchase` | `Purchase purchase` | adds input to allPurchases |
| `public`  | `void`  | `saveProducts` | `None` | writes availableProducts to storeProductsFile |
| `public`  | `String`  | `toString` | `None` | return storeName |
| `public`  | `String`  | `viewAllPurchases` | `None` | return "All purchases from (store name). Customer name: (customer username) Product Name: (product name) Product description: (description) Quantity sold: (quantity) Price for one: (unit price) Total revenue: (revenue)" |
| `public`  | `void`  | `importProducts` | `String filename` | loads products into file, throws appropriate file exceptions |
| `public`  | `void`  | `exportProducts` | `String filename` | unloads products from file, throws appropriate file exceptions |

#### b) Testing
#### StoreLocalTest.java Description
    StoreLocalTest.java acts as a testing class for Store.java by implementing a main method. 
    First, a Store is instantiated. The program first tests the addition
    of new Products by utilizing the following methods: 'createProduct()'  and 'addProduct().' Both require different parameters, however both result in a Store being added to the ArrayList.
    Following this, the 'saveProducts' method will be implemented. The result of this test
    is reflected after the program reads through thre resulting file and compares it to
    ArrayList valued.
    Next, the 'deletProduct()' method is tested. The result of this test is determined by
    the change in length of the ArrayList.
    Following this, the "purchase-related" methods are tested, such as 'viewAllPurchases()'. 
    This method is tested by comparing an expected String value to the actual String value.
    In order for this to run successfully, the 'resetPurchases()' method must work as well.
    Finally, the 'importProducts()' and 'exportProducts()' are test, which involves reading 
    through the file, to ensure the imported and exported values are correct.

#### c) Edit History

| Edit number (unique, serialized) | Name of method created/deleted/edited | Edit number of previous version of method | Final version of method in this edit |
| ------------- | ------------- | ------------- | ------------- |
| 1 | `toString()` | - | _copy paste edited method here_ |
| 2 | `setPassword()` | - | _copy paste edited method here_ |
| 3 | `toString()` | 1 | _copy paste edited method here_ |
| 4 | `toString()` | 3 | _copy paste edited method here_ |
| 5 | `setPassword()` | 2 | _copy paste edited method here_ |

### 5. Product.java

#### a) Functionality and relationship to other classes

##### Fields

| Modifiers | Type | Name | Description |
| ------------- | ------------- | ------------- | ------------- |
| `private` | `String`  | `productName` | name of the prodcut |
| `private`  | `String`  | `storeName` | name of the store which product is at |
| `private`  | `String`  | `description` | description of the product |
| `private`  | `int`  | `quantity` | number of a product available |
| `private`  | `double`  | `price` | price of the product |

##### Constructor(s)

| Modifiers | Parameters | Description |
| ------------- | ------------- | ------------- |
| `public` | `String productName, String storeName, String description, int quantity, double price` | sets values of fields productName, storeName, description, quantity, and price using the arguments |
| `public` | `Product product` | initilaizes all fields in Product |
| `public` | `Product product, int quantity` | initilaizes all fields in Product |

##### Methods

| Modifiers | Return type | Name | Parameters | Description |
| ------------- | ------------- | ------------- | ------------- | ------------- |
| `public` | `String`  | `getProductName` | `None` | returns product name |
| `public` | `String`  | `getStoreName` | `None` | returns store name |
| `public` | `String`  | `getDescription` | `None` | returns product description |
| `public` | `int`  | `getQuantity` | `None` | returns quantity |
| `public` | `double`  | `getPrice` | `None` | returns price |
| `public` | `None`  | `setProductName` | `String productName` | sets productName to input argument |
| `public` | `None`  | `setStoreName` | `String storeName` | sets storeName to input argument |
| `public` | `None`  | `setDescription` | `String description` | sets description to input argument |
| `public` | `None`  | `setQuantity` | `int quantity` | sets quantity to input argument |
| `public` | `None`  | `setPrice` | `double price` | sets price to input argument |
| `public` | `String`  | `getProductDataLanding` | `None` | return formatted string "Product name: (productName) Store selling it: (storeName) Price: (price)" |
| `public` | `String`  | `getProductDataByDesc` | `None` | return formatted string "Product name: (productName) Store selling it: (storeName) Product description: (description) Price: (price)" |
| `public` | `String`  | `getProductDataByQuantity` | `None` | return formatted string "Product name: (productName) Store selling it: (storeName) Quantity available to buy: (quantity) Price: (price)" |
| `public` | `String`  | `getProductDataProductPage` | `None` | return formatted string "Product name: (productName) Store selling it: (storeName) Product description: (description) Quantity available to buy: (quantity) Price: (price)" |
| `public` | `String`  | `toString` | `None` | return formatted string "(productName), (storeName), (description), (quantity), (price)" |

#### b) Testing

#### ProductLocalTest.java Description
    ProductLocalTest.java acts as a testing class for Product.java by implementing a main method. 
    First, a three Products are instantiated. Within this main method are various comparisons between expected String values and actual String values to ensure equality. These are necessary to 
    test the following methods: 'getProductDataLanding()', 'getProductDataByDesc()',
    'getProductByQuantity()', and 'getProductDataProductPage()'. 
    The main method also includes a test for the 'equals()' method. This involves a series of two
    test: this allows to test the methods ability to identify equality and inequality.
    
#### c) Edit History

| Edit number (unique, serialized) | Name of method created/deleted/edited | Edit number of previous version of method | Final version of method in this edit |
| ------------- | ------------- | ------------- | ------------- |
| 1 | `toString()` | - | _copy paste edited method here_ |
| 2 | `setPassword()` | - | _copy paste edited method here_ |
| 3 | `toString()` | 1 | _copy paste edited method here_ |
| 4 | `toString()` | 3 | _copy paste edited method here_ |
| 5 | `setPassword()` | 2 | _copy paste edited method here_ |    
   
### 6. Purchase.java

#### a) Functionality and relationship to other classes

Note: Purchase.java extends Product.java

##### Fields

| Modifiers | Type | Name | Description |
| ------------- | ------------- | ------------- | ------------- |
| `private` | `string`  | `customerUsername` | the customer's username |
| `private`  | `double`  | `revenue` | the revenue made |

##### Constructor(s)

| Modifiers | Parameters | Description |
| ------------- | ------------- | ------------- |
| `public` | `Product product, String customerUsername, int quantityBought` | calls super() and passes in arguments product and quantityBought. Initializes revenue |
| `public` | `String productName, String storeName, String description, int quantity, double price, String customerUsername, double revenue` | calls super() and passes in arguments productName, storeName, description, quantity,and price. Initializes customerUsername and revenue |

##### Methods

| Modifiers | Return type | Name | Parameters | Description |
| ------------- | ------------- | ------------- | ------------- | ------------- |
| `public` | `String`  | `getCustomerUsername` | `None` | returns customerUsername |
| `public` | `double`  | `getRevenue` | `None` | returns revenue |
| `public` | `void`  | `setCustomerUsername` | `String customerUsername` | sets customerUsername to argumment input |
| `public` | `String`  | `printDataForCustomer` | `None` | return formatted string "Product name: (product name) Store Name: (store name) Description: (description) Price when bought: (price) Quantity bought: (quantity) Total Cost: (revenue)" |
| `public` | `String`  | `toString` | `None` | return formatted string "(customer username), (revenue)" |

#### b) Testing

#### PurchaseLocalTest.java Description
    PurchaseLocalTest.java acts as a testing class for Purchase.java by implementing a main method. 
    First, a a Product as well as a Purchase are instantiated. The only tested method within
    this class is the 'printDataForCustomer()' method, which is tested via a test between
    an expected String and the actual String.
    
#### c) Edit History

| Edit number (unique, serialized) | Name of method created/deleted/edited | Edit number of previous version of method | Final version of method in this edit |
| ------------- | ------------- | ------------- | ------------- |
| 1 | `toString()` | - | _copy paste edited method here_ |
| 2 | `setPassword()` | - | _copy paste edited method here_ |
| 3 | `toString()` | 1 | _copy paste edited method here_ |
| 4 | `toString()` | 3 | _copy paste edited method here_ |
| 5 | `setPassword()` | 2 | _copy paste edited method here_ |    

### 7. Marketplace.java

#### a) Functionality and relationship to other classes

##### Fields

| Modifiers | Type | Name | Description |
| ------------- | ------------- | ------------- | ------------- |
| `private static final` | `string`  | `sellerFile` | "sellerFile.txt" |
| `private static`  | `ArrayList<Seller>`  | `sellerArray` | contains all sellers |
| `private static final`  | `string`  | `customerFile` | "customerFile.txt" |
| `private static`  | `ArrayList<Customer>`  | `customerArray` | contains all customers |
| `private static final`  | `String`  | `purchaseLog` | "purchaseLog.txt" |
| `private static`  | `ArrayList<Purchase>`  | `allPurchases` | contains allPurchases |
| `private static final`  | `string`  | `WELCOME_MESSAGE` | "Welcome to Butter, your one stop for everything!\nWhat would you like to do today?" |
| `private static final`  | `string`  | `LOGIN_OR_CREATE` | "1. Create new account<br />2. Login to an existing account<br />3. Exit marketplace" |
| `private static final`  | `string`  | `LOGIN_SELLER_OR_CUSTOMER` | "Login as:<br />1. Seller<br />2. Customer" |
| `private static final`  | `string`  | `SELLER_OR_CUSTOMER` | "What best describes you?<br />1. Seller<br />2. Customer" |
| `private static final`  | `string`  | `INVALID_OPTION` | "Please choose a valid option!" |
| `private static final`  | `string`  | `USERNAME_PROMPT` | "Enter username: " |
| `private static final`  | `string`  | `PASSWORD_PROMPT` | "Enter password: " |
| `private static final`  | `string`  | `INVALID_LOGIN` | "Invalid login!" |
| `private static final`  | `string`  | `EDIT_OR_DELETE_ACC_OR_SURF` | "Hello! What next?<br />1. Edit my account<br />2. Delete my account<br />3. View more options :P<br />4. Logout" |
| `private static final`  | `string`  | `CONFIRM_ACTION` | "Please enter your password to confirm: " |
| `private static final`  | `string`  | `CONFIRM_PASSWORD_CHANGE` | "Please enter your old password to confirm: " |
| `private static final`  | `string`  | `ACTION_UNALLOWED` |"Wrong password! Try again later :/" |
| `private static final`  | `string`  | `EDIT_ACC_OPTIONS` |"What would you like to do?<br />1. Edit username<br />2. Update email<br />3. Change password<br />4. Go back" |
| `private static final`  | `string`  | `SELLER_OPTIONS` | "What would you like to do?<br />1. Edit username<br />2. Update email<br />3. Change password<br />4. Go back" |
| `private static final`  | `string`  | `SEPARATOR` | "-------------" |


##### Methods

| Modifiers | Return type | Name | Parameters | Description |
| ------------- | ------------- | ------------- | ------------- | ------------- |
| `public static` | `void`  | `main` | `String[] args` | performs the function of the ecommerce store. Appropriatley prompts the user, take in input, processes it, and outputs new prompts or resulting information.|
| `public static` | `Seller`  | `validateSellerLogin` | `String username, String password` | ensures that user of type Seller's login credentials match existing account, returns the seller account |
| `public static` | `Customer`  | `validateCustomerLogin` | `String username, String password` | ensures that user of type Customer's login credentials match existing account, returns the customer account |
| `public static` | `void`  | `deleteUser` | `User user` | checks user type and deletes appropriate account, loads information to respective .txt files |
| `public static` | `boolean`  | `isValidString` | `String string` | returns true if string does not contain comma(s) |
| `public static` | `boolean`  | `isValidUsername` | `String username` | returns true if argument username does not already exist or contain comma(s) |
| `public static` | `ArrayList<Product>`  | `filterByProductName` | `String storeName, ArrayList<Product> allProducts | returns all products containing argument productName |
| `public static` | `ArrayList<Product>`  | `filterByStoreName` | `String productName, ArrayList<Product> allProducts | returns all products containing argument storeName |
| `public static` | `ArrayList<Product>`  | `filterByDescription` | `String description, ArrayList<Product> allProducts` | returns all products containing argument description |
| `public static` | `ArrayList<Product>`  | `sortPrice` | `int choice, ArrayList<Product> allProducts` | returns all products sorted from high to low or low to high depending on argument choice |
| `public static` | `ArrayList<Product>`  | `sortQuantity` | `String username` | returns all products sorted from high to low or low to high depending on argument choice |
| `public static` |  `void` | `readSellers` | `None` | reads through sellerFile |
| `public static` |  `void` | `readCustomers` | `None` | reads through customerFile |
| `public static` |  `void` | `loadPurchasesFromFile` | `None` | loads purchases from purchaseLog |
| `public static` |  `void` | `loadPurchaseDataForStoresAndCustomers` | `None` | loads data |
| `public static` |  `void` | `savePurchases` | `None` | loads data to purchaseLog |
| `public static` |  `void` | `saveData` | `None` | loads data |

#### b) Testing

    Tests are completed within `MarketplaceTest.java`. Within this class, 4 tests are performed. 
    Below is an outline of what each test requires in order to run. We also describe the requirements 
    that each test tests.
    
Test 1 (`testCustomerOptions()`): 
    Prior to running this test, all `.txt` files from the folder testOneReset must be moved to the directory with the `.java` files<br />
    
<br />Test case flow: <br />
    
- User attempts to login as customer unsuccessfully
- User successfully logs in as customer
- User (customer) searches for products by product name, store name, description
- User (customer) sorts products by price (high to low and low to high) and quantity (low to high)
- User (customer) buys a product
- User (customer) views purchase history
- User (customer) logs out and exits marketplace
    
Test 2 (`testAccDelete()`):
    Prior to running this test, all `.txt` files from the folder testOneReset must be moved to the directory with the `.java` files.<br />`purchaseLog.txt`'s contents must be changed so it contains the data below:
    
    "Fine Paintbrush","Michaels","Quality paintbrush for fine lines",4,14.99,aysusaglam,59.96
    "4pk Wood Coaster Set","Target","Acacia wood coaster set includes 4 round coasters",3,12.00,aysusaglam,36.00

<br />Test case flow: <br />

- User logs in to seller account successfully
- User deletes their seller account and gets automatically logged out
- User attempts to log into seller account that was just deleted, log in is unsuccessful
- User logs into valid customer account. (This customer had made a purchase from the seller deleted previously in this test case) 
- User views purchase history, item bought from deleted seller is not visible anymore

Test 3 (`testThree()`):
    Prior to running this test, all `.txt` files from the folder testOneReset must be moved to the directory with the `.java` files.<br />`purchaseLog.txt`'s contents must be changed so it contains the data below:
    
    ""MacBook Pro","Costco Wholesale","13-inch MacBook Pro laptop is a portable powerhouse",2,1349.99,aysusaglam,2699.98"

<br />Test case flow: <br />    

- User logs in to seller account successfully
- User shows all the statistics regarding their stores (statistics selection for sellers), and shows the data sorted differently
- User logs out
- User logs into a customer account successfully 
- User shows all the statistics regarding all the stores listed in the marketplace (statistics selection for customers), and shows the data sorted differently
    
Test 4 (`testFour()`): 
    Prior to running this test, all `.txt` files from the folder testOneReset must be moved to the directory with the `.java` files.<br />

<br />Test case flow: <br />
    
- User successfully creates a seller account
    - also enters in an invalid option while doing so, is reprompted to select a valid option
- User successfully logs into the new account
- User successfully creates a store
- User imports product data into the new store (file selection for seller)
- User exports the product data into a new file

#### c) Edit History

| Edit number (unique, serialized) | Name of method created/deleted/edited | Edit number of previous version of method | Final version of method in this edit |
| ------------- | ------------- | ------------- | ------------- |
| 1 | `toString()` | - | _copy paste edited method here_ |
| 2 | `setPassword()` | - | _copy paste edited method here_ |
| 3 | `toString()` | 1 | _copy paste edited method here_ |
| 4 | `toString()` | 3 | _copy paste edited method here_ |
| 5 | `setPassword()` | 2 | _copy paste edited method here_ |    
