package seedu.duke.commands;

//@@author Deanson-Choo
import seedu.duke.book.Book;
import seedu.duke.library.Library;
import seedu.duke.member.MemberManager;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

/**
 * Represents a command that deletes a book from the library based on its index in the catalog.
 * The command supports undo functionality to restore the previously deleted book.
 */
public class DeleteByIndexCommand extends Command {

    private final int bookIndex;
    private Book deletedBook;

    public DeleteByIndexCommand(int index) {
        this.bookIndex = index;
    }

    @Override
    public boolean execute(Library library, Ui ui, Storage storage, MemberManager memberManager) {
        assert library != null : "BookManager should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";

        if (bookIndex < 0 || bookIndex >= library.getBooks().size()) {
            ui.printError("Invalid book index to delete.");
            return false;
        }

        deletedBook = library.getBooks().get(bookIndex);

        String response = library.deleteBook(bookIndex);
        ui.printWithSeparator(response);
        storage.writeToFile(library.getBooks());
        return true;
    }
    //@@author eth4n22
    @Override
    public void undo(Library library, Ui ui, Storage storage, MemberManager memberManager) {
        if (deletedBook != null) {
            library.restoreBook(deletedBook);
            storage.writeToFile(library.getBooks());
        } else {
            ui.printError("Nothing to undo for DeleteByIndexCommand.");
        }
    }

    @Override
    public String getCommandDescription() {
        return "Delete a book by its index";
    }
}
