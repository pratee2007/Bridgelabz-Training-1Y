import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseManager implements AutoCloseable {
    private final Connection connection;

    public DatabaseManager(String username, String password) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC driver not found. Add mysql-connector-j jar to the project classpath.", e);
        }

        String url = "jdbc:mysql://localhost:3306/route_tracker_system"
                + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        connection = DriverManager.getConnection(url, username, password);
    }

    public void saveDriver(Driver driver) throws SQLException {
        String sql = """
                INSERT INTO drivers (driver_id, name)
                VALUES (?, ?)
                ON DUPLICATE KEY UPDATE name = VALUES(name)
                """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, driver.getDriverId());
            statement.setString(2, driver.getName());
            statement.executeUpdate();
        }
    }

    public void saveCheckpoint(String driverId, Checkpoint checkpoint) throws SQLException {
        String sql = """
                INSERT INTO checkpoints (
                    checkpoint_id,
                    driver_id,
                    type,
                    location_name,
                    distance_from_last,
                    expected_duration,
                    actual_duration,
                    penalty,
                    is_critical
                )
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                ON DUPLICATE KEY UPDATE
                    driver_id = VALUES(driver_id),
                    type = VALUES(type),
                    location_name = VALUES(location_name),
                    distance_from_last = VALUES(distance_from_last),
                    expected_duration = VALUES(expected_duration),
                    actual_duration = VALUES(actual_duration),
                    penalty = VALUES(penalty),
                    is_critical = VALUES(is_critical)
                """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, checkpoint.getCheckpointId());
            statement.setString(2, driverId);
            statement.setString(3, checkpoint.getType());
            statement.setString(4, checkpoint.getLocationName());
            statement.setDouble(5, checkpoint.getDistanceFromLast());
            statement.setDouble(6, checkpoint.getExpectedDuration());
            statement.setDouble(7, checkpoint.getActualDuration());
            statement.setDouble(8, checkpoint.calculatePenalty());
            statement.setBoolean(9, checkpoint.isCritical());
            statement.executeUpdate();
        }
    }

    public void deleteCheckpoint(String checkpointId) throws SQLException {
        String sql = "DELETE FROM checkpoints WHERE checkpoint_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, checkpointId);
            statement.executeUpdate();
        }
    }

    @Override
    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
