import java.util.*;

// ==================== INTERFACE ====================
interface Reservable {
    void reserveItem(String borrowerName);
    boolean checkAvailability();
}

// ==================== ABSTRACT CLASS ====================
abstract class LibraryItem implements Reservable {
    private String itemId;
    private String title;
    private String author;
    private String borrowerName;  // Encapsulated personal data
    private boolean available;

    public LibraryItem(String itemId, String title, String author) {
        this.itemId = itemId;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    // Getters and Setters (Encapsulation)
    public String getItemId() { return itemId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }

    protected String getBorrowerName() { return borrowerName; }  // Restricted access
    protected void setBorrowerName(String name) { this.borrowerName = name; }
    protected void setAvailable(boolean available) { this.available = available; }

    // Abstract method
    public abstract int getLoanDuration();

    // Concrete method
    public void getItemDetails() {
        System.out.println("Item ID      : " + itemId);
        System.out.println("Title        : " + title);
        System.out.println("Author       : " + author);
        System.out.println("Loan Period  : " + getLoanDuration() + " days");
        System.out.println("Available    : " + (available ? "Yes" : "No (Borrowed by: " + borrowerName + ")"));
    }

    // Interface methods
    @Override
    public void reserveItem(String borrowerName) {
        if (available) {
            this.borrowerName = borrowerName;
            this.available = false;
            System.out.println("✅ \"" + title + "\" reserved for " + borrowerName);
        } else {
            System.out.println("❌ \"" + title + "\" is currently not available.");
        }
    }

    @Override
    public boolean checkAvailability() {
        return available;
    }
}

// ==================== SUBCLASSES ====================
class Book extends LibraryItem {
    public Book(String id, String title, String author) {
        super(id, title, author);
    }

    @Override
    public int getLoanDuration() {
        return 14; // 2 weeks
    }
}

class Magazine extends LibraryItem {
    public Magazine(String id, String title, String author) {
        super(id, title, author);
    }

    @Override
    public int getLoanDuration() {
        return 7; // 1 week
    }
}

class DVD extends LibraryItem {
    public DVD(String id, String title, String author) {
        super(id, title, author);
    }

    @Override
    public int getLoanDuration() {
        return 3; // 3 days
    }
}

// ==================== MAIN CLASS ====================
public class LibraryManagementSystem {
    public static void main(String[] args) {
        // Polymorphism - LibraryItem reference for all types
        List<LibraryItem> items = new ArrayList<>();

        items.add(new Book("B001", "Java Programming", "Herbert Schildt"));
        items.add(new Magazine("M001", "National Geographic", "Various"));
        items.add(new DVD("D001", "The Social Network", "David Fincher"));

        System.out.println("===== LIBRARY MANAGEMENT SYSTEM =====\n");

        System.out.println("--- All Items ---");
        for (LibraryItem item : items) {
            System.out.println("-----------------------------");
            item.getItemDetails();
        }

        System.out.println("\n--- Reservations ---");
        items.get(0).reserveItem("Rahul Sharma");
        items.get(0).reserveItem("Priya Mehta"); // Should fail - already reserved
        items.get(2).reserveItem("Ankit Verma");

        System.out.println("\n--- Updated Status ---");
        for (LibraryItem item : items) {
            System.out.println(item.getTitle() + " → Available: " + item.checkAvailability());
        }
    }
}
