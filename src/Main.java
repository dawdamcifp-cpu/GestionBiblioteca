public class Main {
    public static void main(String[] args) {
        Library lib = new Library("Biblioteca Municipal");

        lib.addBook("Cien años de soledad", "Gabriel García Márquez", "978-0307474728", 1967, "Ficción");
        lib.addBook("Don Quijote de la Mancha", "Miguel de Cervantes", "978-8424116163", 1605, "Clásico");
        lib.addBook("1984", "George Orwell", "978-0451524935", 1949, "Ficción");
        lib.addBook("El principito", "Antoine de Saint-Exupéry", "978-0156012195", 1943, "Infantil");
        lib.addBook("Crónica de una muerte anunciada", "Gabriel García Márquez", "978-0307387295", 1981, "Ficción");
        lib.addBook("La sombra del viento", "Carlos Ruiz Zafón", "978-8408163381", 2001, "Misterio");

        lib.addUser("Juan Pérez", "U001", "juan@email.com", 3);
        lib.addUser("María González", "U002", "maria@email.com", 2);
        lib.addUser("Pedro López", "U003", "pedro@email.com", 5);

        lib.showStats();
        lib.showAvailableBooks();

        lib.borrowBook("U001", "978-0307474728");
        lib.borrowBook("U001", "978-0451524935");
        lib.borrowBook("U002", "978-0156012195");

        lib.showBorrowedBooks();
        lib.showUserBooks("U001");

        lib.returnBook("U001", "978-0307474728");

        lib.searchByAuthor("García Márquez");
        lib.searchByCategory("Ficción");

        lib.showStats();
    }
}