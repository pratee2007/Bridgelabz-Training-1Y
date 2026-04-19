import java.util.regex.*;
import java.util.*;

public class CensorBadWords {

    public static void main(String[] args) {
        String input = "This is a damn bad example with some stupid words.";

        // List of bad words to censor
        List<String> badWords = Arrays.asList("damn", "stupid", "idiot", "fool");

        String result = input;

        // Replace each bad word (case-insensitive) with ****
        for (String word : badWords) {
            String regex = "(?i)\\b" + word + "\\b"; // (?i) = case-insensitive
            result = result.replaceAll(regex, "****");
        }

        System.out.println("=== Censor Bad Words ===\n");
        System.out.println("Input : \"" + input + "\"");
        System.out.println("Output: \"" + result + "\"");

        // Another example
        String input2 = "Don't be an idiot or a fool about this!";
        String result2 = input2;
        for (String word : badWords) {
            result2 = result2.replaceAll("(?i)\\b" + word + "\\b", "****");
        }
        System.out.println("\nInput : \"" + input2 + "\"");
        System.out.println("Output: \"" + result2 + "\"");
    }
}
