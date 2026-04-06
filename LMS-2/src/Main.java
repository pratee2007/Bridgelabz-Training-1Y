import java.util.*;

public class Main {
    public static void main(String[] args) {
        Library lib = new Library();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. Add Student Member");
            System.out.println("3. Add Teacher Member");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Show Books");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            int ch = sc.nextInt();

            switch (ch) {

                case 1:
                    System.out.print("Book ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Author: ");
                    String author = sc.nextLine();
                    lib.addBook(new Book(id, title, author));
                    break;

                case 2:
                    System.out.print("Student ID: ");
                    int sid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Name: ");
                    String sname = sc.nextLine();
                    lib.addMember(new StudentMember(sid, sname));
                    break;

                case 3:
                    System.out.print("Teacher ID: ");
                    int tid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Name: ");
                    String tname = sc.nextLine();
                    lib.addMember(new TeacherMember(tid, tname));
                    break;

                case 4:
                    System.out.print("Book ID: ");
                    int bid = sc.nextInt();
                    System.out.print("Member ID: ");
                    int mid = sc.nextInt();
                    lib.issueBook(bid, mid);
                    break;

                case 5:
                    System.out.print("Book ID: ");
                    int rb = sc.nextInt();
                    System.out.print("Member ID: ");
                    int rm = sc.nextInt();
                    lib.returnBook(rb, rm);
                    break;

                case 6:
                    lib.showBooks();
                    break;

                case 7:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
