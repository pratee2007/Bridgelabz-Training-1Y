import java.util.regex.*;
import java.util.*;

public class ExtractCapitalizedWords {

    public static void main(String[] args) {
        String text = "The Eiffel Tower is in Paris and the Statue of Liberty is in New York.";

        // Regex: Word starting with uppercase letter followed by lowercase letters
        String regex = "\\b[A-Z][a-z]+\\b";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        System.out.println("=== Extract Capitalized Words ===\n");
        System.out.println("Input: \"" + text + "\"\n");

        List<String> words = new ArrayList<>();
        while (matcher.find()) {
            words.add(matcher.group());
        }

        System.out.println("Capitalized Words:");
        System.out.println(String.join(", ", words));
        System.out.println("\nTotal found: " + words.size());
    }
}
