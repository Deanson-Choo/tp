package seedu.duke.parser;

import seedu.duke.commands.Command;
import seedu.duke.commands.ExitCommand;
import seedu.duke.commands.AddCommand;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.commands.HelpCommand;
import seedu.duke.commands.ListBorrowedCommand;
import seedu.duke.commands.ListCommand;
import seedu.duke.commands.ListOverdueCommand;
import seedu.duke.commands.ListOverdueUsersCommand;
import seedu.duke.commands.UpdateStatusCommand;
import seedu.duke.commands.ListShelfCommand;

import seedu.duke.exception.LeBookException;

/**
 * Parses user input and returns the corresponding command.
 */
public class Parser {

    private static final String BYE = "bye";
    private static final String ADD = "add";
    private static final String LIST = "list";
    private static final String BORROW = "borrow";
    private static final String RETURN = "return";
    private static final String DELETE = "delete";
    private static final String HELP = "help";
    private static final String LIST_OVERDUE = "overdue";
    private static final String LIST_BORROWED = "borrowed";
    private static final String LIST_OVERDUE_USERS = "users";
    private static final String LISTSHELF = "shelf";

    /**
     * Parses the book index from the given string.
     * The book index is expected to be a 1-based number provided by the user.
     * This method converts it to a 0-based index for internal processing.
     *
     * @param bookDetails The string containing the book index.
     * @return The parsed book index (0-based).
     * @throws LeBookException If the input is not a valid number.
     */
    private static int parseBookIndex(String bookDetails) throws LeBookException {
        try {
            return Integer.parseInt(bookDetails) - 1;
        } catch (NumberFormatException e) {
            throw new LeBookException("Please provide a book index.");
        }
    }

    /**
     * Parses user input and returns the corresponding command.
     *
     * @param userInput The input string.
     * @return The corresponding command object.
     * @throws LeBookException If the input is invalid.
     */
    public static Command parse(String userInput) throws LeBookException {
        assert userInput != null : "User input should not be null";
        assert !userInput.trim().isEmpty() : "User input should not be empty";

        String[] fullInput = userInput.split(" ", 2);
        String commandType = fullInput[0].toLowerCase();
        String bookDetails = (fullInput.length > 1) ? fullInput[1] : "";

        String[] inputSplitByRegex = userInput.split("/", 3);
        if (inputSplitByRegex.length < 3) {
            commandType = "";
        }

        int bookIndex;

        switch (commandType) {
        case BYE:
            return new ExitCommand();
        case LIST:
            String listCommandType = bookDetails.trim().toLowerCase();
            if (LIST_OVERDUE.equals(listCommandType)) {
                return new ListOverdueCommand();
            }
            if (LIST_BORROWED.equals(listCommandType)) {
                return new ListBorrowedCommand();
            }
            if (LIST_OVERDUE_USERS.equals(listCommandType)) {
                return new ListOverdueUsersCommand();
            }
            return new ListCommand();
        case BORROW, RETURN:
            bookIndex = parseBookIndex(bookDetails);
            return new UpdateStatusCommand(commandType, bookIndex);
        case DELETE:
            bookIndex = parseBookIndex(bookDetails);
            return new DeleteCommand(bookIndex);
        case ADD:
            String genre = userInput.split("/", 3)[2].trim(); // Jermaine make this nice please
            return new AddCommand(bookDetails, genre);
        case HELP:
            return new HelpCommand();
        case LISTSHELF:
            String shelfGenre = userInput.split("/", 3)[1].trim(); // Jermaine make this nice please
            int index = Integer.parseInt(userInput.split("/", 3)[2].trim());
            return new ListShelfCommand(shelfGenre, index);
        default:
            throw new LeBookException("I don't understand. Try starting with list, add, delete, borrow, return!");
        }
    }
}

