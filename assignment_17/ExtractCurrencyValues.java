import java.util.regex.*;
import java.util.*;

public class ExtractCurrencyValues {

    public static void main(String[] args) {
        String text = "The price is $45.99, and the discount is 10.50. "
                    + "Tax is $3.75 and shipping costs 5.00.";

        // Matches: optional $ followed by digits with optional decimal part
        String regex = "\\$?\\d+\\.\\d{2}";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        System.out.println("=== Extract Currency Values ===\n");
        System.out.println("Input: \"" + text + "\"\n");

        List<String> values = new ArrayList<>();
        while (matcher.find()) {
            values.add(matcher.group());
        }

        System.out.println("Extracted Currency Values:");
        System.out.println(String.join(", ", values));
        System.out.println("\nTotal values found: " + values.size());
    }
}
