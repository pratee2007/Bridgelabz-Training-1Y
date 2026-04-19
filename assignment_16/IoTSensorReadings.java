import java.util.*;
import java.util.stream.*;

public class IoTSensorReadings {

    static class SensorReading {
        String sensorId;
        String type;
        double value;
        String unit;

        SensorReading(String sensorId, String type, double value, String unit) {
            this.sensorId = sensorId;
            this.type = type;
            this.value = value;
            this.unit = unit;
        }
    }

    // Threshold map per sensor type
    static final Map<String, Double> THRESHOLDS = new HashMap<>() {{
        put("Temperature", 35.0);
        put("Humidity", 80.0);
        put("Pressure", 1020.0);
        put("CO2", 1000.0);
    }};

    public static void main(String[] args) {
        List<SensorReading> readings = Arrays.asList(
            new SensorReading("S001", "Temperature", 38.5, "°C"),
            new SensorReading("S002", "Humidity", 65.0, "%"),
            new SensorReading("S003", "CO2", 1250.0, "ppm"),
            new SensorReading("S004", "Temperature", 29.0, "°C"),
            new SensorReading("S005", "Pressure", 1035.0, "hPa"),
            new SensorReading("S006", "Humidity", 85.5, "%"),
            new SensorReading("S007", "CO2", 800.0, "ppm"),
            new SensorReading("S008", "Temperature", 41.2, "°C")
        );

        System.out.println("=== IoT Sensor Readings Above Threshold ===\n");

        // Stream with filter + forEach
        readings.stream()
            .filter(r -> {
                Double threshold = THRESHOLDS.get(r.type);
                return threshold != null && r.value > threshold;
            })
            .forEach(r -> System.out.printf("⚠️  ALERT | Sensor: %-6s | Type: %-12s | Value: %.1f %s (Threshold: %.1f)%n",
                r.sensorId, r.type, r.value, r.unit, THRESHOLDS.get(r.type)));

        System.out.println("\nAll readings processed.");
    }
}
