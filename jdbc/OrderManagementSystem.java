// ==================== MULTILEVEL INHERITANCE ====================
// Level 1 - Base class
class Order {
    private String orderId;
    private String orderDate;

    public Order(String orderId, String orderDate) {
        this.orderId = orderId;
        this.orderDate = orderDate;
    }

    public String getOrderId() { return orderId; }
    public String getOrderDate() { return orderDate; }

    public String getOrderStatus() {
        return "Order Placed on " + orderDate;
    }

    public void displayInfo() {
        System.out.println("Order ID    : " + orderId);
        System.out.println("Order Date  : " + orderDate);
        System.out.println("Status      : " + getOrderStatus());
    }
}

// Level 2 - Subclass of Order
class ShippedOrder extends Order {
    private String trackingNumber;
    private String shippedDate;

    public ShippedOrder(String orderId, String orderDate, String trackingNumber, String shippedDate) {
        super(orderId, orderDate); // Call Order constructor
        this.trackingNumber = trackingNumber;
        this.shippedDate = shippedDate;
    }

    public String getTrackingNumber() { return trackingNumber; }
    public String getShippedDate() { return shippedDate; }

    @Override
    public String getOrderStatus() {
        return "Shipped on " + shippedDate + " | Tracking: " + trackingNumber;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Shipped Date: " + shippedDate);
        System.out.println("Tracking No : " + trackingNumber);
    }
}

// Level 3 - Subclass of ShippedOrder
class DeliveredOrder extends ShippedOrder {
    private String deliveryDate;
    private String receivedBy;

    public DeliveredOrder(String orderId, String orderDate, String trackingNumber,
                          String shippedDate, String deliveryDate, String receivedBy) {
        super(orderId, orderDate, trackingNumber, shippedDate); // Call ShippedOrder constructor
        this.deliveryDate = deliveryDate;
        this.receivedBy = receivedBy;
    }

    public String getDeliveryDate() { return deliveryDate; }

    @Override
    public String getOrderStatus() {
        return "✅ Delivered on " + deliveryDate + " | Received by: " + receivedBy;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Delivery Date: " + deliveryDate);
        System.out.println("Received By  : " + receivedBy);
    }
}

// ==================== MAIN CLASS ====================
public class OrderManagementSystem {
    public static void main(String[] args) {
        Order o1 = new Order("ORD-001", "01/04/2025");
        ShippedOrder o2 = new ShippedOrder("ORD-002", "02/04/2025", "TRK-XY9988", "04/04/2025");
        DeliveredOrder o3 = new DeliveredOrder("ORD-003", "01/04/2025", "TRK-AB1234",
                "03/04/2025", "06/04/2025", "Rahul Sharma");

        System.out.println("===== ONLINE RETAIL ORDER MANAGEMENT =====\n");

        System.out.println("--- Order (Placed) ---");
        o1.displayInfo();

        System.out.println("\n--- Order (Shipped) ---");
        o2.displayInfo();

        System.out.println("\n--- Order (Delivered) ---");
        o3.displayInfo();

        // Polymorphism - Order reference for all levels
        System.out.println("\n--- Order Status Summary ---");
        Order[] orders = {o1, o2, o3};
        for (Order o : orders) {
            System.out.println(o.getOrderId() + " → " + o.getOrderStatus());
        }
    }
}
