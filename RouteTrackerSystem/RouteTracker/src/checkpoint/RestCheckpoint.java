package checkpoint;

/**
 * Represents a rest/break stop on the route.
 * Penalty = delay * 0.5 only if actual duration > 30 minutes AND delayed.
 * Non-critical — route can be valid without a rest stop.
 */
public class RestCheckpoint extends Checkpoint {

    private static final double MIN_DURATION_FOR_PENALTY = 30.0; // minutes
    private String facilityType; // e.g. "Motel", "Dhaba", "Rest Area"

    public RestCheckpoint(String checkpointId, String locationName,
                           double distanceFromLast, double expectedDuration,
                           double actualDuration, String facilityType) {
        super(checkpointId, locationName, distanceFromLast, expectedDuration, actualDuration);
        this.facilityType = facilityType;
    }

    /** Simplified constructor */
    public RestCheckpoint(String checkpointId, String locationName,
                           double distanceFromLast, double expectedDuration, double actualDuration) {
        this(checkpointId, locationName, distanceFromLast, expectedDuration, actualDuration, "Rest Area");
    }

    @Override
    public double calculatePenalty() {
        // Penalty only when delayed AND actual rest took more than 30 minutes
        if (!isDelayed() || actualDuration <= MIN_DURATION_FOR_PENALTY) return 0.0;
        return getDelayMinutes() * 0.5;
    }

    @Override public boolean isCritical() { return false; }
    @Override public String  getType()    { return "RestCheckpoint"; }

    public String getFacilityType() { return facilityType; }
}
