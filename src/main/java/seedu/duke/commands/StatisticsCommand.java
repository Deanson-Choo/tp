package seedu.duke.commands;

import seedu.duke.book.Book;
import seedu.duke.library.Library;
import seedu.duke.member.MemberManager;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.util.List;

/**
 * Command to display library statistics.
 */
public class StatisticsCommand extends Command {

    //@@author eth4n22
    @Override
    public boolean execute(Library library, Ui ui, Storage storage, MemberManager memberManager) {
        List<Book> books = null;
        String statistics = Library.getTheOneLibrary(books).getStatistics();
        ui.printWithSeparator(statistics);
        return true;
    }

    @Override
    public void undo(Library library, Ui ui, Storage storage, MemberManager memberManager) {
    }

}
