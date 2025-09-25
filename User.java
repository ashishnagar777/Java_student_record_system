import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class User {
    private final int id;
    private final String name;
    private final int maxBooks;
    private final Set<String> issuedBookIds = new HashSet<>();

    public User(int id, String name) {
        this(id, name, 3);
    }

    public User(int id, String name, int maxBooks) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("User name cannot be empty");
        if (maxBooks <= 0) throw new IllegalArgumentException("maxBooks must be positive");
        this.id = id;
        this.name = name.trim();
        this.maxBooks = maxBooks;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getMaxBooks() { return maxBooks; }
    public Set<String> getIssuedBookIds() { return Collections.unmodifiableSet(issuedBookIds); }

    public boolean canBorrow() { return issuedBookIds.size() < maxBooks; }

    public void borrow(String bookId) {
        if (!canBorrow()) throw new IllegalStateException(name + " has reached the borrow limit");
        issuedBookIds.add(bookId);
    }

    public void returned(String bookId) {
        if (!issuedBookIds.contains(bookId)) throw new IllegalStateException(name + " does not have book " + bookId);
        issuedBookIds.remove(bookId);
    }

    @Override
    public String toString() {
        return String.format("User[%d] %s (borrowed %d/%d)", id, name, issuedBookIds.size(), maxBooks);
    }
}
