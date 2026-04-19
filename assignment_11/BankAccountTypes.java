// ==================== HIERARCHICAL INHERITANCE ====================
// Superclass
class BankAccount {
    private String accountNumber;
    private double balance;

    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }
    protected void setBalance(double balance) { this.balance = balance; }

    public void displayAccountType() {
        System.out.println("Account No : " + accountNumber);
        System.out.printf("Balance    : ₹%.2f%n", balance);
    }
}

// Subclass 1
class SavingsAccount extends BankAccount {
    private double interestRate; // Annual interest rate in %

    public SavingsAccount(String accNo, double balance, double interestRate) {
        super(accNo, balance);
        this.interestRate = interestRate;
    }

    public double getInterestRate() { return interestRate; }

    public double calculateAnnualInterest() {
        return getBalance() * interestRate / 100;
    }

    @Override
    public void displayAccountType() {
        System.out.println("Account Type   : Savings Account");
        super.displayAccountType();
        System.out.println("Interest Rate  : " + interestRate + "% p.a.");
        System.out.printf("Annual Interest: ₹%.2f%n", calculateAnnualInterest());
    }
}

// Subclass 2
class CheckingAccount extends BankAccount {
    private double withdrawalLimit;

    public CheckingAccount(String accNo, double balance, double withdrawalLimit) {
        super(accNo, balance);
        this.withdrawalLimit = withdrawalLimit;
    }

    public double getWithdrawalLimit() { return withdrawalLimit; }

    public void withdraw(double amount) {
        if (amount <= withdrawalLimit && amount <= getBalance()) {
            setBalance(getBalance() - amount);
            System.out.println("✅ Withdrawn ₹" + amount + " | Remaining: ₹" + getBalance());
        } else {
            System.out.println("❌ Exceeds withdrawal limit or insufficient balance.");
        }
    }

    @Override
    public void displayAccountType() {
        System.out.println("Account Type    : Checking Account");
        super.displayAccountType();
        System.out.printf("Withdrawal Limit: ₹%.2f%n", withdrawalLimit);
    }
}

// Subclass 3
class FixedDepositAccount extends BankAccount {
    private double interestRate;
    private int tenureMonths;

    public FixedDepositAccount(String accNo, double balance, double interestRate, int tenureMonths) {
        super(accNo, balance);
        this.interestRate = interestRate;
        this.tenureMonths = tenureMonths;
    }

    public double calculateMaturityAmount() {
        return getBalance() * Math.pow(1 + interestRate / 100 / 12, tenureMonths);
    }

    @Override
    public void displayAccountType() {
        System.out.println("Account Type    : Fixed Deposit Account");
        super.displayAccountType();
        System.out.println("Interest Rate   : " + interestRate + "% p.a.");
        System.out.println("Tenure          : " + tenureMonths + " months");
        System.out.printf("Maturity Amount : ₹%.2f%n", calculateMaturityAmount());
    }
}

// ==================== MAIN CLASS ====================
public class BankAccountTypes {
    public static void main(String[] args) {
        SavingsAccount sa = new SavingsAccount("SB-1001", 50000, 4.0);
        CheckingAccount ca = new CheckingAccount("CA-2001", 30000, 10000);
        FixedDepositAccount fda = new FixedDepositAccount("FD-3001", 100000, 7.5, 12);

        System.out.println("===== BANK ACCOUNT TYPES =====\n");

        System.out.println("--- Savings Account ---");
        sa.displayAccountType();

        System.out.println("\n--- Checking Account ---");
        ca.displayAccountType();
        ca.withdraw(8000);

        System.out.println("\n--- Fixed Deposit Account ---");
        fda.displayAccountType();

        // Polymorphism - BankAccount reference
        System.out.println("\n--- All Accounts Summary ---");
        BankAccount[] accounts = {sa, ca, fda};
        for (BankAccount acc : accounts) {
            System.out.println(acc.getAccountNumber() + " → Balance: ₹" + acc.getBalance());
        }
    }
}
