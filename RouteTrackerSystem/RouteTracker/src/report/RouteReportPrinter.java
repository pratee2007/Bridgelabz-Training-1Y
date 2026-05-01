package report;

import checkpoint.Checkpoint;
import driver.Driver;
import driver.DriverFleet;

import java.util.List;

/**
 * Prints formatted console reports for individual drivers and the full fleet.
 */
public class RouteReportPrinter {

    private static final String LINE  = "─".repeat(80);
    private static final String DLINE = "═".repeat(80);

    // ── Single Driver Report ─────────────────────────

    public static void printDriverReport(Driver driver) {
        System.out.println("\n" + DLINE);
        System.out.printf(" DRIVER REPORT: %s — %s%n", driver.getDriverId(), driver.getName());
        System.out.printf(" Vehicle: %-15s  Contact: %s%n", driver.getVehicleNumber(), driver.getContactNumber());
        System.out.println(DLINE);

        System.out.println(" Route Summary:");
        System.out.println(" " + LINE);
        driver.getRouteHistory().printRoute();
        System.out.println(" " + LINE);

        double dist    = driver.getTotalDistance();
        double penalty = driver.getTotalPenalty();
        double score   = driver.getRouteScore();
        int    delayed = driver.getDelayedCount();
        int    total   = driver.getTotalCheckpoints();

        System.out.printf(" Total Distance   : %.1f km%n", dist);
        System.out.printf(" Total Penalty    : %.1f%n", penalty);
        System.out.printf(" Route Score      : %.1f%n", score);
        System.out.printf(" Delayed Stops    : %d / %d%n", delayed, total);

        String consistencyMsg = driver.isRouteConsistent()
            ? "✔  All required checkpoints present"
            : "✘  WARNING — Missing critical checkpoints!";
        System.out.println(" Critical Check   : " + consistencyMsg);

        System.out.println(" Performance      : " + performanceLabel(score));
        System.out.println(DLINE);
    }

    // ── Fleet Report ─────────────────────────────────

    public static void printFleetReport(DriverFleet fleet) {
        System.out.println("\n" + DLINE);
        System.out.println("  FLEET ANALYTICS REPORT");
        System.out.println(DLINE);
        System.out.printf("  Total Drivers        : %d%n", fleet.getFleetSize());
        System.out.printf("  Fleet Total Distance : %.1f km%n", fleet.getFleetTotalDistance());
        System.out.printf("  Fleet Total Penalty  : %.1f%n", fleet.getFleetTotalPenalty());
        System.out.printf("  Average Route Score  : %.1f%n", fleet.getAverageRouteScore());

        List<Driver> inconsistent = fleet.getInconsistentDrivers();
        if (inconsistent.isEmpty()) {
            System.out.println("  Consistency Issues   : None — all routes valid ✔");
        } else {
            System.out.println("  Consistency Issues   : " + inconsistent.size() + " driver(s) flagged ✘");
            for (Driver d : inconsistent)
                System.out.println("    → " + d.getDriverId() + " — " + d.getName());
        }

        System.out.println("\n  DRIVER RANKING (by Route Score):");
        System.out.println("  " + LINE);
        System.out.printf("  %-4s %-10s %-20s %8s %8s %8s%n",
            "Rank", "ID", "Name", "Distance", "Penalty", "Score");
        System.out.println("  " + LINE);

        List<Driver> ranked = fleet.getRanking();
        for (int i = 0; i < ranked.size(); i++) {
            Driver d = ranked.get(i);
            System.out.printf("  %-4d %-10s %-20s %7.1f  %7.1f  %7.1f%n",
                i + 1, d.getDriverId(), d.getName(),
                d.getTotalDistance(), d.getTotalPenalty(), d.getRouteScore());
        }

        fleet.getBestDriver().ifPresent(best ->
            System.out.printf("%n  ★ BEST DRIVER: %s — %s (Score: %.1f)%n",
                best.getDriverId(), best.getName(), best.getRouteScore())
        );
        System.out.println(DLINE);
    }

    // ── Helpers ──────────────────────────────────────

    private static String performanceLabel(double score) {
        if (score >= 150) return "★★★ EXCELLENT";
        if (score >= 100) return "★★  GOOD";
        if (score >= 50)  return "★   AVERAGE";
        return            "    POOR — Needs Improvement";
    }
}
