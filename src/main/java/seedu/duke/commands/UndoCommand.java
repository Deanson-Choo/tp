package seedu.duke.commands;

import seedu.duke.library.Library;
import seedu.duke.member.MemberManager;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

/**
 * Command to undo previous commands made
 */

public class UndoCommand extends Command {

    private final int undoCount;

    public UndoCommand(int undoCount) {
        this.undoCount = undoCount;
    }

    @Override
    public boolean execute(Library library, Ui ui, Storage storage, MemberManager memberManager) {
        long undoableCount = library.getUndoManager().getUndoableCommandCount();

        if (undoCount > undoableCount) {
            ui.printError("You only have " + undoableCount + " undoable command(s).");
            return false;
        }

        boolean confirmed = ui.confirmUndo(undoCount);  // new helper method
        if (!confirmed) {
            ui.printMessage("Undo cancelled.");
            return false;
        }

        library.getUndoManager().undoCommands(undoCount, library, ui, storage, memberManager);
        return true;
    }

    @Override
    public void undo(Library library, Ui ui, Storage storage, MemberManager memberManager) {
    }

    @Override
    public String getCommandDescription() {
        return "Undo Command (" + undoCount + " times)";
    }
}
