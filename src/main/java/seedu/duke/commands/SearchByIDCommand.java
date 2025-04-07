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
 * Command to search for books by their exact shelf ID.
 * This command allows users to search for books using their unique shelf ID
 * (e.g., "AD-0-1"). The search is handled by the BookFinder utility class.
 * BookIDs typically follow the format "GENRE_PREFIX-SHELF_NUMBER-POSITION".
 * Usage: find id SHELF_ID
 * Example: find id AD-0-1
 */
public class SearchByIDCommand extends Command {

    private final String searchTerm;

    public SearchByIDCommand(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            this.searchTerm = "";
        } else {
            // Shelf IDs might be case-sensitive depending on implementation, but BookFinder does case-insensitive.
            this.searchTerm = searchTerm.trim();
        }
    }

    /**
     * Executes the shelf ID search operation.
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
            throw new LeBookException("Please provide a shelf ID to search for (e.g., AD-0-1).");
        }


        BookManager bookManager = library.getBookManager();
        BookFinder finder = new BookFinder(bookManager.getBooks());
        List<Book> results = finder.findBooksByShelfId(searchTerm);

        if (results.isEmpty()) {
            ui.printMessage("Sorry, no books found with the shelf ID '" + searchTerm + "'.");
        } else {
            ui.printMessage("Found " + results.size() + " book(s) with the shelf ID '" + searchTerm + "':");
            ui.showBookList(results);
        }
        return true;
    }

    @Override
    public void undo(Library library, Ui ui, Storage storage, MemberManager memberManager) {
    }

}
