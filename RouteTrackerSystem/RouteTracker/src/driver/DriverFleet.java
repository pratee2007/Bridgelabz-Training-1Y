package driver;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Fleet manager that holds and manages all registered drivers.
 * Provides search, ranking, and fleet-wide analytics.
 */
public class DriverFleet {

    private List<Driver> drivers;

    public DriverFleet() {
        this.drivers = new ArrayList<>();
    }

    // ── Driver Registration ──────────────────────────

    public void registerDriver(Driver driver) {
        if (driver == null) throw new IllegalArgumentException("Driver cannot be null.");
        drivers.add(driver);
    }

    public boolean removeDriver(String driverId) {
        return drivers.removeIf(d -> d.getDriverId().equals(driverId));
    }

    public Optional<Driver> findDriver(String driverId) {
        return drivers.stream()
                      .filter(d -> d.getDriverId().equals(driverId))
                      .findFirst();
    }

    public List<Driver> getAllDrivers() {
        return new ArrayList<>(drivers);
    }

    // ── Fleet Analytics ──────────────────────────────

    /** Returns the driver with the highest route score. */
    public Optional<Driver> getBestDriver() {
        return drivers.stream()
                      .max(Comparator.comparingDouble(Driver::getRouteScore));
    }

    /** Returns all drivers sorted by route score descending. */
    public List<Driver> getRanking() {
        List<Driver> sorted = new ArrayList<>(drivers);
        sorted.sort((a, b) -> Double.compare(b.getRouteScore(), a.getRouteScore()));
        return sorted;
    }

    /** Total fleet-wide penalty. */
    public double getFleetTotalPenalty() {
        return drivers.stream().mapToDouble(Driver::getTotalPenalty).sum();
    }

    /** Total fleet-wide distance. */
    public double getFleetTotalDistance() {
        return drivers.stream().mapToDouble(Driver::getTotalDistance).sum();
    }

    /** Average route score across all drivers. */
    public double getAverageRouteScore() {
        return drivers.stream().mapToDouble(Driver::getRouteScore).average().orElse(0);
    }

    /** Drivers with route consistency issues (missing critical checkpoints). */
    public List<Driver> getInconsistentDrivers() {
        List<Driver> result = new ArrayList<>();
        for (Driver d : drivers) {
            if (!d.isRouteConsistent()) result.add(d);
        }
        return result;
    }

    public int getFleetSize() { return drivers.size(); }
}
