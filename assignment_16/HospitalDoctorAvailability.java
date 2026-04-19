import java.util.*;
import java.util.stream.*;

public class HospitalDoctorAvailability {

    static class Doctor {
        String name;
        String specialty;
        List<String> availableDays;

        Doctor(String name, String specialty, List<String> availableDays) {
            this.name = name;
            this.specialty = specialty;
            this.availableDays = availableDays;
        }

        boolean isAvailableOnWeekends() {
            return availableDays.contains("Saturday") || availableDays.contains("Sunday");
        }

        @Override
        public String toString() {
            return "Dr. " + name + " | Specialty: " + specialty + " | Days: " + availableDays;
        }
    }

    public static void main(String[] args) {
        List<Doctor> doctors = Arrays.asList(
            new Doctor("Sharma", "Cardiology", Arrays.asList("Monday", "Wednesday", "Saturday")),
            new Doctor("Patel", "Neurology", Arrays.asList("Tuesday", "Thursday")),
            new Doctor("Mehta", "Orthopedics", Arrays.asList("Saturday", "Sunday")),
            new Doctor("Gupta", "Pediatrics", Arrays.asList("Monday", "Friday", "Sunday")),
            new Doctor("Verma", "Dermatology", Arrays.asList("Wednesday", "Thursday")),
            new Doctor("Singh", "General Medicine", Arrays.asList("Saturday", "Sunday", "Monday"))
        );

        System.out.println("=== Doctors Available on Weekends (Sorted by Specialty) ===");

        doctors.stream()
            .filter(Doctor::isAvailableOnWeekends)           // Filter weekend availability
            .sorted(Comparator.comparing(d -> d.specialty))  // Sort by specialty alphabetically
            .forEach(System.out::println);
    }
}
