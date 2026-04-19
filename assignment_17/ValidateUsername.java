import java.util.regex.*;

public class ValidateUsername {

    public static void main(String[] args) {
        // Rule: Start with letter, only letters/digits/underscore, 5-15 chars
        String regex = "^[a-zA-Z][a-zA-Z0-9_]{4,14}$";

        String[] usernames = {
            "user_123",   // Valid
            "123user",    // Invalid - starts with number
            "us",         // Invalid - too short
            "Alice_99",   // Valid
            "this_username_is_too_long_123", // Invalid - too long
            "John"        // Invalid - too short
        };

        System.out.println("=== Username Validation ===\n");

        for (String username : usernames) {
            boolean isValid = username.matches(regex);
            System.out.println((isValid ? "✅" : "❌") + " \"" + username + "\" → " + (isValid ? "Valid" : "Invalid"));
        }
    }
}
