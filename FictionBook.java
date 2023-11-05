/*******************************************************************
* Name: Joshua Turner
* Date: 10/22/2023
* Assignment: CIS319 Project
*/
// Concrete class representing Fiction Books
public class FictionBook extends Book {
    public FictionBook(String title, String author, String ISBN) {
        super(title, author, ISBN);
    }

    @Override
    public String getType() {
        return "Fiction";
    }
}