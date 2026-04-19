import java.util.*;

// ==================== INTERFACE ====================
interface Loanable {
    void applyForLoan(double amount);
    boolean calculateLoanEligibility();
}

// ==================== ABSTRACT CLASS ====================
abstract class BankAccount {
    private String accountNumber;
    private String holderName;
    private double balance;

    public BankAccount(String accountNumber, String holderName, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }

    // Getters and Setters (Encapsulation)
    public String getAccountNumber() { return accountNumber; }

    public String getHolderName() { return holderName; }
    public void setHolderName(String holderName) { this.holderName = holderName; }

    public double getBalance() { return balance; }
    protected void setBalance(double balance) { this.balance = balance; }

    // Concrete methods
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("✅ Deposited ₹" + amount + " | New Balance: ₹" + balance);
        } else {
            System.out.println("❌ Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("✅ Withdrawn ₹" + amount + " | New Balance: ₹" + balance);
        } else {
            System.out.println("❌ Insufficient balance or invalid amount.");
        }
    }

    // Abstract method
    public abstract double calculateInterest();

    public void displayDetails() {
        System.out.println("Account No   : " + accountNumber);
        System.out.println("Holder Name  : " + holderName);
        System.out.printf("Balance      : ₹%.2f%n", balance);
        System.out.printf("Interest     : ₹%.2f%n", calculateInterest());
    }
}

// ==================== SUBCLASSES ====================
class SavingsAccount extends BankAccount implements Loanable {
    private double interestRate = 0.04; // 4% per year

    public SavingsAccount(String accNo, String name, double balance) {
        super(accNo, name, balance);
    }

    @Override
    public double calculateInterest() {
        return getBalance() * interestRate;
    }

    @Override
    public void applyForLoan(double amount) {
        if (calculateLoanEligibility()) {
            System.out.println("✅ Loan of ₹" + amount + " approved for " + getHolderName());
        } else {
            System.out.println("❌ Loan rejected. Insufficient balance.");
        }
    }

    @Override
    public boolean calculateLoanEligibility() {
        return getBalance() >= 10000; // Minimum ₹10,000 balance required
    }
}

class CurrentAccount extends BankAccount {
    private double overdraftLimit;
    private double interestRate = 0.02; // 2% per year

    public CurrentAccount(String accNo, String name, double balance, double overdraftLimit) {
        super(accNo, name, balance);
        this.overdraftLimit = overdraftLimit;
    }

    public double getOverdraftLimit() { return overdraftLimit; }

    @Override
    public double calculateInterest() {
        return getBalance() * interestRate;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= getBalance() + overdraftLimit) {
            setBalance(getBalance() - amount);
            System.out.println("✅ Withdrawn ₹" + amount + " | New Balance: ₹" + getBalance());
        } else {
            System.out.println("❌ Exceeds overdraft limit.");
        }
    }
}

// ==================== MAIN CLASS ====================
public class BankingSystem {
    public static void main(String[] args) {
        List<BankAccount> accounts = new ArrayList<>();

        SavingsAccount sa = new SavingsAccount("SB001", "Rahul Sharma", 25000);
        CurrentAccount ca = new CurrentAccount("CA001", "Priya Enterprises", 50000, 10000);

        accounts.add(sa);
        accounts.add(ca);

        System.out.println("===== BANKING SYSTEM =====\n");

        // Polymorphism - process different account types
        for (BankAccount acc : accounts) {
            System.out.println("--------------------------");
            acc.displayDetails();
        }

        System.out.println("\n===== TRANSACTIONS =====");
        System.out.println("\n[Savings Account]");
        sa.deposit(5000);
        sa.withdraw(2000);
        sa.applyForLoan(100000);

        System.out.println("\n[Current Account]");
        ca.deposit(10000);
        ca.withdraw(55000); // Uses overdraft
    }
}
