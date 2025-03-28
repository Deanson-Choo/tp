package seedu.duke.commands;

import seedu.duke.exception.LeBookException;
import seedu.duke.library.Library;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public abstract class Command {
    public abstract void execute(Library library, Ui ui, Storage storage) throws LeBookException;

    public boolean isExit() {
        return false;
    }
}
