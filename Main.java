import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Library library = new Library();

    public static void main(String[] args) {
        library.seedSampleData();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            System.out.print("Choose: ");
            String choice = scanner.nextLine().trim();
            try {
                switch (choice) {
                    case "1": listAllBooks(); break;
                    case "2": addBook(scanner); break;
                    case "3": addUser(scanner); break;
                    case "4": issueBook(scanner); break;
                    case "5": returnBook(scanner); break;
                    case "6": search(scanner); break;
                    case "7": listUsers(); break;
                    case "0":
                        System.out.println("Exiting. Goodbye!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("===== Library Menu =====");
        System.out.println("1) List all books (shows availability)");
        System.out.println("2) Add a book");
        System.out.println("3) Add a user");
        System.out.println("4) Issue a book");
        System.out.println("5) Return a book");
        System.out.println("6) Search (title/author)");
        System.out.println("7) List users");
        System.out.println("0) Exit");
    }

    private static void listAllBooks() {
        System.out.println("All books:");
        for (Book b : library.listAvailableBooks()) System.out.println("  " + b);
        System.out.println("Issued books:");
        for (Book b : library.listIssuedBooks()) System.out.println("  " + b);
    }

    private static void addBook(Scanner scanner) {
        System.out.print("Book id: ");
        String id = scanner.nextLine().trim();
        System.out.print("Title: ");
        String title = scanner.nextLine().trim();
        System.out.print("Author: ");
        String author = scanner.nextLine().trim();
        library.addBook(new Book(id, title, author));
        System.out.println("Book added: " + id);
    }

    private static void addUser(Scanner scanner) {
        System.out.print("User id (integer): ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Max books (enter for default 3): ");
        String maxStr = scanner.nextLine().trim();
        if (maxStr.isEmpty()) library.addUser(new User(id, name));
        else library.addUser(new User(id, name, Integer.parseInt(maxStr)));
        System.out.println("User added: " + id);
    }

    private static void issueBook(Scanner scanner) {
        System.out.print("Book id: ");
        String bookId = scanner.nextLine().trim();
        System.out.print("User id: ");
        int userId = Integer.parseInt(scanner.nextLine().trim());
        library.issueBook(bookId, userId);
    }

    private static void returnBook(Scanner scanner) {
        System.out.print("Book id: ");
        String bookId = scanner.nextLine().trim();
        System.out.print("User id: ");
        int userId = Integer.parseInt(scanner.nextLine().trim());
        library.returnBook(bookId, userId);
    }

    private static void search(Scanner scanner) {
        System.out.print("Search by (1) Title or (2) Author: ");
        String mode = scanner.nextLine().trim();
        System.out.print("Query: ");
        String q = scanner.nextLine().trim();
        List<Book> res = mode.equals("2") ? library.searchByAuthor(q) : library.searchByTitle(q);
        if (res.isEmpty()) System.out.println("No matches");
        else for (Book b : res) System.out.println("  " + b);
    }

    private static void listUsers() {
        System.out.println("Users:");
        for (User u : library.listUsers()) System.out.println("  " + u + " -> " + u.getIssuedBookIds());
    }
}
