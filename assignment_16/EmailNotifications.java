import java.util.*;
import java.time.LocalDateTime;

public class EmailNotifications {

    // Simulated email sending method
    static void sendEmailNotification(String email) {
        System.out.println("[EMAIL SENT] To: " + email
            + " | Subject: Important Notification"
            + " | Time: " + LocalDateTime.now());
    }

    public static void main(String[] args) {
        List<String> emails = Arrays.asList(
            "rahul.sharma@example.com",
            "priya.mehta@example.com",
            "ankit.verma@example.com",
            "sunita.rao@example.com",
            "deepak.nair@example.com",
            "kavya.patel@example.com"
        );

        System.out.println("=== Sending Email Notifications ===\n");

        // forEach with lambda to send notification to each user
        emails.forEach(email -> sendEmailNotification(email));

        System.out.println("\nTotal notifications sent: " + emails.size());
    }
}
