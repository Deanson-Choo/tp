package seedu.duke.commands;

import seedu.duke.book.QuantityManager;
import seedu.duke.exception.LeBookException;
import seedu.duke.library.Library;
import seedu.duke.member.MemberManager;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public class ListBookQuantityCommand extends Command {
    private final String title;
    private final String author;

    //@@author eth4n22
    public ListBookQuantityCommand(String title, String author) {
        this.title = title;
        this.author = author;
    }

    //@@author eth4n22
    @Override
    public boolean execute(Library library, Ui ui, Storage storage, MemberManager memberManager)
            throws LeBookException {
        assert library != null : "Library should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";
        assert title != null : "Title cannot be null";
        assert author != null : "Author cannot be null";

        int response = QuantityManager.getQuantityManagerInstance().getHowManyBooks(title, author);
        String message = Integer.toString(response);
        String copyOrCopies = (response == 1) ? " copy " : " copies ";
        String areOrIs = (response == 1) ? " is " : " are ";
        ui.printWithSeparator("There" + areOrIs + message + copyOrCopies
                + "of the book: " + title + " by " + author + " ");

        return true;
    }

    @Override
    public void undo(Library library, Ui ui, Storage storage, MemberManager memberManager) {
    }

}
