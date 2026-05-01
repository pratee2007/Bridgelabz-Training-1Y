package checkpoint;

/**
 * Represents a toll / inspection checkpoint (e.g., border, weigh station).
 * Penalty = delay * 1.5 — inspections have moderate impact.
 * Non-critical by default, but can be marked critical.
 */
public class InspectionCheckpoint extends Checkpoint {

    private String inspectionType; // e.g. "Toll", "Weigh Station", "Border Check"
    private boolean critical;

    public InspectionCheckpoint(String checkpointId, String locationName,
                                 double distanceFromLast, double expectedDuration,
                                 double actualDuration, String inspectionType, boolean critical) {
        super(checkpointId, locationName, distanceFromLast, expectedDuration, actualDuration);
        this.inspectionType = inspectionType;
        this.critical       = critical;
    }

    /** Simplified constructor — non-critical toll by default */
    public InspectionCheckpoint(String checkpointId, String locationName,
                                 double distanceFromLast, double expectedDuration, double actualDuration) {
        this(checkpointId, locationName, distanceFromLast, expectedDuration, actualDuration, "Toll", false);
    }

    @Override
    public double calculatePenalty() {
        if (!isDelayed()) return 0.0;
        return getDelayMinutes() * 1.5;
    }

    @Override public boolean isCritical() { return critical; }
    @Override public String  getType()    { return "InspectionCheckpoint"; }

    public String getInspectionType() { return inspectionType; }
}
