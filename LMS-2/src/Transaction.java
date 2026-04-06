import java.time.LocalDate;

public class Transaction {
    private int bookId;
    private int memberId;
    private LocalDate issueDate;
    private LocalDate returnDate;

    public Transaction(int bookId, int memberId) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.issueDate = LocalDate.now();
    }

    public void returnBook() {
        this.returnDate = LocalDate.now();
    }

    public int getDaysLate() {
        if (returnDate == null) return 0;
        int days = returnDate.getDayOfYear() - issueDate.getDayOfYear();
        return Math.max(0, days - 7); // 7 days free
    }

    public int getBookId() { return bookId; }
    public int getMemberId() { return memberId; }
}
