import java.util.*;

// ==================== INTERFACE ====================
interface Discountable {
    double applyDiscount();
    String getDiscountDetails();
}

// ==================== ABSTRACT CLASS ====================
abstract class FoodItem implements Discountable {
    private String itemName;
    private double price;
    private int quantity;

    public FoodItem(String itemName, double price, int quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and Setters (Encapsulation)
    public String getItemName() { return itemName; }

    public double getPrice() { return price; }
    protected void setPrice(double price) { this.price = price; } // restricted setter

    public int getQuantity() { return quantity; }
    protected void setQuantity(int quantity) { this.quantity = quantity; }

    // Abstract method
    public abstract double calculateTotalPrice();

    // Concrete method
    public void getItemDetails() {
        System.out.println("Item Name    : " + itemName);
        System.out.printf("Price        : ₹%.2f%n", price);
        System.out.println("Quantity     : " + quantity);
        System.out.printf("Total Price  : ₹%.2f%n", calculateTotalPrice());
        System.out.printf("Discount     : ₹%.2f (%s)%n", applyDiscount(), getDiscountDetails());
        System.out.printf("Final Bill   : ₹%.2f%n", calculateTotalPrice() - applyDiscount());
    }
}

// ==================== SUBCLASSES ====================
class VegItem extends FoodItem {
    public VegItem(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    @Override
    public double calculateTotalPrice() {
        return getPrice() * getQuantity(); // No extra charges for veg
    }

    @Override
    public double applyDiscount() {
        return calculateTotalPrice() * 0.10; // 10% discount on veg items
    }

    @Override
    public String getDiscountDetails() {
        return "10% Veg Discount";
    }
}

class NonVegItem extends FoodItem {
    private double handlingCharge = 30.0; // Extra charge per item

    public NonVegItem(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    @Override
    public double calculateTotalPrice() {
        // Extra handling charge added for non-veg
        return (getPrice() + handlingCharge) * getQuantity();
    }

    @Override
    public double applyDiscount() {
        return calculateTotalPrice() * 0.05; // Only 5% discount on non-veg
    }

    @Override
    public String getDiscountDetails() {
        return "5% Non-Veg Discount (Handling charge ₹" + handlingCharge + "/item added)";
    }
}

// ==================== MAIN CLASS ====================
public class OnlineFoodDeliverySystem {

    // Polymorphism - processes all FoodItem types
    public static void processOrder(List<FoodItem> order) {
        System.out.println("===== ONLINE FOOD DELIVERY - ORDER SUMMARY =====\n");
        double grandTotal = 0;

        for (FoodItem item : order) {
            System.out.println("--------------------------------------------");
            item.getItemDetails();
            grandTotal += item.calculateTotalPrice() - item.applyDiscount();
        }

        System.out.println("--------------------------------------------");
        System.out.printf("💰 Grand Total: ₹%.2f%n", grandTotal);
        System.out.println("🚚 Delivery charge: ₹40.00");
        System.out.printf("🧾 Amount Payable: ₹%.2f%n", grandTotal + 40);
    }

    public static void main(String[] args) {
        List<FoodItem> order = new ArrayList<>();

        order.add(new VegItem("Paneer Butter Masala", 280, 2));
        order.add(new NonVegItem("Chicken Biryani", 350, 1));
        order.add(new VegItem("Veg Fried Rice", 180, 3));
        order.add(new NonVegItem("Mutton Rogan Josh", 450, 2));

        processOrder(order);
    }
}
