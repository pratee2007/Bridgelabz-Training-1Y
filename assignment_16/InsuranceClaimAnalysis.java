import java.util.*;
import java.util.stream.*;

public class InsuranceClaimAnalysis {

    static class Claim {
        String claimId;
        String claimType;
        double amount;

        Claim(String claimId, String claimType, double amount) {
            this.claimId = claimId;
            this.claimType = claimType;
            this.amount = amount;
        }
    }

    public static void main(String[] args) {
        List<Claim> claims = Arrays.asList(
            new Claim("C001", "Health", 15000.0),
            new Claim("C002", "Vehicle", 8000.0),
            new Claim("C003", "Health", 22000.0),
            new Claim("C004", "Life", 50000.0),
            new Claim("C005", "Vehicle", 12000.0),
            new Claim("C006", "Health", 9500.0),
            new Claim("C007", "Life", 75000.0),
            new Claim("C008", "Property", 30000.0),
            new Claim("C009", "Vehicle", 6500.0),
            new Claim("C010", "Property", 45000.0)
        );

        System.out.println("=== Average Claim Amount by Claim Type ===");

        // groupingBy() with averagingDouble()
        Map<String, Double> avgByType = claims.stream()
            .collect(Collectors.groupingBy(
                c -> c.claimType,
                Collectors.averagingDouble(c -> c.amount)
            ));

        // Print sorted by claim type
        avgByType.entrySet().stream()
            .sorted(Map.Entry.comparingByKey())
            .forEach(e -> System.out.printf("%-12s -> Avg Claim: ₹%.2f%n", e.getKey(), e.getValue()));
    }
}
