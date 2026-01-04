import java.time.Year;
import java.util.Objects;

/**
 * Represents a book in the library system.
 * Contains information about the book's metadata and availability status.
 */
public class Book {
    private String title;
    private String author;
    private String isbn;
    private int publicationYear;
    private boolean available;
    private String category;
    private int timesCheckedOut;

    /**
     * Creates a new Book instance.
     * 
     * @param title The title of the book
     * @param author The author of the book
     * @param isbn The ISBN of the book
     * @param publicationYear The year the book was published
     * @param category The category/genre of the book
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public Book(String title, String author, String isbn, int publicationYear, String category) {
        validateTitle(title);
        validateAuthor(author);
        validateIsbn(isbn);
        validatePublicationYear(publicationYear);
        validateCategory(category);

        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.available = true;
        this.category = category;
        this.timesCheckedOut = 0;
    }

    // Validation helper methods
    private void validateTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
    }

    private void validateAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Author cannot be null or empty");
        }
    }

    private void validateIsbn(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("ISBN cannot be null or empty");
        }
    }

    private void validatePublicationYear(int year) {
        if (year < 1440 || year > Year.now().getValue()) {
            throw new IllegalArgumentException("Publication year must be between 1440 and current year");
        }
    }

    private void validateCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category cannot be null or empty");
        }
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { 
        validateTitle(title);
        this.title = title; 
    }
    
    public String getAuthor() { return author; }
    public void setAuthor(String author) { 
        validateAuthor(author);
        this.author = author; 
    }
    
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { 
        validateIsbn(isbn);
        this.isbn = isbn; 
    }
    
    public int getPublicationYear() { return publicationYear; }
    public void setPublicationYear(int publicationYear) { 
        validatePublicationYear(publicationYear);
        this.publicationYear = publicationYear; 
    }
    
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { 
        validateCategory(category);
        this.category = category; 
    }
    
    public int getTimesCheckedOut() { return timesCheckedOut; }
    public void setTimesCheckedOut(int timesCheckedOut) { 
        if (timesCheckedOut < 0) {
            throw new IllegalArgumentException("Times checked out cannot be negative");
        }
        this.timesCheckedOut = timesCheckedOut; 
    }

    /**
     * Increments the counter for how many times this book has been checked out.
     */
    public void incrementCheckoutCount() {
        this.timesCheckedOut++;
    }

    /**
     * Returns a formatted string with the book's information.
     * 
     * @return A string containing all book details
     */
    public String getInfo() {
        return title + " - " + author + " (" + publicationYear + ") - ISBN: " + isbn + 
               " - " + (available ? "Disponible" : "Prestado") + 
               " - Categoría: " + category + " - Veces prestado: " + timesCheckedOut;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publicationYear=" + publicationYear +
                ", available=" + available +
                ", category='" + category + '\'' +
                ", timesCheckedOut=" + timesCheckedOut +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(isbn);
    }
}