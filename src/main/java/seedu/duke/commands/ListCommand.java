//@@author Deanson-Choo
package seedu.duke.commands;

import seedu.duke.library.Library;
import seedu.duke.member.MemberManager;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public class ListCommand extends Command {

    @Override
    public boolean execute(Library library, Ui ui, Storage storage, MemberManager memberManager) {
        assert library != null : "BookManager should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";

        String response = library.listBooks();
        ui.printWithSeparator(response);
        return true;
    }
    //@@author
    @Override
    public void undo(Library library, Ui ui, Storage storage, MemberManager memberManager) {
    }

}
