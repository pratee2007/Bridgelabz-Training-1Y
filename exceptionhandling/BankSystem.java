class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String msg) {
        super(msg);
    }
}

class BankAccount {
    double balance = 1000;

    void withdraw(double amount)
            throws InsufficientBalanceException {

        if (amount < 0) {
            throw new IllegalArgumentException("Invalid amount!");
        }

        if (amount > balance) {
            throw new InsufficientBalanceException("Insufficient balance!");
        }

        balance -= amount;
        System.out.println("Withdrawal successful, new balance: " + balance);
    }
}

public class BankSystem {
    public static void main(String[] args) {
        BankAccount acc = new BankAccount();

        try {
            acc.withdraw(1500);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}