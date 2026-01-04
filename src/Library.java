import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<User> users;
    private String n;

    public Library(String n) {
        this.n = n;
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public void addBook(String t, String a, String i, int y, String b) {
        Book book = new Book(t, a, i, y, b);
        books.add(book);
        System.out.println("Libro añadido: " + t);
    }

    public void addUser(String n, String id, String e, int ml) {
        User user = new User(n, id, e, ml);
        users.add(user);
        System.out.println("Usuario registrado: " + n);
    }

    public void borrowBook(String userId, String isbn) {
        User u = null;
        for (User user : users) {
            if (user.getId().equals(userId)) {
                u = user;
                break;
            }
        }

        if (u == null) {
            System.out.println("ERROR: Usuario no encontrado");
            return;
        }

        if (!u.isAct()) {
            System.out.println("ERROR: Usuario inactivo");
            return;
        }

        if (!u.canBorrow()) {
            System.out.println("ERROR: El usuario ha alcanzado el límite de préstamos");
            return;
        }

        Book b = null;
        for (Book book : books) {
            if (book.getI().equals(isbn)) {
                b = book;
                break;
            }
        }

        if (b == null) {
            System.out.println("ERROR: Libro no encontrado");
            return;
        }

        if (!b.isAv()) {
            System.out.println("ERROR: El libro no está disponible");
            return;
        }

        b.setAv(false);
        b.inc();
        u.addB(b);
        System.out.println("Préstamo realizado: " + b.getT() + " para " + u.getN());
    }

    public void returnBook(String userId, String isbn) {
        User u = null;
        for (User user : users) {
            if (user.getId().equals(userId)) {
                u = user;
                break;
            }
        }

        if (u == null) {
            System.out.println("ERROR: Usuario no encontrado");
            return;
        }

        Book b = null;
        for (Book book : u.getBl()) {
            if (book.getI().equals(isbn)) {
                b = book;
                break;
            }
        }

        if (b == null) {
            System.out.println("ERROR: El usuario no tiene prestado este libro");
            return;
        }

        b.setAv(true);
        u.removeB(b);
        System.out.println("Devolución realizada: " + b.getT() + " por " + u.getN());
    }

    public void showAvailableBooks() {
        System.out.println("\n=== LIBROS DISPONIBLES ===");
        for (Book book : books) {
            if (book.isAv()) {
                System.out.println(book.getInfo());
            }
        }
    }

    public void showBorrowedBooks() {
        System.out.println("\n=== LIBROS PRESTADOS ===");
        for (Book book : books) {
            if (!book.isAv()) {
                System.out.println(book.getInfo());
            }
        }
    }

    public void showUserBooks(String userId) {
        User u = null;
        for (User user : users) {
            if (user.getId().equals(userId)) {
                u = user;
                break;
            }
        }

        if (u == null) {
            System.out.println("ERROR: Usuario no encontrado");
            return;
        }

        System.out.println("\n=== LIBROS DE " + u.getN() + " ===");
        for (Book book : u.getBl()) {
            System.out.println(book.getInfo());
        }
    }

    public void showStats() {
        System.out.println("\n=== ESTADÍSTICAS DE LA BIBLIOTECA ===");
        System.out.println("Biblioteca: " + n);
        System.out.println("Total de libros: " + books.size());
        System.out.println("Total de usuarios: " + users.size());

        int av = 0;
        for (Book book : books) {
            if (book.isAv()) av++;
        }
        System.out.println("Libros disponibles: " + av);
        System.out.println("Libros prestados: " + (books.size() - av));

        Book mostBorrowed = null;
        int maxCount = 0;
        for (Book book : books) {
            if (book.getTc() > maxCount) {
                maxCount = book.getTc();
                mostBorrowed = book;
            }
        }

        if (mostBorrowed != null) {
            System.out.println("Libro más prestado: " + mostBorrowed.getT() + " (" + maxCount + " veces)");
        }
    }

    public void searchByAuthor(String author) {
        System.out.println("\n=== BÚSQUEDA POR AUTOR: " + author + " ===");
        boolean found = false;
        for (Book book : books) {
            if (book.getA().toLowerCase().contains(author.toLowerCase())) {
                System.out.println(book.getInfo());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No se encontraron libros de este autor");
        }
    }

    public void searchByCategory(String category) {
        System.out.println("\n=== BÚSQUEDA POR CATEGORÍA: " + category + " ===");
        boolean found = false;
        for (Book book : books) {
            if (book.getB().equalsIgnoreCase(category)) {
                System.out.println(book.getInfo());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No se encontraron libros en esta categoría");
        }
    }
}