package seedu.duke.commands;

import seedu.duke.book.Book;
import seedu.duke.exception.LeBookException;
import seedu.duke.library.Library;
import seedu.duke.member.MemberManager;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.time.LocalDate;

/**
 * Commands for changing the status of books to borrowed or returned
 */
public class UpdateStatusCommand extends Command {

    private final String commandType;
    private final int bookIndex;
    private final String borrowerName;

    //variables for undo function
    private boolean previousStatus;
    private String previousBorrowerName;
    private LocalDate previousDueDate;

    public UpdateStatusCommand(String commandType, int bookIndex, String borrowerName) {
        this.commandType = commandType;
        this.bookIndex = bookIndex;
        this.borrowerName = borrowerName;
    }

    @Override
    public boolean execute(Library library, Ui ui, Storage storage, MemberManager memberManager)
            throws LeBookException {
        assert library != null : "BookManager should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";
        assert commandType != null : "Command type cannot be null";

        try {
            Book book = library.getBooks().get(bookIndex);
            previousStatus = book.isBorrowed();
            previousBorrowerName = book.getBorrowerName();
            previousDueDate = book.getReturnDueDate();
        } catch (IndexOutOfBoundsException e) {
            throw new LeBookException("Book index is out of bounds!");
        }

        String response = library.updateBookStatus(commandType, bookIndex, borrowerName, memberManager);

        if (response.contains("already borrowed") || response.contains("not currently borrowed")) {
            ui.printError(response);
            return false;
        }

        ui.printWithSeparator(response);
        storage.writeToFile(library.getBooks());
        return true;
    }

    @Override
    public void undo(Library library, Ui ui, Storage storage, MemberManager memberManager) {
        Book book = library.getBooks().get(bookIndex);
        book.setStatus(previousStatus);
        book.setBorrowerName(previousBorrowerName);
        book.setReturnDueDate(previousDueDate);

        storage.writeToFile(library.getBooks());
    }

    @Override
    public String getCommandDescription() {
        if (commandType.equals("borrow")) {
            return "Borrow Command";
        } else if (commandType.equals("return")) {
            return "Return Command";
        } else {
            return "Update Status Command";
        }
    }
}
