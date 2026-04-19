import java.util.*;

// ==================== INTERFACE ====================
interface GPS {
    String getCurrentLocation();
    void updateLocation(String newLocation);
}

// ==================== ABSTRACT CLASS ====================
abstract class RideVehicle implements GPS {
    private String vehicleId;
    private String driverName;      // Encapsulated
    private double ratePerKm;
    private String currentLocation;

    public RideVehicle(String vehicleId, String driverName, double ratePerKm, String location) {
        this.vehicleId = vehicleId;
        this.driverName = driverName;
        this.ratePerKm = ratePerKm;
        this.currentLocation = location;
    }

    // Getters and Setters (Encapsulation)
    public String getVehicleId() { return vehicleId; }

    protected String getDriverName() { return driverName; }  // Restricted
    protected void setDriverName(String name) { this.driverName = name; }

    public double getRatePerKm() { return ratePerKm; }
    public void setRatePerKm(double rate) { this.ratePerKm = rate; }

    // GPS interface methods
    @Override
    public String getCurrentLocation() { return currentLocation; }

    @Override
    public void updateLocation(String newLocation) {
        System.out.println("📍 " + vehicleId + " location updated: " + currentLocation + " → " + newLocation);
        this.currentLocation = newLocation;
    }

    // Abstract method
    public abstract double calculateFare(double distance);

    // Concrete method
    public void getVehicleDetails() {
        System.out.println("Vehicle ID   : " + vehicleId);
        System.out.println("Driver       : " + driverName);
        System.out.println("Rate/km      : ₹" + ratePerKm);
        System.out.println("Location     : " + currentLocation);
    }
}

// ==================== SUBCLASSES ====================
class RideCar extends RideVehicle {
    private double baseFare = 50.0;

    public RideCar(String id, String driver, double rate, String location) {
        super(id, driver, rate, location);
    }

    @Override
    public double calculateFare(double distance) {
        return baseFare + (getRatePerKm() * distance); // Base fare + per km charge
    }
}

class RideBike extends RideVehicle {
    public RideBike(String id, String driver, double rate, String location) {
        super(id, driver, rate, location);
    }

    @Override
    public double calculateFare(double distance) {
        return getRatePerKm() * distance; // No base fare for bikes
    }
}

class Auto extends RideVehicle {
    private double baseFare = 25.0;

    public Auto(String id, String driver, double rate, String location) {
        super(id, driver, rate, location);
    }

    @Override
    public double calculateFare(double distance) {
        return baseFare + (getRatePerKm() * distance * 1.1); // Slight surge for autos
    }
}

// ==================== MAIN CLASS ====================
public class RideHailingApplication {

    // Polymorphism - calculates fare dynamically for any vehicle type
    public static void calculateFares(List<RideVehicle> vehicles, double distance) {
        System.out.println("===== RIDE-HAILING APPLICATION =====");
        System.out.printf("Trip Distance: %.1f km%n%n", distance);

        for (RideVehicle v : vehicles) {
            System.out.println("------------------------------------");
            v.getVehicleDetails();
            System.out.printf("Estimated Fare: ₹%.2f%n", v.calculateFare(distance));
        }
        System.out.println("------------------------------------");
    }

    public static void main(String[] args) {
        List<RideVehicle> vehicles = new ArrayList<>();

        RideCar car = new RideCar("CAR-001", "Ravi Kumar", 15, "Connaught Place, Delhi");
        RideBike bike = new RideBike("BIKE-002", "Suresh Nair", 8, "Lajpat Nagar, Delhi");
        Auto auto = new Auto("AUTO-003", "Mahesh Yadav", 10, "Karol Bagh, Delhi");

        vehicles.add(car);
        vehicles.add(bike);
        vehicles.add(auto);

        // Show fare estimates for a 10km trip
        calculateFares(vehicles, 10.0);

        // Demonstrate GPS tracking
        System.out.println("\n===== GPS TRACKING =====");
        car.updateLocation("Nehru Place, Delhi");
        bike.updateLocation("Saket, Delhi");

        System.out.println("\nCurrent Locations:");
        for (RideVehicle v : vehicles) {
            System.out.println(v.getVehicleId() + " → " + v.getCurrentLocation());
        }
    }
}
