import java.util.*;

// ==================== INTERFACE ====================
interface Department {
    void assignDepartment(String department);
    String getDepartmentDetails();
}

// ==================== ABSTRACT CLASS ====================
abstract class Employee implements Department {
    private int employeeId;
    private String name;
    private double baseSalary;
    private String department;

    public Employee(int employeeId, String name, double baseSalary) {
        this.employeeId = employeeId;
        this.name = name;
        this.baseSalary = baseSalary;
    }

    // Getters and Setters (Encapsulation)
    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getBaseSalary() { return baseSalary; }
    public void setBaseSalary(double baseSalary) { this.baseSalary = baseSalary; }

    public String getDepartment() { return department; }

    // Interface methods
    @Override
    public void assignDepartment(String department) {
        this.department = department;
    }

    @Override
    public String getDepartmentDetails() {
        return "Department: " + (department != null ? department : "Not Assigned");
    }

    // Abstract method
    public abstract double calculateSalary();

    // Concrete method
    public void displayDetails() {
        System.out.println("-----------------------------");
        System.out.println("Employee ID  : " + employeeId);
        System.out.println("Name         : " + name);
        System.out.println("Base Salary  : ₹" + baseSalary);
        System.out.println(getDepartmentDetails());
        System.out.println("Final Salary : ₹" + calculateSalary());
    }
}

// ==================== SUBCLASSES ====================
class FullTimeEmployee extends Employee {
    private double bonus;

    public FullTimeEmployee(int id, String name, double baseSalary, double bonus) {
        super(id, name, baseSalary);
        this.bonus = bonus;
    }

    public double getBonus() { return bonus; }
    public void setBonus(double bonus) { this.bonus = bonus; }

    @Override
    public double calculateSalary() {
        return getBaseSalary() + bonus;  // Fixed salary + bonus
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Type         : Full-Time");
        System.out.println("Bonus        : ₹" + bonus);
    }
}

class PartTimeEmployee extends Employee {
    private int hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(int id, String name, double baseSalary, int hoursWorked, double hourlyRate) {
        super(id, name, baseSalary);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    public int getHoursWorked() { return hoursWorked; }
    public void setHoursWorked(int hoursWorked) { this.hoursWorked = hoursWorked; }

    public double getHourlyRate() { return hourlyRate; }
    public void setHourlyRate(double hourlyRate) { this.hourlyRate = hourlyRate; }

    @Override
    public double calculateSalary() {
        return hoursWorked * hourlyRate;  // Pay based on hours worked
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Type         : Part-Time");
        System.out.println("Hours Worked : " + hoursWorked);
        System.out.println("Hourly Rate  : ₹" + hourlyRate);
    }
}

// ==================== MAIN CLASS ====================
public class EmployeeManagementSystem {
    public static void main(String[] args) {
        // Create employees
        FullTimeEmployee e1 = new FullTimeEmployee(101, "Rahul Sharma", 50000, 10000);
        e1.assignDepartment("Engineering");

        PartTimeEmployee e2 = new PartTimeEmployee(102, "Priya Mehta", 0, 80, 300);
        e2.assignDepartment("Marketing");

        FullTimeEmployee e3 = new FullTimeEmployee(103, "Ankit Verma", 60000, 15000);
        e3.assignDepartment("Finance");

        // Polymorphism - using Employee reference list
        List<Employee> employees = new ArrayList<>();
        employees.add(e1);
        employees.add(e2);
        employees.add(e3);

        System.out.println("===== EMPLOYEE MANAGEMENT SYSTEM =====");
        for (Employee emp : employees) {
            emp.displayDetails();   // Polymorphic call
        }
        System.out.println("-----------------------------");
    }
}
