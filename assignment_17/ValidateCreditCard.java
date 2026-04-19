import java.util.regex.*;

public class ValidateCreditCard {

    public static void main(String[] args) {
        // Visa: starts with 4, 16 digits total
        String visaRegex = "^4[0-9]{15}$";

        // MasterCard: starts with 5, 16 digits total
        String masterCardRegex = "^5[0-9]{15}$";

        String[] cardNumbers = {
            "4111111111111111",  // Valid Visa
            "5234567890123456",  // Valid MasterCard
            "6111111111111111",  // Invalid - doesn't start with 4 or 5
            "411111111111111",   // Invalid - only 15 digits
            "41111111111111111", // Invalid - 17 digits
            "5000000000000000"   // Valid MasterCard
        };

        System.out.println("=== Credit Card Validation ===\n");

        for (String card : cardNumbers) {
            if (card.matches(visaRegex)) {
                System.out.println("✅ \"" + card + "\" → Valid VISA Card");
            } else if (card.matches(masterCardRegex)) {
                System.out.println("✅ \"" + card + "\" → Valid MasterCard");
            } else {
                System.out.println("❌ \"" + card + "\" → Invalid Card");
            }
        }
    }
}
