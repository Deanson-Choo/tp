//package seedu.duke.commands;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import seedu.duke.library.Library;
//import seedu.duke.member.MemberManager;
//import seedu.duke.storage.Storage;
//import seedu.duke.ui.Ui;
//import seedu.duke.book.Book;
//
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class UpdateStatusCommandTest {
//    private Library library;
//    private Ui ui;
//    private Storage storage;
//    private MemberManager memberManager;
//
//    @BeforeEach
//    void setUp() {
//        library = Library.getTheOneLibrary(new ArrayList<>());
//        ui = Ui.getUiInstance();
//        storage = Storage.getInstance("data/test_data.txt");
//        memberManager = MemberManager.getInstance();
//
//        library.addNewBookToCatalogue("Title", "Author", "romance");
//        library.addNewBookToShelf("Title", "Author", "romance");
//    }
//
//    @Test
//    void execute_borrowAndUndo_success() {
//        UpdateStatusCommand command = new UpdateStatusCommand("borrow", 0, "John Doe");
//        command.execute(library, ui, storage, memberManager);
//        assertTrue(library.getBooks().get(0).isBorrowed());
//
//        command.undo(library, ui, storage, memberManager);
//        assertFalse(library.getBooks().get(0).isBorrowed());
//    }
//}
//
