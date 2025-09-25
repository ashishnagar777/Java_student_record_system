import java.time.LocalDate;

public class Book {
    private final String id;
    private final String title;
    private final String author;
    private boolean issued;
    private Integer issuedToUserId;
    private LocalDate issueDate;

    public Book(String id, String title, String author) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Book id cannot be empty");
        this.id = id.trim();
        this.title = title == null ? "" : title.trim();
        this.author = author == null ? "" : author.trim();
        this.issued = false;
        this.issuedToUserId = null;
        this.issueDate = null;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isIssued() { return issued; }
    public Integer getIssuedToUserId() { return issuedToUserId; }
    public LocalDate getIssueDate() { return issueDate; }

    public void issueTo(int userId) {
        if (issued) throw new IllegalStateException("Book is already issued");
        this.issued = true;
        this.issuedToUserId = userId;
        this.issueDate = LocalDate.now();
    }

    public void returned() {
        if (!issued) throw new IllegalStateException("Book is not issued");
        this.issued = false;
        this.issuedToUserId = null;
        this.issueDate = null;
    }

    @Override
    public String toString() {
        String status = issued ? String.format("(issued to user %d on %s)", issuedToUserId, issueDate) : "(available)";
        return String.format("[%s] %s by %s %s", id, title, author, status);
    }
}
