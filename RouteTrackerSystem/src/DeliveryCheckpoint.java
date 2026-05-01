public class DeliveryCheckpoint extends Checkpoint {
    public DeliveryCheckpoint(String id, String location, double dist, double expected, double actual) {
        super(id, location, dist, expected, actual);
    }

    @Override public double calculatePenalty() { return isDelayed() ? getDelay() * 2 : 0; }
    @Override public boolean isCritical() { return true; }
    @Override public String getType() { return "DeliveryCheckpoint"; }
}
