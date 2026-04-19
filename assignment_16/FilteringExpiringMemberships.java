import java.time.LocalDate;
import java.util.*;
import java.util.stream.*;

public class FilteringExpiringMemberships {

    static class GymMember {
        String name;
        LocalDate expiryDate;

        GymMember(String name, LocalDate expiryDate) {
            this.name = name;
            this.expiryDate = expiryDate;
        }

        @Override
        public String toString() {
            return name + " | Expiry: " + expiryDate;
        }
    }

    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        LocalDate threshold = today.plusDays(30);

        List<GymMember> members = Arrays.asList(
            new GymMember("Aarav Shah", today.plusDays(10)),
            new GymMember("Priya Nair", today.plusDays(60)),
            new GymMember("Ravi Kumar", today.plusDays(5)),
            new GymMember("Sneha Rao", today.plusDays(90)),
            new GymMember("Karan Mehta", today.plusDays(25)),
            new GymMember("Divya Singh", today.plusDays(3)),
            new GymMember("Amit Joshi", today.plusDays(45))
        );

        System.out.println("=== Members with Memberships Expiring Within 30 Days ===");
        System.out.println("Today: " + today);
        System.out.println("Threshold: " + threshold);
        System.out.println("--------------------------------------------------");

        List<GymMember> expiringSoon = members.stream()
            .filter(m -> !m.expiryDate.isAfter(threshold))   // expires within 30 days
            .sorted(Comparator.comparing(m -> m.expiryDate)) // soonest first
            .collect(Collectors.toList());

        expiringSoon.forEach(System.out::println);

        System.out.println("\nTotal members expiring soon: " + expiringSoon.size());
    }
}
