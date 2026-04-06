public class TeacherMember extends Member {

    public TeacherMember(int id, String name) {
        super(id, name);
    }

    @Override
    public int getIssueLimit() {
        return 5;
    }

    @Override
    public double calculateFine(int daysLate) {
        return daysLate * 2; // ₹2 per day
    }
}
