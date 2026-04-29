public class RestCheckpoint extends Checkpoint {
    public RestCheckpoint(String id, String location, double dist, double expected, double actual) {
        super(id, location, dist, expected, actual);
    }

    @Override
    public double calculatePenalty() {
        return (isDelayed() && actualDuration > 30) ? getDelay() * 0.5 : 0;
    }

    @Override public boolean isCritical() { return false; }
    @Override public String getType() { return "RestCheckpoint"; }
}
