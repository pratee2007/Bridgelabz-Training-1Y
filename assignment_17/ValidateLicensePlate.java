import java.util.regex.*;

public class ValidateLicensePlate {

    public static void main(String[] args) {
        // Format: Two uppercase letters followed by four digits
        String regex = "^[A-Z]{2}[0-9]{4}$";

        String[] plates = {
            "AB1234",   // Valid
            "A12345",   // Invalid - only one letter
            "AB123",    // Invalid - only 3 digits
            "XY9999",   // Valid
            "ab1234",   // Invalid - lowercase letters
            "AB12345"   // Invalid - 5 digits
        };

        System.out.println("=== License Plate Validation ===\n");

        for (String plate : plates) {
            boolean isValid = plate.matches(regex);
            System.out.println((isValid ? "✅" : "❌") + " \"" + plate + "\" → " + (isValid ? "Valid" : "Invalid"));
        }
    }
}
