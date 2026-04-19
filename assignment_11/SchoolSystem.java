// ==================== HIERARCHICAL INHERITANCE ====================
// Superclass
class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public int getAge() { return age; }

    public void displayRole() {
        System.out.println("Name : " + name);
        System.out.println("Age  : " + age);
    }
}

// Subclass 1
class Teacher extends Person {
    private String subject;
    private int experienceYears;

    public Teacher(String name, int age, String subject, int experienceYears) {
        super(name, age);
        this.subject = subject;
        this.experienceYears = experienceYears;
    }

    public String getSubject() { return subject; }

    @Override
    public void displayRole() {
        System.out.println("Role       : Teacher");
        super.displayRole();
        System.out.println("Subject    : " + subject);
        System.out.println("Experience : " + experienceYears + " years");
    }
}

// Subclass 2
class Student extends Person {
    private String grade;
    private double gpa;

    public Student(String name, int age, String grade, double gpa) {
        super(name, age);
        this.grade = grade;
        this.gpa = gpa;
    }

    public String getGrade() { return grade; }

    @Override
    public void displayRole() {
        System.out.println("Role  : Student");
        super.displayRole();
        System.out.println("Grade : " + grade);
        System.out.printf("GPA   : %.1f%n", gpa);
    }
}

// Subclass 3
class Staff extends Person {
    private String department;
    private String designation;

    public Staff(String name, int age, String department, String designation) {
        super(name, age);
        this.department = department;
        this.designation = designation;
    }

    public String getDepartment() { return department; }

    @Override
    public void displayRole() {
        System.out.println("Role        : Staff");
        super.displayRole();
        System.out.println("Department  : " + department);
        System.out.println("Designation : " + designation);
    }
}

// ==================== MAIN CLASS ====================
public class SchoolSystem {
    public static void main(String[] args) {
        Teacher t = new Teacher("Mrs. Sunita Rao", 38, "Mathematics", 12);
        Student s = new Student("Aarav Sharma", 16, "10th Grade", 9.2);
        Staff st = new Staff("Mr. Deepak Nair", 45, "Administration", "Office Manager");

        System.out.println("===== SCHOOL MANAGEMENT SYSTEM =====\n");

        System.out.println("--- Teacher ---");
        t.displayRole();

        System.out.println("\n--- Student ---");
        s.displayRole();

        System.out.println("\n--- Staff ---");
        st.displayRole();

        // Polymorphism - Person reference for all roles
        System.out.println("\n--- All Members ---");
        Person[] people = {t, s, st};
        for (Person p : people) {
            System.out.println(p.getName() + " (Age: " + p.getAge() + ")");
        }
    }
}
