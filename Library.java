import java.util.*;

public class Library {
    private final Map<String, Book> books = new HashMap<>();
    private final Map<Integer, User> users = new HashMap<>();

    public void addBook(Book b) {
        if (books.containsKey(b.getId())) throw new IllegalArgumentException("Book id already exists: " + b.getId());
        books.put(b.getId(), b);
    }

    public void addUser(User u) {
        if (users.containsKey(u.getId())) throw new IllegalArgumentException("User id already exists: " + u.getId());
        users.put(u.getId(), u);
    }

    public boolean issueBook(String bookId, int userId) {
        Book b = books.get(bookId);
        if (b == null) throw new NoSuchElementException("Book not found: " + bookId);
        User u = users.get(userId);
        if (u == null) throw new NoSuchElementException("User not found: " + userId);

        if (b.isIssued()) {
            System.out.println("Cannot issue: book is already issued to user " + b.getIssuedToUserId());
            return false;
        }
        if (!u.canBorrow()) {
            System.out.println("Cannot issue: user " + u.getName() + " has reached borrow limit");
            return false;
        }

        b.issueTo(userId);
        u.borrow(bookId);
        System.out.println("Issued book " + bookId + " to user " + userId);
        return true;
    }

    public boolean returnBook(String bookId, int userId) {
        Book b = books.get(bookId);
        if (b == null) throw new NoSuchElementException("Book not found: " + bookId);
        User u = users.get(userId);
        if (u == null) throw new NoSuchElementException("User not found: " + userId);

        if (!b.isIssued()) {
            System.out.println("Cannot return: book is not currently issued");
            return false;
        }
        if (!Objects.equals(b.getIssuedToUserId(), userId)) {
            System.out.println("Cannot return: book is issued to a different user: " + b.getIssuedToUserId());
            return false;
        }

        b.returned();
        u.returned(bookId);
        System.out.println("Book " + bookId + " returned by user " + userId);
        return true;
    }

    public List<Book> searchByTitle(String q) {
        String qq = q == null ? "" : q.toLowerCase();
        List<Book> res = new ArrayList<>();
        for (Book b : books.values()) {
            if (b.getTitle().toLowerCase().contains(qq)) res.add(b);
        }
        return res;
    }

    public List<Book> searchByAuthor(String q) {
        String qq = q == null ? "" : q.toLowerCase();
        List<Book> res = new ArrayList<>();
        for (Book b : books.values()) {
            if (b.getAuthor().toLowerCase().contains(qq)) res.add(b);
        }
        return res;
    }

    public List<Book> listAvailableBooks() {
        List<Book> res = new ArrayList<>();
        for (Book b : books.values()) if (!b.isIssued()) res.add(b);
        return res;
    }

    public List<Book> listIssuedBooks() {
        List<Book> res = new ArrayList<>();
        for (Book b : books.values()) if (b.isIssued()) res.add(b);
        return res;
    }

    public Collection<User> listUsers() { return users.values(); }

    public void seedSampleData() {
        try {
            addBook(new Book("B001", "Effective Java", "Joshua Bloch"));
            addBook(new Book("B002", "Clean Code", "Robert C. Martin"));
            addBook(new Book("B003", "Java: The Complete Reference", "Herbert Schildt"));

            addUser(new User(1, "Alice"));
            addUser(new User(2, "Bob"));
            addUser(new User(3, "Charlie", 5));
        } catch (IllegalArgumentException ex) {
        }
    }
}
