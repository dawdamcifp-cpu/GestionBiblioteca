import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a library that manages books and users.
 * Handles book borrowing, returning, and searching operations.
 */
public class Library {
    private List<Book> books;
    private List<User> users;
    private String name;

    /**
     * Creates a new Library instance.
     * 
     * @param name The name of the library
     * @throws IllegalArgumentException if name is null or empty
     */
    public Library(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Library name cannot be null or empty");
        }
        this.name = name;
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    /**
     * Adds a new book to the library.
     * 
     * @param title The book's title
     * @param author The book's author
     * @param isbn The book's ISBN
     * @param publicationYear The year the book was published
     * @param category The book's category
     */
    public void addBook(String title, String author, String isbn, int publicationYear, String category) {
        try {
            Book book = new Book(title, author, isbn, publicationYear, category);
            books.add(book);
            System.out.println("Libro añadido: " + title);
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: No se pudo añadir el libro - " + e.getMessage());
        }
    }

    /**
     * Registers a new user in the library.
     * 
     * @param name The user's name
     * @param id The user's ID
     * @param email The user's email
     * @param maxLoans The maximum number of books the user can borrow
     */
    public void addUser(String name, String id, String email, int maxLoans) {
        try {
            User user = new User(name, id, email, maxLoans);
            users.add(user);
            System.out.println("Usuario registrado: " + name);
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: No se pudo registrar el usuario - " + e.getMessage());
        }
    }

    /**
     * Finds a user by their ID.
     * 
     * @param userId The user's ID
     * @return The User object if found, null otherwise
     */
    private User findUserById(String userId) {
        if (userId == null || userId.trim().isEmpty()) {
            return null;
        }
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Finds a book by its ISBN.
     * 
     * @param isbn The book's ISBN
     * @return The Book object if found, null otherwise
     */
    private Book findBookByIsbn(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            return null;
        }
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    /**
     * Finds a borrowed book from a user's borrowed list.
     * 
     * @param user The user
     * @param isbn The book's ISBN
     * @return The Book object if found, null otherwise
     */
    private Book findBorrowedBookByIsbn(User user, String isbn) {
        if (user == null || isbn == null || isbn.trim().isEmpty()) {
            return null;
        }
        for (Book book : user.getBorrowedBooks()) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    /**
     * Processes a book loan to a user.
     * 
     * @param userId The ID of the user borrowing the book
     * @param isbn The ISBN of the book to borrow
     */
    public void borrowBook(String userId, String isbn) {
        User user = findUserById(userId);
        if (user == null) {
            System.out.println("ERROR: Usuario no encontrado");
            return;
        }

        if (!user.isActive()) {
            System.out.println("ERROR: Usuario inactivo");
            return;
        }

        if (!user.canBorrow()) {
            System.out.println("ERROR: El usuario ha alcanzado el límite de préstamos");
            return;
        }

        Book book = findBookByIsbn(isbn);
        if (book == null) {
            System.out.println("ERROR: Libro no encontrado");
            return;
        }

        if (!book.isAvailable()) {
            System.out.println("ERROR: El libro no está disponible");
            return;
        }

        book.setAvailable(false);
        book.incrementCheckoutCount();
        user.addBorrowedBook(book);
        System.out.println("Préstamo realizado: " + book.getTitle() + " para " + user.getName());
    }

    /**
     * Processes the return of a book by a user.
     * 
     * @param userId The ID of the user returning the book
     * @param isbn The ISBN of the book to return
     */
    public void returnBook(String userId, String isbn) {
        User user = findUserById(userId);
        if (user == null) {
            System.out.println("ERROR: Usuario no encontrado");
            return;
        }

        Book book = findBorrowedBookByIsbn(user, isbn);
        if (book == null) {
            System.out.println("ERROR: El usuario no tiene prestado este libro");
            return;
        }

        book.setAvailable(true);
        user.removeBorrowedBook(book);
        System.out.println("Devolución realizada: " + book.getTitle() + " por " + user.getName());
    }

    /**
     * Displays all available books in the library.
     */
    public void showAvailableBooks() {
        System.out.println("\n=== LIBROS DISPONIBLES ===");
        boolean found = false;
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println(book.getInfo());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No hay libros disponibles");
        }
    }

    /**
     * Displays all currently borrowed books.
     */
    public void showBorrowedBooks() {
        System.out.println("\n=== LIBROS PRESTADOS ===");
        boolean found = false;
        for (Book book : books) {
            if (!book.isAvailable()) {
                System.out.println(book.getInfo());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No hay libros prestados");
        }
    }

    /**
     * Displays all books borrowed by a specific user.
     * 
     * @param userId The ID of the user
     */
    public void showUserBooks(String userId) {
        User user = findUserById(userId);
        if (user == null) {
            System.out.println("ERROR: Usuario no encontrado");
            return;
        }

        System.out.println("\n=== LIBROS DE " + user.getName() + " ===");
        if (user.getBorrowedBooks().isEmpty()) {
            System.out.println("El usuario no tiene libros prestados");
        } else {
            for (Book book : user.getBorrowedBooks()) {
                System.out.println(book.getInfo());
            }
        }
    }

    /**
     * Displays library statistics including total books, users, and most borrowed book.
     */
    public void showStats() {
        System.out.println("\n=== ESTADÍSTICAS DE LA BIBLIOTECA ===");
        System.out.println("Biblioteca: " + name);
        System.out.println("Total de libros: " + books.size());
        System.out.println("Total de usuarios: " + users.size());

        int availableCount = getTotalAvailableBooks();
        System.out.println("Libros disponibles: " + availableCount);
        System.out.println("Libros prestados: " + (books.size() - availableCount));

        Book mostBorrowed = getMostBorrowedBook();
        if (mostBorrowed != null) {
            System.out.println("Libro más prestado: " + mostBorrowed.getTitle() + 
                             " (" + mostBorrowed.getTimesCheckedOut() + " veces)");
        } else {
            System.out.println("No hay estadísticas de préstamos");
        }
    }

    /**
     * Searches and displays books by author.
     * 
     * @param author The author name to search for (case-insensitive partial match)
     */
    public void searchByAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            System.out.println("ERROR: El nombre del autor no puede estar vacío");
            return;
        }

        System.out.println("\n=== BÚSQUEDA POR AUTOR: " + author + " ===");
        boolean found = false;
        for (Book book : books) {
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                System.out.println(book.getInfo());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No se encontraron libros de este autor");
        }
    }

