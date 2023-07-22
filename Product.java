/**
 * @author Aashna, Jiwon, Aysu, Mridu
 * @version 1.0
 */
public class Product {

    //Fields
    private String productName;
    private String storeName;
    private String description;
    private int quantity;
    private double price;

    public Product(String productName, String storeName, String description, int quantity, double price) {
        this.productName = productName;
        this.storeName = storeName;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public Product(Product product) {
        this.productName = product.getProductName();
        this.storeName = product.getStoreName();
        this.description = product.getDescription();
        this.quantity = product.getQuantity();
        this.price = product.getPrice();
    }

    public Product(Product product, int quantity) {
        this.productName = product.getProductName();
        this.storeName = product.getStoreName();
        this.description = product.getDescription();
        this.quantity = quantity;
        this.price = product.getPrice();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Product)) {
            return false;
        }
        if (!(((Product) obj).getProductName().equals(this.productName))) {
            return false;
        }
        if (!(((Product) obj).getStoreName().equals(this.storeName))) {
            return false;
        }
        if (!(((Product) obj).getDescription().equals(this.description))) {
            return false;
        }
        if (!(((Product) obj).getQuantity() == this.quantity)) {
            return false;
        }
        return ((Product) obj).getPrice() == this.price;
    }

    public String getProductDataLanding() {
        return String.format("Product name: %s%n Store selling it: %s%n" +
                " Price: %.2f%n", productName, storeName, price);
    }

    public String getProductDataByDesc() {
        return String.format("Product name: %s%n Store selling it: %s%n Product description: %s%n" +
                " Price: %.2f%n", productName, storeName, description, price);
    }


    public String getProductDataByQuantity() {
        return String.format("Product name: %s%n Store selling it: %s%n" +
                " Quantity available to buy: %d%n Price: %.2f%n", productName, storeName, quantity, price);
    }

    public String displayProductPage() {
        return String.format("Product name: %s%n Store selling it: %s%n" +
                "  Product description: %s%n" +
                " Quantity available to buy: %d%n Price: %.2f%n", productName, storeName, description, quantity, price);
    }

    @Override
    public String toString() {
        return String.format("\"%s\",\"%s\",\"%s\",%d,%.2f", productName, storeName, description, quantity, price);
    }
}
