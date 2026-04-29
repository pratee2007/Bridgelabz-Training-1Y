import java.sql.SQLException;

public class Driver {
    private String driverId;
    private String name;
    private RouteLinkedList<Checkpoint> routeHistory = new RouteLinkedList<>();
    private DatabaseManager database;

    public Driver(String driverId, String name) {
        this.driverId = driverId;
        this.name = name;
    }

    public Driver(String driverId, String name, DatabaseManager database) {
        this(driverId, name);
        this.database = database;
    }

    public void saveToDatabase() {
        if (database == null) return;

        try {
            database.saveDriver(this);
        } catch (SQLException e) {
            System.out.println("Could not save driver to database: " + e.getMessage());
        }
    }

    public void addCheckpoint(Checkpoint cp) {
        routeHistory.addCheckpoint(cp);

        if (database == null) return;
        try {
            database.saveCheckpoint(driverId, cp);
        } catch (SQLException e) {
            System.out.println("  Could not save checkpoint to database: " + e.getMessage());
        }
    }

    public boolean removeCheckpoint(String id) {
        boolean removed = routeHistory.removeCheckpoint(id);
        if (!removed || database == null) return removed;

        try {
            database.deleteCheckpoint(id);
        } catch (SQLException e) {
            System.out.println("  Could not delete checkpoint from database: " + e.getMessage());
        }
        return true;
    }

    public Checkpoint findCheckpoint(String id) { return routeHistory.findCheckpoint(id); }

    public void printSummary() {
        System.out.println("\nDriver: " + driverId + " - " + name);
        System.out.println("Route Summary:");
        routeHistory.printRoute();

        double dist = routeHistory.computeTotalDistance();
        double penalty = routeHistory.computeTotalPenalty();
        double score = dist - penalty;

        System.out.println("Total Distance: " + dist + " km");
        System.out.println("Total Penalty: " + penalty);
        System.out.println("Route Score: " + score);
        System.out.println("Critical Route Check: "
                + (routeHistory.isConsistent() ? "All required checkpoints present"
                : "WARNING - Missing critical checkpoints!"));
    }

    public String getDriverId() { return driverId; }
    public String getName() { return name; }
}
