package system;

import checkpoint.*;
import driver.*;
import report.RouteReportPrinter;

/**
 * The central engine of the Route Tracker System.
 * Automatically creates drivers, simulates routes, and runs analytics.
 */
public class RouteTrackerEngine {

    private DriverFleet fleet;

    public RouteTrackerEngine() {
        this.fleet = new DriverFleet();
    }

    /** Full automatic run: register drivers → simulate routes → print reports */
    public void run() {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║          LOGISTICS ROUTE TRACKER SYSTEM — AUTO MODE         ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

        // ── Step 1: Register Drivers ──────────────────
        registerDrivers();

        // ── Step 2: Assign Manual Routes (sample driver) ─
        assignManualRoute();

        // ── Step 3: Auto-Simulate Routes for remaining drivers ─
        autoSimulateRoutes();

        // ── Step 4: Print Individual Reports ─────────
        System.out.println("\n\n════════════════ INDIVIDUAL DRIVER REPORTS ════════════════");
        for (Driver d : fleet.getAllDrivers()) {
            RouteReportPrinter.printDriverReport(d);
        }

        // ── Step 5: Print Fleet Report ────────────────
        System.out.println("\n\n════════════════ FLEET ANALYTICS REPORT ════════════════════");
        RouteReportPrinter.printFleetReport(fleet);

        // ── Step 6: Demo Operations ───────────────────
        demoOperations();
    }

    // ── Private Steps ────────────────────────────────

    private void registerDrivers() {
        System.out.println("\n[ENGINE] Registering drivers...");

        fleet.registerDriver(new Driver("D1204", "Kavita Nair",    "MH-12-AB-1234", "+91-9900000001"));
        fleet.registerDriver(new Driver("D1205", "Arjun Mehta",    "DL-01-CD-5678", "+91-9900000002"));
        fleet.registerDriver(new Driver("D1206", "Priya Sharma",   "KA-09-EF-9101", "+91-9900000003"));
        fleet.registerDriver(new Driver("D1207", "Ravi Tiwari",    "UP-32-GH-1121", "+91-9900000004"));
        fleet.registerDriver(new Driver("D1208", "Meena Pillai",   "TN-22-IJ-3141", "+91-9900000005"));

        System.out.println("[ENGINE] " + fleet.getFleetSize() + " drivers registered.");
    }

    private void assignManualRoute() {
        System.out.println("[ENGINE] Building manual route for D1204 — Kavita Nair...");

        Driver kavita = fleet.findDriver("D1204").orElseThrow();

        // Exactly matching the sample output
        kavita.addCheckpoint(new DeliveryCheckpoint("CP01", "Warehouse A",
            30.0, 40.0, 50.0, "PKG-1001", "Client Alpha"));

        kavita.addCheckpoint(new FuelCheckpoint("CP02", "Pump 12",
            20.0, 15.0, 15.0, "HP Station", 45.0));

        kavita.addCheckpoint(new RestCheckpoint("CP03", "Motel X",
            25.0, 60.0, 65.0, "Motel"));

        kavita.addCheckpoint(new DeliveryCheckpoint("CP04", "Client Hub",
            45.0, 35.0, 50.0, "PKG-1002", "Client Beta"));

        System.out.println("[ENGINE] Manual route assigned (4 checkpoints).");
    }

    private void autoSimulateRoutes() {
        System.out.println("[ENGINE] Auto-simulating routes for remaining drivers...");

        long seed = 42;
        for (Driver d : fleet.getAllDrivers()) {
            if (d.getTotalCheckpoints() > 0) continue; // already assigned
            RouteSimulator.simulate(d, seed++, 6);
            System.out.printf("[ENGINE] Route simulated for %s — %s (%d checkpoints).%n",
                d.getDriverId(), d.getName(), d.getTotalCheckpoints());
        }
    }

    private void demoOperations() {
        System.out.println("\n\n════════════════ LIVE OPERATION DEMO ═══════════════════════");

        Driver kavita = fleet.findDriver("D1204").orElseThrow();

        // Find a checkpoint
        System.out.println("\n[FIND] Searching for checkpoint CP03 on driver D1204:");
        Checkpoint found = kavita.findCheckpoint("CP03");
        System.out.println("  → Found: " + (found != null ? found : "Not found"));

        // Remove a checkpoint and re-check consistency
        System.out.println("\n[REMOVE] Removing FuelCheckpoint CP02 from D1204...");
        boolean removed = kavita.removeCheckpoint("CP02");
        System.out.println("  → Remove successful: " + removed);
        System.out.println("  → Route consistent after removal: " + kavita.isRouteConsistent()
            + " (expected: false — no Fuel stop)");

        // Re-add fuel checkpoint
        System.out.println("\n[ADD] Re-adding FuelCheckpoint CP02...");
        kavita.addCheckpoint(new FuelCheckpoint("CP02", "Pump 12",
            20.0, 15.0, 15.0, "HP Station", 45.0));
        System.out.println("  → Route consistent after re-add: " + kavita.isRouteConsistent()
            + " (expected: true)");

        // Fleet best driver
        System.out.println("\n[RANK] Fleet best driver:");
        fleet.getBestDriver().ifPresent(best ->
            System.out.printf("  → %s — %s | Score: %.1f%n",
                best.getDriverId(), best.getName(), best.getRouteScore())
        );

        System.out.println("\n[ENGINE] System run complete.");
        System.out.println("═".repeat(65));
    }
}
