public class FuelCheckpoint extends Checkpoint {
    public FuelCheckpoint(String id, String location, double dist, double expected, double actual) {
        super(id, location, dist, expected, actual);
    }

    @Override public double calculatePenalty() { return isDelayed() ? 10 : 0; }
    @Override public boolean isCritical() { return true; }
    @Override public String getType() { return "FuelCheckpoint"; }
}
