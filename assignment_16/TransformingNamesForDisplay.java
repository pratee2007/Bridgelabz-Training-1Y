import java.util.*;
import java.util.stream.*;

public class TransformingNamesForDisplay {

    public static void main(String[] args) {
        List<String> customerNames = Arrays.asList(
            "rohit sharma",
            "priya mehta",
            "ankit verma",
            "sunita rao",
            "deepak nair",
            "kavya patel",
            "arjun singh"
        );

        System.out.println("=== Customer Names (Uppercase & Alphabetically Sorted) ===");

        customerNames.stream()
            .map(String::toUpperCase)     // Convert to uppercase
            .sorted()                     // Sort alphabetically
            .forEach(System.out::println);

        System.out.println("\n=== Original List for Comparison ===");
        customerNames.forEach(System.out::println);
    }
}
