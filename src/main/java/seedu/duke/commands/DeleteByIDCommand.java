package seedu.duke.commands;

//@@author Deanson-Choo
import seedu.duke.book.Book;
import seedu.duke.library.Library;
import seedu.duke.member.MemberManager;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

/**
 * Represents a command to delete a book from the library using its unique book ID.
 * This command is executed when the user provides the book ID.
 * The deleted book is stored to support undo functionality.
 */
public class DeleteByIDCommand extends Command {

    private final String bookID;
    private Book deletedBook;

    public DeleteByIDCommand(String bookID) {
        this.bookID = bookID;
    }

    @Override
    public boolean execute(Library library, Ui ui, Storage storage, MemberManager memberManager) {
        assert library != null : "BookManager should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";

        deletedBook = library.getBookByID(bookID);

        if (deletedBook == null) {
            ui.printError("Book to delete not found");
            return false;
        }

        String response = library.deleteBook(bookID);
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
            ui.printError("Nothing to undo for DeleteByIDCommand");
        }
    }

    @Override
    public String getCommandDescription() {
        return "Delete a book by its ID";
    }
}
