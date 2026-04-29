import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static Driver driver = null;
    static DatabaseManager database = null;

    static String read() { return sc.nextLine().trim(); }

    static double readDouble() {
        while (true) {
            try {
                return Double.parseDouble(read());
            } catch (Exception e) {
                System.out.print("  Enter a valid number: ");
            }
        }
    }

    static int readInt() {
        try {
            return Integer.parseInt(read());
        } catch (Exception e) {
            return -1;
        }
    }

    public static void main(String[] args) {
        System.out.println("==============================");
        System.out.println("   ROUTE TRACKER SYSTEM");
        System.out.println("==============================");

        connectDatabase();

        System.out.print("Enter Driver ID   : ");
        String id = read();
        System.out.print("Enter Driver Name : ");
        String name = read();
        driver = new Driver(id, name, database);
        driver.saveToDatabase();
        System.out.println("Driver created!\n");

        boolean running = true;
        while (running) {
            System.out.println("------------------------------");
            System.out.println("1. Add Checkpoint");
            System.out.println("2. Remove Checkpoint");
            System.out.println("3. Find Checkpoint");
            System.out.println("4. View Route Summary");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            switch (readInt()) {
                case 1 -> addCheckpoint();
                case 2 -> removeCheckpoint();
                case 3 -> findCheckpoint();
                case 4 -> driver.printSummary();
                case 5 -> running = false;
                default -> System.out.println("Invalid. Enter 1-5.");
            }
        }

        closeDatabase();
        System.out.println("\nThank you! Goodbye.");
    }

    static void connectDatabase() {
        System.out.println("MySQL database: localhost:3306 / route_tracker_system");
        System.out.print("Enter MySQL username (example root): ");
        String username = read();
        System.out.print("Enter MySQL password (press Enter if blank): ");
        String password = read();

        try {
            database = new DatabaseManager(username, password);
            System.out.println("Database connected!\n");
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
            System.out.println("Run database.sql in MySQL Workbench, add MySQL Connector/J, then try again.\n");
        }
    }

    static void addCheckpoint() {
        System.out.println("\n  Checkpoint Type:");
        System.out.println("  1. Delivery  (penalty = delay x 2)");
        System.out.println("  2. Fuel      (penalty = flat 10 if delayed)");
        System.out.println("  3. Rest      (penalty = delay x 0.5 if actual > 30 min)");
        System.out.print("  Choose type (1/2/3): ");
        int type = readInt();
        if (type < 1 || type > 3) {
            System.out.println("  Invalid type.");
            return;
        }

        System.out.print("  Checkpoint ID           : ");
        String id = read();
        System.out.print("  Location Name           : ");
        String loc = read();
        System.out.print("  Distance from last (km) : ");
        double dist = readDouble();
        System.out.print("  Expected duration (min) : ");
        double exp = readDouble();
        System.out.print("  Actual duration   (min) : ");
        double act = readDouble();

        Checkpoint cp = switch (type) {
            case 1 -> new DeliveryCheckpoint(id, loc, dist, exp, act);
            case 2 -> new FuelCheckpoint(id, loc, dist, exp, act);
            default -> new RestCheckpoint(id, loc, dist, exp, act);
        };

        driver.addCheckpoint(cp);
        System.out.println("  Checkpoint added: " + cp + "\n");
    }

    static void removeCheckpoint() {
        System.out.print("  Checkpoint ID to remove: ");
        String id = read();
        boolean removed = driver.removeCheckpoint(id);

        if (removed) {
            System.out.println("  Removed successfully.");
        } else {
            System.out.println("  Not found.");
        }
    }

    static void findCheckpoint() {
        System.out.print("  Checkpoint ID to find: ");
        String id = read();
        Checkpoint cp = driver.findCheckpoint(id);
        System.out.println(cp != null ? "  Found: " + cp : "  Not found.");
    }

    static void closeDatabase() {
        if (database == null) return;

        try {
            database.close();
        } catch (SQLException e) {
            System.out.println("Database close failed: " + e.getMessage());
        }
    }
}
