import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/route_tracker_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "PRATEEK@";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public static void initializeDatabase() {
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS drivers (
                    driver_id VARCHAR(50) PRIMARY KEY,
                    name VARCHAR(100) NOT NULL
                )
                """);

            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS checkpoints (
                    checkpoint_id VARCHAR(50) PRIMARY KEY,
                    driver_id VARCHAR(50) NOT NULL,
                    checkpoint_type VARCHAR(40) NOT NULL,
                    location_name VARCHAR(150) NOT NULL,
                    distance_from_last DOUBLE NOT NULL,
                    expected_duration DOUBLE NOT NULL,
                    actual_duration DOUBLE NOT NULL,
                    penalty DOUBLE NOT NULL,
                    critical BOOLEAN NOT NULL,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    FOREIGN KEY (driver_id) REFERENCES drivers(driver_id)
                        ON DELETE CASCADE
                )
                """);

            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS delivery_checkpoints (
                    checkpoint_id VARCHAR(50) PRIMARY KEY,
                    delivery_penalty DOUBLE NOT NULL,
                    FOREIGN KEY (checkpoint_id) REFERENCES checkpoints(checkpoint_id)
                        ON DELETE CASCADE
                )
                """);

            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS fuel_checkpoints (
                    checkpoint_id VARCHAR(50) PRIMARY KEY,
                    fuel_penalty DOUBLE NOT NULL,
                    FOREIGN KEY (checkpoint_id) REFERENCES checkpoints(checkpoint_id)
                        ON DELETE CASCADE
                )
                """);

            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS rest_checkpoints (
                    checkpoint_id VARCHAR(50) PRIMARY KEY,
                    rest_penalty DOUBLE NOT NULL,
                    FOREIGN KEY (checkpoint_id) REFERENCES checkpoints(checkpoint_id)
                        ON DELETE CASCADE
                )
                """);
        } catch (SQLException e) {
            System.out.println("Database setup failed: " + e.getMessage());
        }
    }

    public static void saveDriver(Driver driver) {
        String sql = """
            INSERT INTO drivers (driver_id, name)
            VALUES (?, ?)
            ON DUPLICATE KEY UPDATE name = VALUES(name)
            """;

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, driver.getDriverId());
            ps.setString(2, driver.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Could not save driver: " + e.getMessage());
        }
    }

    public static void saveCheckpoint(String driverId, Checkpoint cp) {
        String sql = """
            INSERT INTO checkpoints (
                checkpoint_id, driver_id, checkpoint_type, location_name,
                distance_from_last, expected_duration, actual_duration,
                penalty, critical
            )
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
            ON DUPLICATE KEY UPDATE
                driver_id = VALUES(driver_id),
                checkpoint_type = VALUES(checkpoint_type),
                location_name = VALUES(location_name),
                distance_from_last = VALUES(distance_from_last),
                expected_duration = VALUES(expected_duration),
                actual_duration = VALUES(actual_duration),
                penalty = VALUES(penalty),
                critical = VALUES(critical)
            """;

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cp.getCheckpointId());
            ps.setString(2, driverId);
            ps.setString(3, cp.getType());
            ps.setString(4, cp.getLocationName());
            ps.setDouble(5, cp.getDistanceFromLast());
            ps.setDouble(6, cp.getExpectedDuration());
            ps.setDouble(7, cp.getActualDuration());
            ps.setDouble(8, cp.calculatePenalty());
            ps.setBoolean(9, cp.isCritical());
            ps.executeUpdate();

            saveTypeSpecificCheckpoint(conn, cp);
        } catch (SQLException e) {
            System.out.println("Could not save checkpoint: " + e.getMessage());
        }
    }

    private static void saveTypeSpecificCheckpoint(Connection conn, Checkpoint cp) throws SQLException {
        if (cp instanceof DeliveryCheckpoint) {
            saveCheckpointPenalty(conn, "delivery_checkpoints", "delivery_penalty", cp);
        } else if (cp instanceof FuelCheckpoint) {
            saveCheckpointPenalty(conn, "fuel_checkpoints", "fuel_penalty", cp);
        } else if (cp instanceof RestCheckpoint) {
            saveCheckpointPenalty(conn, "rest_checkpoints", "rest_penalty", cp);
        }
    }

    private static void saveCheckpointPenalty(
        Connection conn,
        String tableName,
        String penaltyColumn,
        Checkpoint cp
    ) throws SQLException {
        String sql = "INSERT INTO " + tableName + " (checkpoint_id, " + penaltyColumn + ") " +
            "VALUES (?, ?) ON DUPLICATE KEY UPDATE " + penaltyColumn + " = VALUES(" + penaltyColumn + ")";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cp.getCheckpointId());
            ps.setDouble(2, cp.calculatePenalty());
            ps.executeUpdate();
        }
    }

    public static void deleteCheckpoint(String checkpointId) {
        String sql = "DELETE FROM checkpoints WHERE checkpoint_id = ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, checkpointId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Could not delete checkpoint: " + e.getMessage());
        }
    }
}
