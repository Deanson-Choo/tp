package seedu.duke.commands;
//@@author Deanson Choo
import seedu.duke.book.Book;
import seedu.duke.library.Library;
import seedu.duke.member.MemberManager;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

/**
 * Represents a command to delete a book from the library using its title and author.
 * This command is executed when a user specifies the title and author of a book they wish to remove.
 * The deleted book is stored temporarily to support undo functionality.
 */
public class DeleteByBookCommand extends Command {

    private final String bookTitle;
    private final String bookAuthor;
    private Book deletedBook;

    public DeleteByBookCommand(String bookTitle, String bookAuthor) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
    }

    @Override
    public boolean execute(Library library, Ui ui, Storage storage, MemberManager memberManager) {
        assert library != null : "BookManager should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";

        deletedBook = library.getBookByTitleAndAuthor(bookTitle, bookAuthor);

        if (deletedBook == null) {
            ui.printError("Book to delete not found");
            return false;
        }

        String response = library.deleteBook(bookTitle, bookAuthor);
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
            ui.printError("Nothing to undo for DeleteByBookCommand");
        }
    }

    @Override
    public String getCommandDescription() {
        return "Delete a book by its title and author";
    }
}
