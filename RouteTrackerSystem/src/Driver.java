public class Driver {
    private String driverId;
    private String name;
    private RouteLinkedList<Checkpoint> routeHistory = new RouteLinkedList<>();

    public Driver(String driverId, String name) {
        this.driverId = driverId;
        this.name = name;
    }

    public void addCheckpoint(Checkpoint cp) {
        routeHistory.addCheckpoint(cp);
        DatabaseManager.saveCheckpoint(driverId, cp);
    }

    public boolean removeCheckpoint(String id) {
        boolean removed = routeHistory.removeCheckpoint(id);
        if (removed) {
            DatabaseManager.deleteCheckpoint(id);
        }
        return removed;
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
        System.out.println("Critical Route Check: " +
            (routeHistory.isConsistent() ? "All required checkpoints present"
                                         : "WARNING - Missing critical checkpoints!"));
    }

    public String getDriverId() { return driverId; }
    public String getName() { return name; }
}
