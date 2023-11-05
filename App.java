import java.util.List;

/*******************************************************************
* Name: Joshua Turner
* Date: 10/22/2023
* Assignment: CIS319 Project
*/
public class App {public static void main(String[] args) {
        DatabaseManager.createDatabase(); // Ensure the database and table are created

        // Insert 4 books
        FictionBook book1 = new FictionBook("The Great Gatsby", "F. Scott Fitzgerald", "978-0743273565");
        NonFictionBook book2 = new NonFictionBook("Sapiens", "Yuval Noah Harari", "978-0062316097");
        FictionBook book3 = new FictionBook("To Kill a Mockingbird", "Harper Lee", "978-0061120084");
        NonFictionBook book4 = new NonFictionBook("Becoming", "Michelle Obama", "978-1524763138");

        DatabaseManager.insertBook(book1);
        DatabaseManager.insertBook(book2);
        DatabaseManager.insertBook(book3);
        DatabaseManager.insertBook(book4);

        // List all books
        List<Book> allBooks = DatabaseManager.getAllBooks();
        System.out.println("All Books:");
        for (Book book : allBooks) {
            System.out.println(book);
            System.out.println("Type: " + book.getType() + "\n");
        }

        // Update a book (e.g., change the author of "Sapiens")
        NonFictionBook updatedBook2 = new NonFictionBook("Sapiens: A Brief History of Humankind", "Yuval Noah Harari", "978-0062316097");
        DatabaseManager.updateBook("978-0062316097", updatedBook2);

        System.out.println("Book 'Sapiens' updated.");

        // Delete a book (e.g., delete "To Kill a Mockingbird")
        DatabaseManager.deleteBook("978-0061120084");

        System.out.println("Book 'To Kill a Mockingbird' deleted.");

        // List all books after update and delete
        allBooks = DatabaseManager.getAllBooks();
        System.out.println("\nAll Books after Update and Delete:");
        for (Book book : allBooks) {
            System.out.println(book);
            System.out.println("Type: " + book.getType() + "\n");
        }
    }
}