import java.util.regex.*;
import java.util.*;

public class ExtractEmails {

    public static void main(String[] args) {
        String text = "Contact us at support@example.com and info@company.org "
                    + "or reach admin@website.net for help.";

        // Regex pattern for email addresses
        String regex = "[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        System.out.println("=== Extracted Email Addresses ===\n");
        System.out.println("Input Text: \"" + text + "\"\n");
        System.out.println("Found Emails:");

        List<String> emails = new ArrayList<>();
        while (matcher.find()) {
            emails.add(matcher.group());
        }

        emails.forEach(email -> System.out.println("  → " + email));
        System.out.println("\nTotal emails found: " + emails.size());
    }
}
