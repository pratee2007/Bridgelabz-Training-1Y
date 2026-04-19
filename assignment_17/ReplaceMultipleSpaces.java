import java.util.regex.*;

public class ReplaceMultipleSpaces {

    public static void main(String[] args) {
        String input = "This   is    an   example   with    multiple    spaces.";

        // Replace one or more whitespace characters with a single space
        String regex = " +";
        String result = input.replaceAll(regex, " ");

        System.out.println("=== Replace Multiple Spaces ===\n");
        System.out.println("Input : \"" + input + "\"");
        System.out.println("Output: \"" + result + "\"");

        // Additional example
        String input2 = "Hello      World!   How   are   you?";
        String result2 = input2.replaceAll(regex, " ");
        System.out.println("\nInput : \"" + input2 + "\"");
        System.out.println("Output: \"" + result2 + "\"");
    }
}
