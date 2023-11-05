/*******************************************************************
* Name: Joshua Turner
* Date: 10/22/2023
* Assignment: CIS319 Project
*/
public abstract class Book {
    protected String title;
    protected String author;
    protected String ISBN;

    public Book(String title, String author, String ISBN) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
    }

    // Abstract method to be implemented by subclasses
    public abstract String getType();

    @Override
    public String toString() {
        return "Title: " + title + "\nAuthor: " + author + "\nISBN: " + ISBN;
    }
}
