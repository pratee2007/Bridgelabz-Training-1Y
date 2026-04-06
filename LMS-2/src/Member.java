public class Member {
    protected int id;
    protected String name;
    protected int booksIssued = 0;

    public Member(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }

    // Polymorphic methods
    public int getIssueLimit() {
        return 0;
    }

    public double calculateFine(int daysLate) {
        return 0;
    }

    public boolean canIssue() {
        return booksIssued < getIssueLimit();
    }

    public void issueOne() { booksIssued++; }
    public void returnOne() { booksIssued--; }

    @Override
    public String toString() {
        return id + " | " + name + " | Issued: " + booksIssued;
    }
}
