package system;

import checkpoint.*;
import driver.Driver;

import java.util.Random;

/**
 * Automatically simulates realistic routes for drivers.
 * Generates random checkpoints with controlled delay scenarios.
 */
public class RouteSimulator {

    private static final Random RNG = new Random();

    // Location pools
    private static final String[] WAREHOUSES  = {"Warehouse A", "Depot Mumbai", "Hub Delhi", "Centre Pune", "Store Surat"};
    private static final String[] FUEL_PUMPS  = {"Pump 12", "HP Station", "BPCL Outlet", "Indian Oil Stop", "Shell Petrol"};
    private static final String[] REST_SPOTS  = {"Motel X", "Roadside Dhaba", "Highway Inn", "Trucker's Rest", "NH Lounge"};
    private static final String[] CLIENTS     = {"Client Hub", "Retail Plaza", "City Mall", "Tech Park", "Factory Gate"};
    private static final String[] TOLL_GATES  = {"NH-44 Toll", "Ring Road Toll", "Expressway Booth", "City Bypass Toll"};

    /**
     * Builds a full automatic route for the given driver.
     * Simulates real-world mix of checkpoints with randomized delays.
     *
     * @param driver       Driver to assign the route to
     * @param seed         Random seed for reproducibility (use -1 for truly random)
     * @param totalStops   Number of checkpoints to generate
     */
    public static void simulate(Driver driver, long seed, int totalStops) {
        Random rng = (seed >= 0) ? new Random(seed) : RNG;

        // Always start with a delivery, always include at least one fuel stop
        int stops = Math.max(totalStops, 4);

        for (int i = 0; i < stops; i++) {
            String cpId   = driver.getDriverId() + "_CP" + String.format("%02d", i + 1);
            double dist   = 10 + rng.nextDouble() * 40;          // 10–50 km
            double expDur = 15 + rng.nextDouble() * 45;          // 15–60 min expected
            double actual = simulateActual(expDur, rng);          // maybe delayed

            // Checkpoint type selection: ensure first=delivery, at least one fuel
            Checkpoint cp;
            if (i == 0 || (i == stops / 2 && !hasDelivery(driver))) {
                cp = makeDelivery(cpId, dist, expDur, actual, rng);
            } else if (i == 1 || i == stops - 2) {
                cp = makeFuel(cpId, dist, expDur, actual, rng);
            } else if (i % 4 == 3) {
                cp = makeRest(cpId, dist, expDur, actual, rng);
            } else if (i % 5 == 4) {
                cp = makeInspection(cpId, dist, expDur, actual, rng);
            } else {
                cp = makeDelivery(cpId, dist, expDur, actual, rng);
            }
            driver.addCheckpoint(cp);
        }
    }

    /** Convenience: simulate with default 6 stops */
    public static void simulate(Driver driver) {
        simulate(driver, -1, 6);
    }

    // ── Private Helpers ──────────────────────────────

    private static double simulateActual(double expected, Random rng) {
        int roll = rng.nextInt(10);
        if (roll < 4) return expected;                              // 40% on time
        if (roll < 7) return expected + 5 + rng.nextDouble() * 15; // 30% slight delay
        return expected + 20 + rng.nextDouble() * 30;              // 30% heavy delay
    }

    private static boolean hasDelivery(Driver driver) {
        return driver.getRouteHistory().getAllCheckpoints().stream()
               .anyMatch(c -> c instanceof DeliveryCheckpoint);
    }

    private static DeliveryCheckpoint makeDelivery(String id, double dist, double exp, double act, Random rng) {
        String loc = CLIENTS[rng.nextInt(CLIENTS.length)];
        return new DeliveryCheckpoint(id, loc, dist, exp, act,
               "PKG-" + (1000 + rng.nextInt(9000)), "Customer " + (char)('A' + rng.nextInt(26)));
    }

    private static FuelCheckpoint makeFuel(String id, double dist, double exp, double act, Random rng) {
        String loc = FUEL_PUMPS[rng.nextInt(FUEL_PUMPS.length)];
        return new FuelCheckpoint(id, loc, dist, exp, act, loc, 20 + rng.nextDouble() * 60);
    }

    private static RestCheckpoint makeRest(String id, double dist, double exp, double act, Random rng) {
        String loc      = REST_SPOTS[rng.nextInt(REST_SPOTS.length)];
        String facility = new String[]{"Motel", "Dhaba", "Rest Area"}[rng.nextInt(3)];
        return new RestCheckpoint(id, loc, dist, exp, act, facility);
    }

    private static InspectionCheckpoint makeInspection(String id, double dist, double exp, double act, Random rng) {
        String loc  = TOLL_GATES[rng.nextInt(TOLL_GATES.length)];
        String type = new String[]{"Toll", "Weigh Station", "Border Check"}[rng.nextInt(3)];
        return new InspectionCheckpoint(id, loc, dist, exp, act, type, false);
    }
}
