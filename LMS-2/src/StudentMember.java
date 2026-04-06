public class StudentMember extends Member {

    public StudentMember(int id, String name) {
        super(id, name);
    }

    @Override
    public int getIssueLimit() {
        return 3;
    }

    @Override
    public double calculateFine(int daysLate) {
        return daysLate * 5; // ₹5 per day
    }
}
