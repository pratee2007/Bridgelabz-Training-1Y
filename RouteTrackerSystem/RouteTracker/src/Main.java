import system.RouteTrackerEngine;

/**
 * ╔══════════════════════════════════════════════════╗
 * ║     LOGISTICS ROUTE TRACKER SYSTEM               ║
 * ║     Fully Automated — OOP + Generic LinkedList   ║
 * ╚══════════════════════════════════════════════════╝
 *
 * File Structure:
 *   checkpoint/
 *     Checkpoint.java          ← Abstract base
 *     DeliveryCheckpoint.java  ← penalty = delay × 2
 *     FuelCheckpoint.java      ← penalty = flat 10
 *     RestCheckpoint.java      ← penalty = delay × 0.5 (if actual > 30 min)
 *     InspectionCheckpoint.java← penalty = delay × 1.5
 *   list/
 *     RouteLinkedList.java     ← Generic T extends Checkpoint
 *   driver/
 *     Driver.java              ← Owns a RouteLinkedList
 *     DriverFleet.java         ← Manages all drivers, fleet analytics
 *   system/
 *     RouteSimulator.java      ← Auto-generates realistic routes
 *     RouteTrackerEngine.java  ← Orchestrates everything
 *   report/
 *     RouteReportPrinter.java  ← Formatted console output
 *   Main.java                  ← Entry point
 */
public class Main {
    public static void main(String[] args) {
        new RouteTrackerEngine().run();
    }
}