    /**
     * Searches and displays books by category.
     * 
     * @param category The category to search for (case-insensitive exact match)
     */
    public void searchByCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            System.out.println("ERROR: La categoría no puede estar vacía");
            return;
        }

        System.out.println("\n=== BÚSQUEDA POR CATEGORÍA: " + category + " ===");
        boolean found = false;
        for (Book book : books) {
            if (book.getCategory().equalsIgnoreCase(category)) {
                System.out.println(book.getInfo());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No se encontraron libros en esta categoría");
        }
    }

    /**
     * Counts the total number of available books.
     * 
     * @return The number of available books
     */
    public int getTotalAvailableBooks() {
        int count = 0;
        for (Book book : books) {
            if (book.isAvailable()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Finds the most borrowed book in the library.
     * 
     * @return The most borrowed Book, or null if no books have been borrowed
     */
    private Book getMostBorrowedBook() {
        Book mostBorrowed = null;
        int maxCount = 0;
        for (Book book : books) {
            if (book.getTimesCheckedOut() > maxCount) {
                maxCount = book.getTimesCheckedOut();
                mostBorrowed = book;
            }
        }
        return mostBorrowed;
    }

    /**
     * Returns an unmodifiable view of the books list.
     * 
     * @return Unmodifiable list of books
     */
    public List<Book> getBooks() {
        return Collections.unmodifiableList(books);
    }

    /**
     * Returns an unmodifiable view of the users list.
     * 
     * @return Unmodifiable list of users
     */
    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }

    public String getName() {
        return name;
    }
}