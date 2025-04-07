package seedu.duke.commands;

import seedu.duke.book.Book;
import seedu.duke.book.BookFinder;
import seedu.duke.book.BookManager;
import seedu.duke.exception.LeBookException;
import seedu.duke.library.Library;
import seedu.duke.member.MemberManager;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.util.List;

/**
 * Command to search for books by their author.
 * This command allows users to search for books where the author name contains
 * the provided search term (case-insensitive). It delegates the actual searching
 * to the BookFinder utility class.
 * Usage: find author AUTHOR_NAME
 * Example: find author tolkien
 */
public class SearchByAuthorCommand extends Command {

    private final String searchTerm;

    public SearchByAuthorCommand(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            this.searchTerm = "";
        } else {
            this.searchTerm = searchTerm.trim();
        }
    }

    /**
     * Executes the author search operation.
     * This method validates the search term, retrieves the book collection,
     * performs the search using BookFinder, and displays the results using the UI.
     *
     * @param library       The library instance containing book data.
     * @param ui            The UI to display output to the user.
     * @param storage       The storage component for data persistence.
     * @param memberManager The manager for library member operations.
     * @throws LeBookException If the search term is empty or if any other error occurs.
     */
    @Override
    public boolean execute(Library library, Ui ui, Storage storage, MemberManager memberManager)
            throws LeBookException {
        if (searchTerm.isEmpty()) {
            throw new LeBookException("Please provide an author name to search for.");
        }
        List<Book> books = null;
        BookManager bookManager = BookManager.getBookManagerInstance(books);
        BookFinder finder = new BookFinder(bookManager.getBooks());
        List<Book> results = finder.findBooksByAuthor(searchTerm);

        if (results.isEmpty()) {
            ui.printMessage("Sorry, no books found with author '" + searchTerm + "'.");
        } else {
            ui.printMessage("Found " + results.size() + " book(s) with author '" + searchTerm + "':");
            ui.showBookList(results);
        }
        return true;
    }

    @Override
    public void undo(Library library, Ui ui, Storage storage, MemberManager memberManager) {
    }

}
