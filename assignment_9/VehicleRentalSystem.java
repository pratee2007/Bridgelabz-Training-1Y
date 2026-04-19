import java.util.*;

// ==================== INTERFACE ====================
interface Insurable {
    double calculateInsurance();
    String getInsuranceDetails();
}

// ==================== ABSTRACT CLASS ====================
abstract class Vehicle {
    private String vehicleNumber;
    private String type;
    private double rentalRate;
    private String insurancePolicyNumber; // encapsulated - no public getter

    public Vehicle(String vehicleNumber, String type, double rentalRate, String policyNumber) {
        this.vehicleNumber = vehicleNumber;
        this.type = type;
        this.rentalRate = rentalRate;
        this.insurancePolicyNumber = policyNumber;
    }

    // Getters and Setters (Encapsulation)
    public String getVehicleNumber() { return vehicleNumber; }
    public String getType() { return type; }
    public double getRentalRate() { return rentalRate; }
    public void setRentalRate(double rentalRate) { this.rentalRate = rentalRate; }

    // Policy number restricted - only available internally
    protected String getInsurancePolicyNumber() { return insurancePolicyNumber; }

    // Abstract method
    public abstract double calculateRentalCost(int days);

    public void displayDetails(int days) {
        System.out.println("Vehicle No   : " + vehicleNumber);
        System.out.println("Type         : " + type);
        System.out.println("Rental Rate  : ₹" + rentalRate + "/day");
        System.out.println("Days Rented  : " + days);
        System.out.printf("Rental Cost  : ₹%.2f%n", calculateRentalCost(days));
    }
}

// ==================== SUBCLASSES ====================
class Car extends Vehicle implements Insurable {
    public Car(String number, double rate, String policy) {
        super(number, "Car", rate, policy);
    }

    @Override
    public double calculateRentalCost(int days) {
        return getRentalRate() * days * 1.1; // 10% extra service charge for cars
    }

    @Override
    public double calculateInsurance() {
        return getRentalRate() * 0.15; // 15% of daily rate
    }

    @Override
    public String getInsuranceDetails() {
        return "Car Insurance: ₹" + String.format("%.2f", calculateInsurance()) + "/day | Policy: " + getInsurancePolicyNumber();
    }
}

class Bike extends Vehicle implements Insurable {
    public Bike(String number, double rate, String policy) {
        super(number, "Bike", rate, policy);
    }

    @Override
    public double calculateRentalCost(int days) {
        return getRentalRate() * days; // No extra charge
    }

    @Override
    public double calculateInsurance() {
        return getRentalRate() * 0.05; // 5% of daily rate
    }

    @Override
    public String getInsuranceDetails() {
        return "Bike Insurance: ₹" + String.format("%.2f", calculateInsurance()) + "/day | Policy: " + getInsurancePolicyNumber();
    }
}

class Truck extends Vehicle implements Insurable {
    public Truck(String number, double rate, String policy) {
        super(number, "Truck", rate, policy);
    }

    @Override
    public double calculateRentalCost(int days) {
        return getRentalRate() * days * 1.25; // 25% extra for heavy vehicle
    }

    @Override
    public double calculateInsurance() {
        return getRentalRate() * 0.20; // 20% of daily rate
    }

    @Override
    public String getInsuranceDetails() {
        return "Truck Insurance: ₹" + String.format("%.2f", calculateInsurance()) + "/day | Policy: " + getInsurancePolicyNumber();
    }
}

// ==================== MAIN CLASS ====================
public class VehicleRentalSystem {
    public static void main(String[] args) {
        int days = 5;

        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Car("MH12AB1234", 2000, "CAR-POL-001"));
        vehicles.add(new Bike("DL5SCD5678", 500, "BIKE-POL-002"));
        vehicles.add(new Truck("GJ01XY9999", 5000, "TRUCK-POL-003"));

        System.out.println("===== VEHICLE RENTAL SYSTEM =====\n");

        // Polymorphism - iterating over Vehicle references
        for (Vehicle v : vehicles) {
            System.out.println("----------------------------------");
            v.displayDetails(days);

            if (v instanceof Insurable) {
                Insurable ins = (Insurable) v;
                System.out.println(ins.getInsuranceDetails());
                System.out.printf("Total Insurance (5 days): ₹%.2f%n", ins.calculateInsurance() * days);
            }
        }
        System.out.println("----------------------------------");
    }
}
