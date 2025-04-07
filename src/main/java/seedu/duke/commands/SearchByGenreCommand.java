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
 * Command to search for books by their genre.
 * This command allows users to search for books that exactly match the specified genre
 * (case-insensitive). It validates the genre before searching and delegates the actual
 * searching to the BookFinder utility class.
 * Usage: find genre GENRE_NAME
 * Example: find genre adventure
 * Supported genres are: romance, adventure, action, horror, mystery, nonfiction, and scifi.
 */
public class SearchByGenreCommand extends Command {
    public static final String MESSAGE_INVALID_GENRE = "Invalid genre provided. " +
            "Please use one of the supported genres:" +
            "\n" + "  romance, adventure, action, horror, mystery, nonfiction, scifi";

    private final String searchTerm;

    public SearchByGenreCommand(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            this.searchTerm = "";
        } else {
            // Genre search is case-insensitive but store term as provided by parser (usually lowercased)
            this.searchTerm = searchTerm.trim();
        }
    }

    /**
     * Executes the genre search operation.
     * This method validates the search term, checks if the genre is valid,
     * performs the search using BookFinder, and displays the results using the UI.
     *
     * @param library       The library instance containing book data.
     * @param ui            The UI to display output to the user.
     * @param storage       The storage component for data persistence.
     * @param memberManager The manager for library member operations.
     * @throws LeBookException If the search term is empty, the genre is invalid, or if any other error occurs.
     */
    @Override
    public boolean execute(Library library, Ui ui, Storage storage, MemberManager memberManager)
            throws LeBookException {
        if (searchTerm.isEmpty()) {
            throw new LeBookException("Please provide a genre to search for.");
        }

        BookManager bookManager = library.getBookManager();

        // --- Validate Genre FIRST ---
        if (!bookManager.isValidGenre(searchTerm)) {
            throw new LeBookException(MESSAGE_INVALID_GENRE);
        }

        BookFinder finder = new BookFinder(bookManager.getBooks());
        List<Book> results = finder.findBooksByGenre(searchTerm); // Finder uses case-insensitive compare

        if (results.isEmpty()) {
            ui.printMessage("Sorry, no books found with the genre '" + searchTerm + "'.");
        } else {
            ui.printMessage("Found " + results.size() + " book(s) with the genre '" + searchTerm + "':");
            ui.showBookList(results);
        }
        return true;
    }

    @Override
    public void undo(Library library, Ui ui, Storage storage, MemberManager memberManager) {
    }

}
