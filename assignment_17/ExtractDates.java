import java.util.regex.*;
import java.util.*;

public class ExtractDates {

    public static void main(String[] args) {
        String text = "The events are scheduled for 12/05/2023, 15/08/2024, and 29/02/2020.";

        // Regex for dd/mm/yyyy format
        String regex = "\\b(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/([0-9]{4})\\b";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        System.out.println("=== Extract Dates (dd/mm/yyyy) ===\n");
        System.out.println("Input: \"" + text + "\"\n");

        List<String> dates = new ArrayList<>();
        while (matcher.find()) {
            dates.add(matcher.group());
        }

        System.out.println("Extracted Dates:");
        System.out.println(String.join(", ", dates));
        System.out.println("\nTotal dates found: " + dates.size());
    }
}
