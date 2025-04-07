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
 * Command to search for books by their title.
 * This command allows users to search for books where the title contains
 * the provided search term (case-insensitive). It delegates the actual searching
 * to the BookFinder utility class.
 * Usage: find title BOOK_TITLE
 * Example: find title hobbit
 */
public class SearchByTitleCommand extends Command {

    private final String searchTerm;

    public SearchByTitleCommand(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            // Initialize with empty string or handle upstream in Parser, decided to handle in Parser
            this.searchTerm = "";
        } else {
            this.searchTerm = searchTerm.trim();
        }
    }

    /**
     * Executes the title search operation.
     * This method validates the search term, retrieves the book collection,
     * performs the search using BookFinder, and displays the results using the UI.
     * The search results include all books whose titles contain the search term,
     * regardless of where in the title it appears.
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
        // Shouldn't happen if Parser validates, but as a safeguard:
        if (searchTerm.isEmpty()) {
            throw new LeBookException("Please provide a title to search for.");
        }

        BookManager bookManager = library.getBookManager();
        BookFinder finder = new BookFinder(bookManager.getBooks());
        List<Book> results = finder.findBooksByTitle(searchTerm);

        if (results.isEmpty()) {
            ui.printMessage("Sorry, no books found with title '" + searchTerm + "'.");
        } else {
            ui.printMessage("Found " + results.size() + " book with relevant title '" + searchTerm + "':");
            ui.showBookList(results);
        }
        return true;
    }

    @Override
    public void undo(Library library, Ui ui, Storage storage, MemberManager memberManager) {
    }

}
