import java.util.*;

public class Library {
    private List<Book> books = new ArrayList<>();
    private List<Member> members = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();

    public void addBook(Book b) { books.add(b); }
    public void addMember(Member m) { members.add(m); }

    public void issueBook(int bookId, int memberId) {
        Book book = findBook(bookId);
        Member member = findMember(memberId);

        if (book == null || member == null) {
            System.out.println("Invalid IDs");
            return;
        }

        if (book.isIssued()) {
            System.out.println("Book already issued");
            return;
        }

        if (!member.canIssue()) {
            System.out.println("Issue limit reached");
            return;
        }

        book.issue();
        member.issueOne();

        transactions.add(new Transaction(bookId, memberId));

        System.out.println("Book issued to " + member);
    }

    public void returnBook(int bookId, int memberId) {
        Book book = findBook(bookId);
        Member member = findMember(memberId);

        if (book == null || member == null) {
            System.out.println("Invalid IDs");
            return;
        }

        if (!book.isIssued()) {
            System.out.println("Book was not issued");
            return;
        }

        Transaction t = findTransaction(bookId, memberId);
        t.returnBook();

        int daysLate = t.getDaysLate();
        double fine = member.calculateFine(daysLate);

        book.returnBook();
        member.returnOne();

        System.out.println("Book returned. Fine = ₹" + fine);
    }

    public void showBooks() {
        System.out.println("\n--- Book List ---");
        for (Book b : books) {
            System.out.println(b);
        }
    }



    private Transaction findTransaction(int bookId, int memberId) {
        for (Transaction t : transactions)
            if (t.getBookId() == bookId && t.getMemberId() == memberId)
                return t;
        return null;
    }

    private Book findBook(int id) {
        for (Book b : books)
            if (b.getId() == id) return b;
        return null;
    }

    private Member findMember(int id) {
        for (Member m : members)
            if (m.getId() == id) return m;
        return null;
    }
}
