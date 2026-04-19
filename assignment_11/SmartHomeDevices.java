// ==================== SINGLE INHERITANCE ====================
// Superclass
class Device {
    private String deviceId;
    private String status;

    public Device(String deviceId, String status) {
        this.deviceId = deviceId;
        this.status = status;
    }

    public String getDeviceId() { return deviceId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public void displayStatus() {
        System.out.println("Device ID : " + deviceId);
        System.out.println("Status    : " + status);
    }
}

// Subclass (Single Inheritance)
class Thermostat extends Device {
    private double temperatureSetting;
    private String mode; // "Cooling" or "Heating"

    public Thermostat(String deviceId, String status, double temperatureSetting, String mode) {
        super(deviceId, status); // Call superclass constructor
        this.temperatureSetting = temperatureSetting;
        this.mode = mode;
    }

    public double getTemperatureSetting() { return temperatureSetting; }
    public void setTemperatureSetting(double temp) { this.temperatureSetting = temp; }

    public String getMode() { return mode; }
    public void setMode(String mode) { this.mode = mode; }

    @Override
    public void displayStatus() {
        super.displayStatus(); // Call superclass method
        System.out.println("Temperature: " + temperatureSetting + "°C");
        System.out.println("Mode       : " + mode);
    }
}

// ==================== MAIN CLASS ====================
public class SmartHomeDevices {
    public static void main(String[] args) {
        Thermostat t1 = new Thermostat("THM-001", "Active", 22.5, "Cooling");
        Thermostat t2 = new Thermostat("THM-002", "Inactive", 18.0, "Heating");

        System.out.println("===== SMART HOME DEVICE STATUS =====\n");

        System.out.println("--- Thermostat 1 ---");
        t1.displayStatus();

        System.out.println("\n--- Thermostat 2 ---");
        t2.displayStatus();

        // Update settings
        System.out.println("\n--- Updating Thermostat 2 Settings ---");
        t2.setStatus("Active");
        t2.setTemperatureSetting(24.0);
        t2.setMode("Cooling");
        t2.displayStatus();
    }
}
