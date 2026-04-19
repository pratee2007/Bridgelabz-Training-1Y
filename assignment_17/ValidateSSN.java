import java.util.regex.*;

public class ValidateSSN {

    public static void main(String[] args) {
        // SSN format: 3 digits - 2 digits - 4 digits  (e.g., 123-45-6789)
        String regex = "^\\d{3}-\\d{2}-\\d{4}$";

        String[] ssnList = {
            "123-45-6789",  // Valid
            "123456789",    // Invalid - no dashes
            "12-345-6789",  // Invalid - wrong grouping
            "000-00-0000",  // Valid format (though invalid SSN in real life)
            "999-99-9999",  // Valid format
            "123-456-789",  // Invalid - wrong digit count
            "123-45-678"    // Invalid - last group too short
        };

        System.out.println("=== Social Security Number (SSN) Validation ===\n");

        for (String ssn : ssnList) {
            boolean isValid = ssn.matches(regex);
            System.out.println((isValid ? "✅" : "❌") + " \"" + ssn + "\" → " + (isValid ? "Valid" : "Invalid"));
        }
    }
}
