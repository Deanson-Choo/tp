package seedu.duke.commands;

import seedu.duke.library.Library;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public class UpdateStatusCommand extends Command {

    private final String commandType;
    private final int bookIndex;

    public UpdateStatusCommand(String commandType, int bookIndex) {
        this.commandType = commandType;
        this.bookIndex = bookIndex;
    }

    @Override
    public void execute(Library library, Ui ui, Storage storage) {
        assert library != null : "BookManager should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";
        assert commandType != null : "Book details cannot be null";
        
        String response = library.updateBookStatus(commandType, bookIndex);
        ui.printWithSeparator(response);
        storage.writeToFile(library.getBooks());
    }
}
