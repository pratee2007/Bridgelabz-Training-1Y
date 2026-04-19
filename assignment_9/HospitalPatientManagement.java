import java.util.*;

// ==================== INTERFACE ====================
interface MedicalRecord {
    void addRecord(String record);
    void viewRecords();
}

// ==================== ABSTRACT CLASS ====================
abstract class Patient implements MedicalRecord {
    private int patientId;
    private String name;
    private int age;
    private List<String> medicalHistory; // Encapsulated sensitive data

    public Patient(int patientId, String name, int age) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.medicalHistory = new ArrayList<>();
    }

    // Getters and Setters (Encapsulation)
    public int getPatientId() { return patientId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    protected List<String> getMedicalHistory() { return medicalHistory; } // Protected

    // Interface implementation
    @Override
    public void addRecord(String record) {
        medicalHistory.add(record);
        System.out.println("📋 Record added for " + name + ": " + record);
    }

    @Override
    public void viewRecords() {
        System.out.println("Medical History for " + name + ":");
        if (medicalHistory.isEmpty()) {
            System.out.println("  No records found.");
        } else {
            medicalHistory.forEach(r -> System.out.println("  - " + r));
        }
    }

    // Abstract method
    public abstract double calculateBill();

    // Concrete method
    public void getPatientDetails() {
        System.out.println("Patient ID   : " + patientId);
        System.out.println("Name         : " + name);
        System.out.println("Age          : " + age);
        System.out.printf("Total Bill   : ₹%.2f%n", calculateBill());
    }
}

// ==================== SUBCLASSES ====================
class InPatient extends Patient {
    private int daysAdmitted;
    private double dailyRoomCharge;
    private double treatmentCost;

    public InPatient(int id, String name, int age, int daysAdmitted, double dailyRoomCharge, double treatmentCost) {
        super(id, name, age);
        this.daysAdmitted = daysAdmitted;
        this.dailyRoomCharge = dailyRoomCharge;
        this.treatmentCost = treatmentCost;
    }

    @Override
    public double calculateBill() {
        return (daysAdmitted * dailyRoomCharge) + treatmentCost;
    }

    @Override
    public void getPatientDetails() {
        System.out.println("Type         : In-Patient");
        super.getPatientDetails();
        System.out.println("Days Admitted: " + daysAdmitted);
        System.out.println("Room Charge  : ₹" + dailyRoomCharge + "/day");
        System.out.println("Treatment    : ₹" + treatmentCost);
    }
}

class OutPatient extends Patient {
    private double consultationFee;
    private double medicationCost;

    public OutPatient(int id, String name, int age, double consultationFee, double medicationCost) {
        super(id, name, age);
        this.consultationFee = consultationFee;
        this.medicationCost = medicationCost;
    }

    @Override
    public double calculateBill() {
        return consultationFee + medicationCost;
    }

    @Override
    public void getPatientDetails() {
        System.out.println("Type         : Out-Patient");
        super.getPatientDetails();
        System.out.println("Consultation : ₹" + consultationFee);
        System.out.println("Medication   : ₹" + medicationCost);
    }
}

// ==================== MAIN CLASS ====================
public class HospitalPatientManagement {
    public static void main(String[] args) {
        List<Patient> patients = new ArrayList<>();

        InPatient p1 = new InPatient(1001, "Rahul Sharma", 45, 7, 3000, 25000);
        p1.addRecord("Diagnosed with Appendicitis");
        p1.addRecord("Surgery performed successfully");

        OutPatient p2 = new OutPatient(1002, "Priya Mehta", 32, 500, 1200);
        p2.addRecord("Routine checkup - BP normal");

        patients.add(p1);
        patients.add(p2);

        System.out.println("===== HOSPITAL PATIENT MANAGEMENT =====\n");

        // Polymorphism - Patient reference handles all types
        for (Patient p : patients) {
            System.out.println("----------------------------------------");
            p.getPatientDetails();
            p.viewRecords();
        }
        System.out.println("----------------------------------------");
    }
}
