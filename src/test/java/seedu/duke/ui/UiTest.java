package seedu.duke.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    private Ui ui;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        ui = new Ui();
    }

    @Test
    public void testPrintWelcomeMessage() {
        ui.printWelcomeMessage();
        String expected = """
        ========================================
        Welcome to Lebook, your personal book management system!
        ========================================
        """;
        assertEquals(expected.trim(), normalize(outputStream.toString()));
    }

    @Test
    public void testPrintExitMessage() {
        ui.printExitMessage();
        String expected = """
        ========================================
        Goodbye! See you again.
        ========================================
        """;
        assertEquals(expected.trim(), normalize(outputStream.toString()));
    }

    @Test
    public void testPrintSuccessMessage() {
        ui.printSuccess("Book added successfully!");
        String expected = """
        ========================================
        [SUCCESS] Book added successfully!
        ========================================
        """;
        assertEquals(expected.trim(), normalize(outputStream.toString()));
    }

    @Test
    public void testPrintErrorMessage() {
        ui.printError("Invalid command.");
        String expected = """
        ========================================
        [ERROR] Invalid command.
        ========================================
        """;
        assertEquals(expected.trim(), normalize(outputStream.toString()));
    }

    @Test
    public void testPrintWithSeparator() {
        ui.printWithSeparator("This is a test message.");
        String expected = """
        ========================================
        This is a test message.
        ========================================
        """;
        assertEquals(expected.trim(), normalize(outputStream.toString()));
    }

    @Test
    public void testReadCommand() {
        String input = "test command\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Ui uiWithInput = new Ui();
        assertEquals("test command", uiWithInput.readCommand());
    }

    // Helper function to normalize line endings and trim spaces
    private String normalize(String output) {
        return output.replaceAll("\r\n", "\n").trim();
    }
}
