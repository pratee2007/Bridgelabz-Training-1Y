package checkpoint;

/**
 * Represents a delivery stop on the route.
 * Penalty = (actual - expected) * 2 for any delay.
 * Critical checkpoint — must be present for route consistency.
 */
public class DeliveryCheckpoint extends Checkpoint {

    private String packageId;
    private String recipientName;

    public DeliveryCheckpoint(String checkpointId, String locationName,
                               double distanceFromLast, double expectedDuration,
                               double actualDuration, String packageId, String recipientName) {
        super(checkpointId, locationName, distanceFromLast, expectedDuration, actualDuration);
        this.packageId     = packageId;
        this.recipientName = recipientName;
    }

    /** Simplified constructor without package details */
    public DeliveryCheckpoint(String checkpointId, String locationName,
                               double distanceFromLast, double expectedDuration, double actualDuration) {
        this(checkpointId, locationName, distanceFromLast, expectedDuration, actualDuration, "N/A", "N/A");
    }

    @Override
    public double calculatePenalty() {
        if (!isDelayed()) return 0.0;
        return getDelayMinutes() * 2.0;
    }

    @Override public boolean isCritical() { return true; }
    @Override public String  getType()    { return "DeliveryCheckpoint"; }

    public String getPackageId()     { return packageId; }
    public String getRecipientName() { return recipientName; }
}
