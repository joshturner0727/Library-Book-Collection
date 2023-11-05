/*******************************************************************
* Name: Joshua Turner
* Date: 10/22/2023
* Assignment: CIS319 Project
*/
// Concrete class representing Non-Fiction Books
public class NonFictionBook extends Book {
    public NonFictionBook(String title, String author, String ISBN) {
        super(title, author, ISBN);
    }

    @Override
    public String getType() {
        return "Non-Fiction";
    }
}
