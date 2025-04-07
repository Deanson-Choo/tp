package seedu.duke.book;

import seedu.duke.commands.Command;
import seedu.duke.library.Library;
import seedu.duke.member.MemberManager;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.util.Stack;

public class UndoManager {
    private final Stack<Command> commandHistory;

    public UndoManager() {
        commandHistory = new Stack<>();
    }

    public void pushCommand(Command command) {
        commandHistory.push(command);
    }

    public long getUndoableCommandCount() {
        return commandHistory.stream().filter(Command::isUndoable).count();
    }

    public void undoCommands(int count, Library library, Ui ui, Storage storage, MemberManager memberManager) {
        if (commandHistory.isEmpty()) {
            ui.printError("No commands to undo!");
            return;
        }

        long undoableCount = commandHistory.stream().filter(Command::isUndoable).count();

        if (count > undoableCount) {
            ui.printError("You only have " + undoableCount + " undoable command(s).");
            return;
        }

        int undoneCount = 0;
        Stack<Command> tempStack = new Stack<>();

        while (!commandHistory.isEmpty() && undoneCount < count) {
            Command lastCommand = commandHistory.pop();
            if (lastCommand.isUndoable()) {
                lastCommand.undo(library, ui, storage, memberManager);
                ui.printSuccess("Successfully undone: " + lastCommand.getCommandDescription());
                undoneCount++;
            } else {
                tempStack.push(lastCommand);
            }
        }
        while (!tempStack.isEmpty()) {
            commandHistory.push(tempStack.pop());
        }
    }

}
