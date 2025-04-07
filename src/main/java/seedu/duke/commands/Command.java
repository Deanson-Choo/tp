package seedu.duke.commands;

import seedu.duke.exception.LeBookException;
import seedu.duke.library.Library;
import seedu.duke.member.MemberManager;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public abstract class Command {
    public abstract boolean execute(Library library, Ui ui, Storage storage, MemberManager memberManager)
            throws LeBookException;

    public abstract void undo(Library library, Ui ui, Storage storage, MemberManager memberManager);


    public String getCommandDescription(){
        return this.getClass().getSimpleName();
    }

    public boolean isUndoable() {
        return !(this instanceof ExitCommand
                || this instanceof HelpCommand
                || this instanceof ListCommand
                || this instanceof ListBorrowedCommand
                || this instanceof ListOverdueCommand
                || this instanceof ListOverdueUsersCommand
                || this instanceof ListShelfCommand
                || this instanceof ListBookQuantityCommand
                || this instanceof StatisticsCommand
                || this instanceof UndoCommand);
    }

    public boolean isExit() {
        return false;
    }
}
