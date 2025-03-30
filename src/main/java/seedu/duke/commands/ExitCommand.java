package seedu.duke.commands;

import seedu.duke.library.Library;
import seedu.duke.member.MemberManager;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(Library library, Ui ui, Storage storage, MemberManager memberManager) {
        assert library != null : "BookManager should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
