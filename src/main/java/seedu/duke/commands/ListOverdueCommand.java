package seedu.duke.commands;

import seedu.duke.library.Library;
import seedu.duke.member.MemberManager;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public class ListOverdueCommand extends Command {

    @Override
    public boolean execute(Library library, Ui ui, Storage storage, MemberManager memberManager) {
        String result = library.listOverdueBooks();
        ui.printWithSeparator(result);
        return true;
    }

    @Override
    public void undo(Library library, Ui ui, Storage storage, MemberManager memberManager) {
    }

}
