import java.util.*;

public class EventAttendeeWelcome {

    static class Attendee {
        String name;
        String ticketType;

        Attendee(String name, String ticketType) {
            this.name = name;
            this.ticketType = ticketType;
        }
    }

    public static void main(String[] args) {
        List<Attendee> attendees = Arrays.asList(
            new Attendee("Rahul Gupta", "VIP"),
            new Attendee("Sneha Iyer", "General"),
            new Attendee("Vikram Bose", "VIP"),
            new Attendee("Anjali Desai", "General"),
            new Attendee("Manoj Tiwari", "Student"),
            new Attendee("Pooja Reddy", "Student"),
            new Attendee("Nikhil Jain", "General")
        );

        System.out.println("=== Event Welcome Messages ===\n");

        // forEach with lambda to print personalized welcome message
        attendees.forEach(attendee -> {
            String greeting;
            if (attendee.ticketType.equals("VIP")) {
                greeting = "🌟 Welcome, " + attendee.name + "! Your VIP lounge is ready. Enjoy exclusive access!";
            } else if (attendee.ticketType.equals("Student")) {
                greeting = "🎓 Welcome, " + attendee.name + "! Great to have young talent at the event!";
            } else {
                greeting = "👋 Welcome, " + attendee.name + "! Enjoy the event!";
            }
            System.out.println(greeting);
        });

        System.out.println("\nTotal attendees checked in: " + attendees.size());
    }
}
