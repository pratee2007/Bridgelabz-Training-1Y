package checkpoint;

/**
 * Represents a fuel station stop on the route.
 * Penalty = flat 10 if delayed, 0 otherwise.
 * Critical checkpoint — must be present for route consistency.
 */
public class FuelCheckpoint extends Checkpoint {

    private String stationName;
    private double fuelLitres;

    public FuelCheckpoint(String checkpointId, String locationName,
                           double distanceFromLast, double expectedDuration,
                           double actualDuration, String stationName, double fuelLitres) {
        super(checkpointId, locationName, distanceFromLast, expectedDuration, actualDuration);
        this.stationName = stationName;
        this.fuelLitres  = fuelLitres;
    }

    /** Simplified constructor */
    public FuelCheckpoint(String checkpointId, String locationName,
                           double distanceFromLast, double expectedDuration, double actualDuration) {
        this(checkpointId, locationName, distanceFromLast, expectedDuration, actualDuration, locationName, 0.0);
    }

    @Override
    public double calculatePenalty() {
        return isDelayed() ? 10.0 : 0.0;
    }

    @Override public boolean isCritical() { return true; }
    @Override public String  getType()    { return "FuelCheckpoint"; }

    public String getStationName() { return stationName; }
    public double getFuelLitres()  { return fuelLitres; }
}
