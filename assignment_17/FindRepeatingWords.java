import java.util.regex.*;
import java.util.*;

public class FindRepeatingWords {

    public static void main(String[] args) {
        String input = "This is is a repeated repeated word test.";

        // Regex: match a word followed by whitespace and then the same word again
        // \\b(\w+)\\b matches a word, \\s+ matches spaces, \\1 is back-reference to same word
        String regex = "\\b(\\w+)\\s+\\1\\b";

        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);

        System.out.println("=== Find Repeating Words ===\n");
        System.out.println("Input: \"" + input + "\"\n");

        List<String> repeatingWords = new ArrayList<>();
        while (matcher.find()) {
            repeatingWords.add(matcher.group(1)); // group(1) captures the repeated word
        }

        if (repeatingWords.isEmpty()) {
            System.out.println("No repeating words found.");
        } else {
            System.out.println("Repeating Words Found:");
            System.out.println(String.join(", ", repeatingWords));
        }

        // Another example
        String input2 = "The the dog barked and and ran away.";
        Matcher matcher2 = pattern.matcher(input2);
        List<String> repeating2 = new ArrayList<>();
        while (matcher2.find()) {
            repeating2.add(matcher2.group(1));
        }
        System.out.println("\nInput: \"" + input2 + "\"");
        System.out.println("Repeating Words: " + String.join(", ", repeating2));
    }
}
