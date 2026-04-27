// ==================== HYBRID INHERITANCE ====================
// Interface
interface Worker {
    void performDuties();
}

// Superclass
class Person {
    private String name;
    private int id;

    public Person(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() { return name; }
    public int getId() { return id; }

    public void displayInfo() {
        System.out.println("ID   : " + id);
        System.out.println("Name : " + name);
    }
}

// Subclass 1 - inherits Person + implements Worker (Hybrid)
class Chef extends Person implements Worker {
    private String specialty;
    private int yearsOfExperience;

    public Chef(String name, int id, String specialty, int yearsOfExperience) {
        super(name, id);
        this.specialty = specialty;
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getSpecialty() { return specialty; }

    @Override
    public void performDuties() {
        System.out.println("👨‍🍳 Chef " + getName() + " is preparing " + specialty + " dishes.");
        System.out.println("   Experience: " + yearsOfExperience + " years in the kitchen.");
    }

    public void displayInfo() {
        System.out.println("Role       : Chef");
        super.displayInfo();
        System.out.println("Specialty  : " + specialty);
        System.out.println("Experience : " + yearsOfExperience + " years");
    }
}

// Subclass 2 - inherits Person + implements Worker (Hybrid)
class Waiter extends Person implements Worker {
    private int tablesAssigned;
    private String shift;

    public Waiter(String name, int id, int tablesAssigned, String shift) {
        super(name, id);
        this.tablesAssigned = tablesAssigned;
        this.shift = shift;
    }

    @Override
    public void performDuties() {
        System.out.println("🧑‍🍽️ Waiter " + getName() + " is serving " + tablesAssigned + " tables.");
        System.out.println("   Shift: " + shift);
    }

    public void displayInfo() {
        System.out.println("Role            : Waiter");
        super.displayInfo();
        System.out.println("Tables Assigned : " + tablesAssigned);
        System.out.println("Shift           : " + shift);
    }
}

// ==================== MAIN CLASS ====================
public class RestaurantManagementSystem {
    public static void main(String[] args) {
        Chef chef1 = new Chef("Ravi Kumar", 201, "North Indian", 10);
        Chef chef2 = new Chef("Anjali Singh", 202, "Continental", 7);
        Waiter waiter1 = new Waiter("Mohit Yadav", 301, 5, "Morning");
        Waiter waiter2 = new Waiter("Sneha Joshi", 302, 4, "Evening");

        System.out.println("===== RESTAURANT MANAGEMENT SYSTEM =====\n");

        System.out.println("--- Staff Details ---");
        System.out.println("\n[Chef 1]");
        chef1.displayInfo();
        System.out.println("\n[Chef 2]");
        chef2.displayInfo();
        System.out.println("\n[Waiter 1]");
        waiter1.displayInfo();
        System.out.println("\n[Waiter 2]");
        waiter2.displayInfo();

        // Polymorphism via Worker interface
        System.out.println("\n--- Duty Assignments ---");
        Worker[] workers = {chef1, chef2, waiter1, waiter2};
        for (Worker w : workers) {
            w.performDuties();  // Polymorphic call through interface
            System.out.println();
        }
    }
}
