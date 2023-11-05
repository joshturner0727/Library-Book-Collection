/*******************************************************************
* Name: Joshua Turner
* Date: 10/22/2023
* Assignment: CIS319 Project
*/
// Interface for CRUD operations on books
interface BookOperations {
    void createBook(Book book);
    Book readBook(String ISBN);
    void updateBook(String ISBN, Book newBook);
    void deleteBook(String ISBN);
}