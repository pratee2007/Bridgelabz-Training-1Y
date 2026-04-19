import java.util.regex.*;
import java.util.*;

public class ExtractProgrammingLanguages {

    public static void main(String[] args) {
        String text = "I love Java, Python, and JavaScript, but I haven't tried Go yet. "
                    + "Some also prefer C++, Kotlin, and Ruby for their projects.";

        // List of known programming languages to search for
        List<String> languages = Arrays.asList(
            "Java", "Python", "JavaScript", "Go", "C\\+\\+", "Kotlin",
            "Ruby", "Swift", "Rust", "TypeScript", "PHP", "C#"
        );

        System.out.println("=== Extract Programming Language Names ===\n");
        System.out.println("Input: \"" + text + "\"\n");

        List<String> found = new ArrayList<>();

        for (String lang : languages) {
            // Word boundary to avoid partial matches
            Pattern pattern = Pattern.compile("\\b" + lang + "\\b");
            Matcher matcher = pattern.matcher(text);
            if (matcher.find()) {
                // Store display name (remove escape chars)
                found.add(lang.replace("\\+\\+", "++"));
            }
        }

        System.out.println("Programming Languages Found:");
        System.out.println(String.join(", ", found));
        System.out.println("\nTotal found: " + found.size());
    }
}
