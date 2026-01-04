import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Represents a library user.
 * Users can borrow and return books with a maximum loan limit.
 */
public class User {
    private String name;
    private String id;
    private String email;
    private List<Book> borrowedBooks;
    private int maxLoans;
    private boolean active;

    /**
     * Creates a new User instance.
     * 
     * @param name The user's full name
     * @param id The user's unique identifier
     * @param email The user's email address
     * @param maxLoans The maximum number of books the user can borrow simultaneously
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public User(String name, String id, String email, int maxLoans) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (maxLoans <= 0) {
            throw new IllegalArgumentException("Max loans must be greater than 0");
        }

        this.name = name;
        this.id = id;
        this.email = email;
        this.borrowedBooks = new ArrayList<>();
        this.maxLoans = maxLoans;
        this.active = true;
    }

    public String getName() { return name; }
    public void setName(String name) { 
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name; 
    }
    
    public String getId() { return id; }
    public void setId(String id) { 
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        this.id = id; 
    }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { 
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        this.email = email; 
    }
    
    /**
     * Returns an unmodifiable view of the borrowed books list.
     * 
     * @return Unmodifiable list of borrowed books
     */
    public List<Book> getBorrowedBooks() { 
        return Collections.unmodifiableList(borrowedBooks); 
    }
    
    public int getMaxLoans() { return maxLoans; }
    public void setMaxLoans(int maxLoans) { 
        if (maxLoans <= 0) {
            throw new IllegalArgumentException("Max loans must be greater than 0");
        }
        this.maxLoans = maxLoans; 
    }
    
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    /**
     * Checks if the user can borrow more books.
     * 
     * @return true if the user is active and hasn't reached the loan limit
     */
    public boolean canBorrow() {
        return active && borrowedBooks.size() < maxLoans;
    }

    /**
     * Adds a book to the user's borrowed books list.
     * 
     * @param book The book to add
     * @throws IllegalArgumentException if book is null
     * @throws IllegalStateException if user has reached the loan limit
     */
    public void addBorrowedBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }
        if (!canBorrow()) {
            throw new IllegalStateException("User has reached the maximum loan limit");
        }
        borrowedBooks.add(book);
    }

    /**
     * Removes a book from the user's borrowed books list.
     * 
     * @param book The book to remove
     * @throws IllegalArgumentException if book is null
     */
    public void removeBorrowedBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }
        borrowedBooks.remove(book);
    }

    /**
     * Returns a formatted string with the user's information.
     * 
     * @return A string containing all user details
     */
    public String getUserInfo() {
        return "Usuario: " + name + " (ID: " + id + ") - Email: " + email + 
               " - Libros prestados: " + borrowedBooks.size() + "/" + maxLoans;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", borrowedBooks=" + borrowedBooks.size() +
                ", maxLoans=" + maxLoans +
                ", active=" + active +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}