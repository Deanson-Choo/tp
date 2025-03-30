package seedu.duke.parser;

/*
import org.junit.jupiter.api.Test;
import seedu.duke.commands.AddCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.commands.ExitCommand;
import seedu.duke.commands.ListCommand;
import seedu.duke.commands.ListOverdueUsersCommand;
import seedu.duke.commands.UpdateStatusCommand;
import seedu.duke.exception.LeBookException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
*/

public class ParserTest {
  /*
    @Test
    void testParseValidCommand_addCommand() throws LeBookException {
        Command result = Parser.parse("add harry potter");
        assertNotNull(result);
        assertInstanceOf(AddCommand.class, result);
    }

    @Test
    void testParseValidCommand_listCommand() throws LeBookException {
        Command result = Parser.parse("list");
        assertNotNull(result);
        assertInstanceOf(ListCommand.class, result);
    }

    @Test
    void testParseExitCommand() throws LeBookException {
        Command result = Parser.parse("bye");
        assertInstanceOf(ExitCommand.class, result);
    }

    @Test
    void testParseInvalidCommand() {
        Exception exception = assertThrows(LeBookException.class, () -> Parser.parse("invalidCommand"));
        assertEquals("I don't understand. Try starting with list, add, delete, borrow, return!",
                exception.getMessage());
    }

    @Test
    public void parse_emptyInput_assertionErrorThrown() {
        assertThrows(AssertionError.class, () -> Parser.parse(""));
    }

    @Test
    public void parse_spacesInput_assertionErrorThrown() {
        assertThrows(AssertionError.class, () -> Parser.parse("  "));
    }

    @Test
    public void parse_nullInput_assertionErrorThrown() {
        assertThrows(AssertionError.class, () -> Parser.parse(null));
    }

    @Test
    void testParseBorrowCommand_validIndex() throws LeBookException {
        Command result = Parser.parse("borrow 1");
        assertNotNull(result);
        assertInstanceOf(UpdateStatusCommand.class, result);
    }

    @Test
    void testParseBorrowCommand_invalidIndex() {
        Exception exception = assertThrows(LeBookException.class, () -> Parser.parse("borrow abc"));
        assertEquals("Please provide a book index.", exception.getMessage());
    }

    @Test
    void testParseReturnCommand_validIndex() throws LeBookException {
        Command result = Parser.parse("return 1");
        assertNotNull(result);
        assertInstanceOf(UpdateStatusCommand.class, result);
    }

    @Test
    void testParseReturnCommand_invalidIndex() {
        Exception exception = assertThrows(LeBookException.class, () -> Parser.parse("return xyz"));
        assertEquals("Please provide a book index.", exception.getMessage());
    }

    @Test
    void testParseDeleteCommand_validIndex() throws LeBookException {
        Command result = Parser.parse("delete 1");
        assertNotNull(result);
        assertInstanceOf(DeleteCommand.class, result);
    }

    @Test
    void testParseDeleteCommand_invalidIndex() {
        Exception exception = assertThrows(LeBookException.class, () -> Parser.parse("delete abc"));
        assertEquals("Please provide a book index.", exception.getMessage());
    }

    @Test
    void testParseValidCommand_listOverdueUsersCommand() throws LeBookException {
        Command result = Parser.parse("list users");
        assertNotNull(result);
        assertInstanceOf(ListOverdueUsersCommand.class, result);
    }
    */
}
