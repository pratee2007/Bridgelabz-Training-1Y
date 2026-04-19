// ==================== HYBRID INHERITANCE ====================
// Interface (simulates multiple inheritance)
interface Refuelable {
    void refuel(double liters);
    String getFuelType();
}

// Superclass
class Vehicle {
    private String model;
    private int maxSpeed;

    public Vehicle(String model, int maxSpeed) {
        this.model = model;
        this.maxSpeed = maxSpeed;
    }

    public String getModel() { return model; }
    public int getMaxSpeed() { return maxSpeed; }

    public void displayDetails() {
        System.out.println("Model      : " + model);
        System.out.println("Max Speed  : " + maxSpeed + " km/h");
    }
}

// Subclass 1 - inherits Vehicle (no Refuelable)
class ElectricVehicle extends Vehicle {
    private double batteryCapacity; // in kWh
    private double currentCharge;   // in percentage

    public ElectricVehicle(String model, int maxSpeed, double batteryCapacity) {
        super(model, maxSpeed);
        this.batteryCapacity = batteryCapacity;
        this.currentCharge = 100.0;
    }

    // EV-specific method
    public void charge(double hours) {
        double chargeAdded = hours * 20; // 20% per hour
        currentCharge = Math.min(100.0, currentCharge + chargeAdded);
        System.out.println("⚡ " + getModel() + " charged for " + hours + " hours.");
        System.out.printf("   Battery Level: %.1f%%%n", currentCharge);
    }

    public double getCurrentCharge() { return currentCharge; }

    @Override
    public void displayDetails() {
        System.out.println("Type           : Electric Vehicle");
        super.displayDetails();
        System.out.println("Battery        : " + batteryCapacity + " kWh");
        System.out.printf("Current Charge : %.1f%%%n", currentCharge);
    }
}

// Subclass 2 - inherits Vehicle + implements Refuelable (Hybrid)
class PetrolVehicle extends Vehicle implements Refuelable {
    private double tankCapacity; // in liters
    private double currentFuel;

    public PetrolVehicle(String model, int maxSpeed, double tankCapacity) {
        super(model, maxSpeed);
        this.tankCapacity = tankCapacity;
        this.currentFuel = tankCapacity; // Full tank initially
    }

    // Refuelable interface methods
    @Override
    public void refuel(double liters) {
        double space = tankCapacity - currentFuel;
        double added = Math.min(liters, space);
        currentFuel += added;
        System.out.println("⛽ " + getModel() + " refueled with " + added + " liters.");
        System.out.printf("   Tank Level: %.1f / %.1f liters%n", currentFuel, tankCapacity);
    }

    @Override
    public String getFuelType() {
        return "Petrol";
    }

    public double getCurrentFuel() { return currentFuel; }

    @Override
    public void displayDetails() {
        System.out.println("Type         : Petrol Vehicle");
        super.displayDetails();
        System.out.println("Fuel Type    : " + getFuelType());
        System.out.println("Tank Capacity: " + tankCapacity + " liters");
        System.out.printf("Current Fuel : %.1f liters%n", currentFuel);
    }
}

// ==================== MAIN CLASS ====================
public class VehicleManagementSystem {
    public static void main(String[] args) {
        ElectricVehicle ev = new ElectricVehicle("Tesla Model 3", 250, 75.0);
        PetrolVehicle pv = new PetrolVehicle("Honda Civic", 220, 47.0);

        System.out.println("===== VEHICLE MANAGEMENT SYSTEM =====\n");

        System.out.println("--- Electric Vehicle ---");
        ev.displayDetails();
        ev.charge(2.5); // Charge for 2.5 hours

        System.out.println("\n--- Petrol Vehicle ---");
        pv.displayDetails();
        pv.refuel(20); // Add 20 liters

        // Polymorphism via Vehicle reference
        System.out.println("\n--- All Vehicles Summary ---");
        Vehicle[] vehicles = {ev, pv};
        for (Vehicle v : vehicles) {
            System.out.println(v.getModel() + " | Max Speed: " + v.getMaxSpeed() + " km/h");
        }

        // Polymorphism via Refuelable interface
        System.out.println("\n--- Refuelable Vehicles ---");
        Refuelable[] refuelables = {pv};
        for (Refuelable r : refuelables) {
            System.out.println("Fuel Type: " + r.getFuelType());
            r.refuel(10);
        }
    }
}
