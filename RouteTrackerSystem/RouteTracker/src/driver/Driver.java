package driver;

import checkpoint.Checkpoint;
import list.RouteLinkedList;

/**
 * Represents a logistics driver with a full route history.
 * Manages adding, removing, and querying checkpoints.
 */
public class Driver {

    private String driverId;
    private String name;
    private String vehicleNumber;
    private String contactNumber;
    private RouteLinkedList<Checkpoint> routeHistory;

    public Driver(String driverId, String name, String vehicleNumber, String contactNumber) {
        this.driverId       = driverId;
        this.name           = name;
        this.vehicleNumber  = vehicleNumber;
        this.contactNumber  = contactNumber;
        this.routeHistory   = new RouteLinkedList<>();
    }

    /** Simplified constructor */
    public Driver(String driverId, String name) {
        this(driverId, name, "N/A", "N/A");
    }

    // ── Route Management ─────────────────────────────

    public void addCheckpoint(Checkpoint cp)              { routeHistory.addCheckpoint(cp); }
    public boolean removeCheckpoint(String id)            { return routeHistory.removeCheckpoint(id); }
    public Checkpoint findCheckpoint(String id)           { return routeHistory.findCheckpoint(id); }

    // ── Computed Metrics ─────────────────────────────

    public double getTotalDistance() { return routeHistory.computeTotalDistance(); }
    public double getTotalPenalty()  { return routeHistory.computeTotalPenalty(); }
    public double getRouteScore()    { return routeHistory.computeRouteScore(); }
    public boolean isRouteConsistent() { return routeHistory.isConsistent(); }
    public int getDelayedCount()     { return routeHistory.countDelayed(); }
    public int getTotalCheckpoints() { return routeHistory.getSize(); }

    // ── Getters ──────────────────────────────────────
    public String getDriverId()                         { return driverId; }
    public String getName()                             { return name; }
    public String getVehicleNumber()                    { return vehicleNumber; }
    public String getContactNumber()                    { return contactNumber; }
    public RouteLinkedList<Checkpoint> getRouteHistory(){ return routeHistory; }

    @Override
    public String toString() {
        return String.format("Driver[%s | %s | Vehicle: %s]", driverId, name, vehicleNumber);
    }
}
