/*******************************************************************
* Name: Joshua Turner
* Date: 10/22/2023
* Assignment: CIS319 Project
*/
// Class representing the Library
import java.util.ArrayList;
import java.util.List;
public class Library {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public Book getBook(String ISBN) {
        for (Book book : books) {
            if (book.ISBN.equals(ISBN)) {
                return book;
            }
        }
        return null;
    }

    public void updateBook(String ISBN, Book newBook) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).ISBN.equals(ISBN)) {
                books.set(i, newBook);
                return;
            }
        }
    }

    public void deleteBook(String ISBN) {
        books.removeIf(book -> book.ISBN.equals(ISBN));
    }

    public void displayAllBooks() {
        for (Book book : books) {
            System.out.println(book);
            System.out.println("Type: " + book.getType() + "\n");
        }
    }
}