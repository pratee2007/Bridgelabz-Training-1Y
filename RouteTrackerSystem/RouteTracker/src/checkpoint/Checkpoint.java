package checkpoint;

/**
 * Abstract base class representing any checkpoint on a driver's route.
 * All checkpoint types must extend this class and implement penalty logic.
 */
public abstract class Checkpoint {

    protected String checkpointId;
    protected String locationName;
    protected double distanceFromLast;  // kilometers from previous checkpoint
    protected double expectedDuration;  // expected time in minutes
    protected double actualDuration;    // actual time taken in minutes

    public Checkpoint(String checkpointId, String locationName,
                      double distanceFromLast, double expectedDuration, double actualDuration) {
        this.checkpointId     = checkpointId;
        this.locationName     = locationName;
        this.distanceFromLast = distanceFromLast;
        this.expectedDuration = expectedDuration;
        this.actualDuration   = actualDuration;
    }

    // ── Getters ─────────────────────────────────────
    public String getCheckpointId()     { return checkpointId; }
    public String getLocationName()     { return locationName; }
    public double getDistanceFromLast() { return distanceFromLast; }
    public double getExpectedDuration() { return expectedDuration; }
    public double getActualDuration()   { return actualDuration; }

    /** Returns true when actual time exceeds expected time */
    public boolean isDelayed() {
        return actualDuration > expectedDuration;
    }

    /** Delay amount in minutes (0 if on time) */
    public double getDelayMinutes() {
        return Math.max(0, actualDuration - expectedDuration);
    }

    // ── Abstract contract ────────────────────────────
    public abstract double  calculatePenalty();
    public abstract boolean isCritical();
    public abstract String  getType();

    @Override
    public String toString() {
        return String.format("%-22s | %-20s | %-8s | Penalty: %5.1f | Dist: %5.1f km",
            getType(), locationName,
            isDelayed() ? "Delayed" : "On Time",
            calculatePenalty(), distanceFromLast);
    }
}
