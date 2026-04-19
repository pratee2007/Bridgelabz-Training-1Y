// ==================== MULTILEVEL INHERITANCE ====================
// Level 1 - Base class
class Course {
    private String courseName;
    private int duration; // in hours

    public Course(String courseName, int duration) {
        this.courseName = courseName;
        this.duration = duration;
    }

    public String getCourseName() { return courseName; }
    public int getDuration() { return duration; }

    public void displayDetails() {
        System.out.println("Course Name  : " + courseName);
        System.out.println("Duration     : " + duration + " hours");
    }
}

// Level 2 - Subclass of Course
class OnlineCourse extends Course {
    private String platform;
    private boolean isRecorded;

    public OnlineCourse(String courseName, int duration, String platform, boolean isRecorded) {
        super(courseName, duration); // Call Course constructor
        this.platform = platform;
        this.isRecorded = isRecorded;
    }

    public String getPlatform() { return platform; }
    public boolean isRecorded() { return isRecorded; }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Platform     : " + platform);
        System.out.println("Recorded     : " + (isRecorded ? "Yes" : "No"));
    }
}

// Level 3 - Subclass of OnlineCourse
class PaidOnlineCourse extends OnlineCourse {
    private double fee;
    private double discount;

    public PaidOnlineCourse(String courseName, int duration, String platform,
                            boolean isRecorded, double fee, double discount) {
        super(courseName, duration, platform, isRecorded); // Call OnlineCourse constructor
        this.fee = fee;
        this.discount = discount;
    }

    public double getFee() { return fee; }
    public double getDiscount() { return discount; }

    public double getFinalFee() {
        return fee - (fee * discount / 100);
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.printf("Fee          : ₹%.2f%n", fee);
        System.out.printf("Discount     : %.0f%%%n", discount);
        System.out.printf("Final Fee    : ₹%.2f%n", getFinalFee());
    }
}

// ==================== MAIN CLASS ====================
public class CourseHierarchy {
    public static void main(String[] args) {
        Course c1 = new Course("Java Basics", 40);
        OnlineCourse c2 = new OnlineCourse("Data Structures", 60, "Coursera", true);
        PaidOnlineCourse c3 = new PaidOnlineCourse("Full Stack Development", 120,
                "Udemy", true, 4999, 20);

        System.out.println("===== EDUCATIONAL COURSE HIERARCHY =====\n");

        System.out.println("--- Offline Course ---");
        c1.displayDetails();

        System.out.println("\n--- Online Course ---");
        c2.displayDetails();

        System.out.println("\n--- Paid Online Course ---");
        c3.displayDetails();

        // Polymorphism
        System.out.println("\n--- All Courses Summary ---");
        Course[] courses = {c1, c2, c3};
        for (Course c : courses) {
            System.out.println(c.getCourseName() + " - " + c.getDuration() + " hrs");
        }
    }
}
