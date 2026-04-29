public abstract class Checkpoint {
    protected String checkpointId;
    protected String locationName;
    protected double distanceFromLast;
    protected double expectedDuration;
    protected double actualDuration;

    public Checkpoint(String id, String location, double dist, double expected, double actual) {
        this.checkpointId = id;
        this.locationName = location;
        this.distanceFromLast = dist;
        this.expectedDuration = expected;
        this.actualDuration = actual;
    }

    public String getCheckpointId() { return checkpointId; }
    public String getLocationName() { return locationName; }
    public double getDistanceFromLast() { return distanceFromLast; }
    public double getExpectedDuration() { return expectedDuration; }
    public double getActualDuration() { return actualDuration; }

    public boolean isDelayed() { return actualDuration > expectedDuration; }
    public double getDelay() { return Math.max(0, actualDuration - expectedDuration); }

    public abstract double calculatePenalty();
    public abstract boolean isCritical();
    public abstract String getType();

    @Override
    public String toString() {
        return getType() + " - " + locationName
                + " - " + (isDelayed() ? "Delayed" : "On Time")
                + " - Penalty: " + calculatePenalty();
    }
}
