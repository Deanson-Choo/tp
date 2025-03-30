package seedu.duke.library;

import seedu.duke.shelving.ShelvesManager;
import seedu.duke.book.Book;
import seedu.duke.book.BookManager;

import seedu.duke.exception.BookNotFoundException;

import seedu.duke.member.MemberManager;


import java.util.List;

public class Library {
    private final BookManager catalogueManager;
    private final ShelvesManager shelvesManager;

    public Library(List<Book>allBooks) {
        catalogueManager = new BookManager(allBooks);
        shelvesManager = new ShelvesManager();
    }


    public String listBooks() {
        return catalogueManager.listBooks();
    }

    public String listShelf(String shelfGenre, int shelfIndex) {
        return shelvesManager.listShelf(shelfGenre, shelfIndex);
    }

    public String listBorrowedBooks() {
        return catalogueManager.listBorrowedBooks();
    }

    public String addNewBookToCatalogue(String bookDetails, String genre) {
        return catalogueManager.addNewBookToCatalogue(bookDetails);
    }

    public String addNewBookToShelf(String bookDetails, String genre) {
        return shelvesManager.addBook(bookDetails, genre);
    }

    public String deleteBook(int bookIndex){
        try {
            String bookID = catalogueManager.getBookID(bookIndex);
            String response1 = catalogueManager.deleteBook(bookIndex);
            //assuming the book exists - not a dummy
            shelvesManager.deleteBook(bookID);
            return response1;
        } catch (BookNotFoundException e) {
            return e.getMessage();
        }
    }

    public String updateBookStatus(String commandType, int bookIndex) {
        try {
            String bookID = catalogueManager.getBookID(bookIndex);
            String response1 = catalogueManager.updateBookStatus(commandType, bookIndex);
            //assuming the book exists - not a dummy
            shelvesManager.updateBookStatus(commandType, bookID);
            return response1;
        } catch (BookNotFoundException e) {
            return e.getMessage();
        }


    public List<Book> getBooks() {
        return catalogueManager.getBooks();
    }
}
