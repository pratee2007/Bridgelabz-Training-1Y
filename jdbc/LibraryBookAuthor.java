// ==================== SINGLE INHERITANCE ====================
// Superclass
class Book {
    private String title;
    private int publicationYear;

    public Book(String title, int publicationYear) {
        this.title = title;
        this.publicationYear = publicationYear;
    }

    public String getTitle() { return title; }
    public int getPublicationYear() { return publicationYear; }

    public void displayInfo() {
        System.out.println("Book Title       : " + title);
        System.out.println("Publication Year : " + publicationYear);
    }
}

// Subclass (Single Inheritance)
class Author extends Book {
    private String name;
    private String bio;

    public Author(String title, int publicationYear, String name, String bio) {
        super(title, publicationYear); // Call superclass constructor
        this.name = name;
        this.bio = bio;
    }

    public String getName() { return name; }
    public String getBio() { return bio; }

    @Override
    public void displayInfo() {
        super.displayInfo();  // Call superclass method
        System.out.println("Author Name      : " + name);
        System.out.println("Author Bio       : " + bio);
    }
}

// ==================== MAIN CLASS ====================
public class LibraryBookAuthor {
    public static void main(String[] args) {
        Author a1 = new Author("Java: The Complete Reference", 2022, "Herbert Schildt",
                "American author and Java programming expert.");

        Author a2 = new Author("Clean Code", 2008, "Robert C. Martin",
                "Software engineer and co-author of the Agile Manifesto.");

        System.out.println("===== LIBRARY MANAGEMENT SYSTEM =====\n");

        System.out.println("--- Book 1 ---");
        a1.displayInfo();

        System.out.println("\n--- Book 2 ---");
        a2.displayInfo();

        // Superclass reference (Polymorphism)
        System.out.println("\n--- Using Book Reference ---");
        Book b = new Author("Effective Java", 2018, "Joshua Bloch", "Former Java architect at Google.");
        b.displayInfo();
    }
}
