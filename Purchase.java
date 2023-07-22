/**
 * @author Aashna, Jiwon, Aysu, Mridu
 * @version 1.0
 */
public class Purchase extends Product {
    private String customerUsername;
    private double revenue;

    public Purchase(Product product, String customerUsername, int quantityBought) {
        super(product, quantityBought); //?? changed quantity to quantityBought
        this.customerUsername = customerUsername;
        this.revenue = super.getPrice() * quantityBought;
    }

    public Purchase(String productName, String storeName, String description, int quantity, double price,
                    String customerUsername, double revenue) {
        super(productName, storeName, description, quantity, price);
        this.customerUsername = customerUsername;
        this.revenue = revenue;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public double getRevenue() {
        return revenue;
    }

    public String printDataForCustomer() {
        return String.format("Product name: %s%nStore Name: %s%nDescription: %s%n" +
                        "Price when bought: %.2f%nQuantity bought: %d%nTotal Cost: %.2f",
                super.getProductName(), super.getStoreName(), super.getDescription(),
                super.getPrice(), super.getQuantity(), revenue);
    }

    public String toString() {
        return super.toString() + String.format(",%s,%.2f", customerUsername, revenue);
        //?? include storeName too so allPurchases can be loaded
    }
}
