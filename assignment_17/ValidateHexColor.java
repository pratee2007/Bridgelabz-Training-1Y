import java.util.regex.*;

public class ValidateHexColor {

    public static void main(String[] args) {
        // Format: # followed by exactly 6 hex characters (0-9, A-F, a-f)
        String regex = "^#[0-9A-Fa-f]{6}$";

        String[] colors = {
            "#FFA500",  // Valid
            "#ff4500",  // Valid
            "#123",     // Invalid - too short
            "#GGGGGG",  // Invalid - invalid hex chars
            "#12345",   // Invalid - only 5 digits
            "#aabbcc"   // Valid
        };

        System.out.println("=== Hex Color Code Validation ===\n");

        for (String color : colors) {
            boolean isValid = color.matches(regex);
            System.out.println((isValid ? "✅" : "❌") + " \"" + color + "\" → " + (isValid ? "Valid" : "Invalid"));
        }
    }
}
