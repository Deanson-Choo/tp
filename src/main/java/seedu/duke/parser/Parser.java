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
    private static final String LIST_SHELF = "shelf";

    /**
     * Parses the book index from the given string.
     * The book index is expected to be a 1-based number provided by the user.
     * This method converts it to a 0-based index for internal processing.
     *
     * @param bookDetails The string containing the book index.
     * @return The parsed book index (0-based).
     * @throws LeBookException If the input is not a valid number.
     */
    private static int parseIndex(String bookDetails) throws LeBookException {
        try {
            return Integer.parseInt(bookDetails) - 1;
        } catch (NumberFormatException e) {
            throw new LeBookException("Please provide a valid book index.");
        }
    }

    /**
     * Parses the borrower's name from a borrow command.
     *
     * @param bookDetails The string containing the book index and borrower's name.
     * @return The borrower's name.
     * @throws LeBookException If the input is not in the correct format.
     */
    private static String parseBorrowCommand(String bookDetails) throws LeBookException {
        String[] parts = bookDetails.split("/", 2);
        if (parts.length < 2) {
            throw new LeBookException("Invalid format. It should be: borrow BOOK_INDEX / MEMBER_NAME");
        }
        return parts[1].trim();
    }

    /**
     * Parses the add command and returns an AddCommand object.
     *
     * @param bookDetails The string containing the book title and genre.
     * @return An AddCommand object.
     * @throws LeBookException If the input is not in the correct format.
     */
    private static Command parseAddCommand(String bookDetails) throws LeBookException {
        String[] parts = bookDetails.split("/", 3);
        if (parts.length < 3) {
            throw new LeBookException("Invalid format. It should be: add BOOK_TITLE / AUTHOR_NAME / GENRE");
        }
        String title = parts[0].trim();
        String author = parts[1].trim();
        String genre = parts[2].trim();
        return new AddCommand(title, author, genre);
    }

    /**
     * Parses the list shelf command and returns a ListShelfCommand object.
     *
     * @param bookDetails The string containing the genre and index.
     * @return A ListShelfCommand object.
     * @throws LeBookException If the input is not in the correct format.
     */
    private static Command parseListShelfCommand(String bookDetails) throws LeBookException {
        String[] parts = bookDetails.split("/", 2);
        if (parts.length < 2) {
            throw new LeBookException("Invalid format. It should be: list shelf / GENRE / INDEX");
        }

        String[] shelfDetails = parts[1].split("/", 2);
        if (shelfDetails.length < 2) {
            throw new LeBookException("Invalid format. It should be: list shelf / GENRE / INDEX");
        }

        String genre = shelfDetails[0].trim();
        String indexString = shelfDetails[1].trim();
        int shelfIndex = parseIndex(indexString);
        return new ListShelfCommand(genre, shelfIndex);
    }

    private static Command parseListCommand(String inputDetails) throws LeBookException {
        String listCommandType = inputDetails.trim().toLowerCase();
        if (listCommandType.isEmpty()) {
            return new ListCommand();
        }
        return switch (listCommandType) {
            case LIST_OVERDUE -> new ListOverdueCommand();
            case LIST_BORROWED -> new ListBorrowedCommand();
            case LIST_OVERDUE_USERS -> new ListOverdueUsersCommand();
            default -> throw new LeBookException("Invalid list command type. It should be: list overdue, " +
                    "list borrowed, list users.");
        };
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
        String inputDetails = (fullInput.length > 1) ? fullInput[1] : "";

        int bookIndex;

        switch (commandType) {
        case BYE:
            return new ExitCommand();
        case LIST:
            return parseListCommand(inputDetails);
        case BORROW:
            String bookIndexString = inputDetails.split("/")[0].trim();
            bookIndex = parseIndex(bookIndexString);
            String borrowerName = parseBorrowCommand(inputDetails);
            return new UpdateStatusCommand(commandType, bookIndex, borrowerName);
        case RETURN:
            bookIndex = parseIndex(inputDetails);
            return new UpdateStatusCommand(commandType, bookIndex, null);
        case DELETE:
            bookIndex = parseIndex(inputDetails);
            return new DeleteCommand(bookIndex);
        case ADD:
            return parseAddCommand(inputDetails);
        case HELP:
            return new HelpCommand();
        case LIST_SHELF:
            return parseListShelfCommand(inputDetails);
        default:
            throw new LeBookException("I don't understand. Try starting with list, add, delete, borrow, return!");
        }
    }
}

