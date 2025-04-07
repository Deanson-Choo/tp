package seedu.duke.commands;

import seedu.duke.exception.LeBookException;
import seedu.duke.library.Library;
import seedu.duke.member.MemberManager;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public class AddCommand extends Command {
    private final String title;
    private final String author;
    private final String genre;
    private int addedBookIndex = -1;

    public AddCommand(String title, String author, String genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    @Override
    public boolean execute(Library library, Ui ui, Storage storage, MemberManager memberManager)
            throws LeBookException {
        assert library != null : "Library should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";
        assert title != null : "Title cannot be null";
        assert author != null : "Author cannot be null";

        if (title.trim().contains("|") || author.trim().contains("|")) {
            throw new LeBookException("Book title cannot contain \"|\"");
        }

        String responseForCatalogue = library.addNewBookToCatalogue(title, author, genre);

        if (!responseForCatalogue.equals("This Library does not support this Genre!")) {
            String responseForShelf = library.addNewBookToShelf(title, author, genre);
            ui.printWithSeparator(responseForCatalogue + responseForShelf);
            storage.writeToFile(library.getBooks());
            addedBookIndex = library.getLastAddedBookIndex(title, author);
            return true;
        }
        return false;
    }

    @Override
    public void undo(Library library, Ui ui, Storage storage, MemberManager memberManager) {
        if (addedBookIndex != -1) {
            String result = library.deleteBook(addedBookIndex);
            ui.printWithSeparator("Undo AddCommand:\n" + result);
            storage.writeToFile(library.getBooks());
        } else {
            ui.printError("Nothing to undo for AddCommand.");
        }
    }

    @Override
    public String getCommandDescription() {
        return "Add Command";
    }
}
