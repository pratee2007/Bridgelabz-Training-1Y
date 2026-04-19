import java.util.*;

// ==================== INTERFACE ====================
interface Taxable {
    double calculateTax();
    String getTaxDetails();
}

// ==================== ABSTRACT CLASS ====================
abstract class Product {
    private String productId;
    private String name;
    private double price;

    public Product(String productId, String name, double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    // Getters and Setters (Encapsulation)
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    // Abstract method
    public abstract double calculateDiscount();

    public void displayProduct() {
        System.out.println("Product ID : " + productId);
        System.out.println("Name       : " + name);
        System.out.println("Price      : ₹" + price);
        System.out.printf("Discount   : ₹%.2f%n", calculateDiscount());
    }
}

// ==================== SUBCLASSES ====================
class Electronics extends Product implements Taxable {
    private double taxRate = 0.18; // 18% GST

    public Electronics(String id, String name, double price) {
        super(id, name, price);
    }

    @Override
    public double calculateDiscount() {
        return getPrice() * 0.10; // 10% discount
    }

    @Override
    public double calculateTax() {
        return getPrice() * taxRate;
    }

    @Override
    public String getTaxDetails() {
        return "Tax Type: GST 18% = ₹" + String.format("%.2f", calculateTax());
    }
}

class Clothing extends Product implements Taxable {
    private double taxRate = 0.05; // 5% GST

    public Clothing(String id, String name, double price) {
        super(id, name, price);
    }

    @Override
    public double calculateDiscount() {
        return getPrice() * 0.20; // 20% discount
    }

    @Override
    public double calculateTax() {
        return getPrice() * taxRate;
    }

    @Override
    public String getTaxDetails() {
        return "Tax Type: GST 5% = ₹" + String.format("%.2f", calculateTax());
    }
}

class Groceries extends Product {
    public Groceries(String id, String name, double price) {
        super(id, name, price);
    }

    @Override
    public double calculateDiscount() {
        return getPrice() * 0.05; // 5% discount
    }
    // Groceries are not taxable
}

// ==================== MAIN CLASS ====================
public class ECommercePlatform {

    // Polymorphism - processes any Product type
    public static void calculateFinalPrice(List<Product> products) {
        System.out.println("===== E-COMMERCE PLATFORM - FINAL PRICES =====\n");

        for (Product p : products) {
            System.out.println("----------------------------------------------");
            p.displayProduct();

            double tax = 0;
            if (p instanceof Taxable) {
                Taxable t = (Taxable) p;
                tax = t.calculateTax();
                System.out.println(t.getTaxDetails());
            } else {
                System.out.println("Tax        : Not Applicable");
            }

            double finalPrice = p.getPrice() + tax - p.calculateDiscount();
            System.out.printf("Final Price: ₹%.2f%n", finalPrice);
        }
        System.out.println("----------------------------------------------");
    }

    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();

        products.add(new Electronics("E001", "Samsung TV", 45000));
        products.add(new Clothing("C001", "Levi's Jeans", 2500));
        products.add(new Groceries("G001", "Organic Rice (5kg)", 600));

        calculateFinalPrice(products);
    }
}
