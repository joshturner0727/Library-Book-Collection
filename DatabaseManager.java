import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String DATABASE_URL = "jdbc:sqlite:library.db";

    public static void createDatabase() {
      try {
        // Connect to the database or create it if it doesn't exist
        Connection connection = DriverManager.getConnection(DATABASE_URL);

        // Create a statement
        Statement statement = connection.createStatement();

        // Create the book collection table
        String createTableSQL = "CREATE TABLE IF NOT EXISTS book_collection (" +
                "ISBN TEXT PRIMARY KEY, " +
                "title TEXT, " +
                "author TEXT, " +
                "type TEXT" +
                ");";

        statement.execute(createTableSQL);

        // Close the statement and connection
        statement.close();
        connection.close();

        System.out.println("Database and table created successfully.");
    } catch (SQLException e) {
        System.err.println("Error creating the database: " + e.getMessage());
    }
}
    // Method to insert a new book into the database
    public static void insertBook(Book book) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO book_collection (ISBN, title, author, type) VALUES (?, ?, ?, ?);"
             )) {

            preparedStatement.setString(1, book.ISBN);
            preparedStatement.setString(2, book.title);
            preparedStatement.setString(3, book.author);
            preparedStatement.setString(4, book.getType());

            preparedStatement.executeUpdate();

            System.out.println("Book added to the database.");
        } catch (SQLException e) {
            System.err.println("Error inserting a book: " + e.getMessage());
        }
    }

    // Method to retrieve a book from the database by ISBN
    public static Book getBook(String ISBN) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM book_collection WHERE ISBN = ?;"
             )) {

            preparedStatement.setString(1, ISBN);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String type = resultSet.getString("type");

                if ("Fiction".equals(type)) {
                    return new FictionBook(title, author, ISBN);
                } else {
                    return new NonFictionBook(title, author, ISBN);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving a book: " + e.getMessage());
        }

        return null;
    }

    // Method to update a book in the database
    public static void updateBook(String ISBN, Book newBook) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE book_collection SET title = ?, author = ?, type = ? WHERE ISBN = ?;"
             )) {

            preparedStatement.setString(1, newBook.title);
            preparedStatement.setString(2, newBook.author);
            preparedStatement.setString(3, newBook.getType());
            preparedStatement.setString(4, ISBN);

            preparedStatement.executeUpdate();

            System.out.println("Book updated in the database.");
        } catch (SQLException e) {
            System.err.println("Error updating a book: " + e.getMessage());
        }
    }

    // Method to delete a book from the database by ISBN
    public static void deleteBook(String ISBN) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM book_collection WHERE ISBN = ?;"
             )) {

            preparedStatement.setString(1, ISBN);

            preparedStatement.executeUpdate();

            System.out.println("Book deleted from the database.");
        } catch (SQLException e) {
            System.err.println("Error deleting a book: " + e.getMessage());
        }
    }

    public static List<Book> getAllBooks() {
      List<Book> books = new ArrayList<>();

      try (Connection connection = DriverManager.getConnection(DATABASE_URL);
           PreparedStatement preparedStatement = connection.prepareStatement(
                   "SELECT * FROM book_collection;"
           )) {

          ResultSet resultSet = preparedStatement.executeQuery();

          while (resultSet.next()) {
              String ISBN = resultSet.getString("ISBN");
              String title = resultSet.getString("title");
              String author = resultSet.getString("author");
              String type = resultSet.getString("type");

              Book book;
              if ("Fiction".equals(type)) {
                  book = new FictionBook(title, author, ISBN);
              } else {
                  book = new NonFictionBook(title, author, ISBN);
              }

              books.add(book);
          }
      } catch (SQLException e) {
          System.err.println("Error retrieving all books: " + e.getMessage());
      }

      return books;
  }
}
