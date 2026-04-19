import java.util.regex.*;
import java.util.*;

public class ExtractLinks {

    public static void main(String[] args) {
        String text = "Visit https://www.google.com and http://example.org for more info. "
                    + "Also check https://openai.com/blog for latest updates.";

        // Regex for http and https URLs
        String regex = "https?://[a-zA-Z0-9.\\-/]+";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        System.out.println("=== Extract Links from Text ===\n");
        System.out.println("Input: \"" + text + "\"\n");

        List<String> links = new ArrayList<>();
        while (matcher.find()) {
            links.add(matcher.group());
        }

        System.out.println("Extracted Links:");
        System.out.println(String.join(", ", links));
        System.out.println("\nTotal links found: " + links.size());
    }
}
