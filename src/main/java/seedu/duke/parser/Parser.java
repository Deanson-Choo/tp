package seedu.duke.parser;

import seedu.duke.commands.AddCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.DeleteByIndexCommand;
import seedu.duke.commands.DeleteByBookCommand;
import seedu.duke.commands.ExitCommand;
import seedu.duke.commands.ListBorrowedCommand;
import seedu.duke.commands.ListCommand;
import seedu.duke.commands.ListOverdueCommand;
import seedu.duke.commands.ListShelfCommand;
import seedu.duke.commands.SearchByAuthorCommand;
import seedu.duke.commands.SearchByGenreCommand;
import seedu.duke.commands.SearchByIDCommand;
import seedu.duke.commands.SearchByTitleCommand;
import seedu.duke.commands.UpdateStatusCommand;
import seedu.duke.commands.ListOverdueUsersCommand;
import seedu.duke.commands.DeleteByIDCommand;
import seedu.duke.commands.HelpCommand;
import seedu.duke.commands.ListBookQuantityCommand;
import seedu.duke.commands.StatisticsCommand;
import seedu.duke.commands.UndoCommand;
import seedu.duke.exception.LeBookException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.math.BigInteger;

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
    private static final String FIND = "find";

    private static final String LIST_OVERDUE = "overdue";
    private static final String LIST_BORROWED = "borrowed";
    private static final String LIST_OVERDUE_USERS = "overdue users";
    private static final String LIST_SHELF = "shelf";
    private static final String LIST_QUANTITY = "quantity";

    private static final String DELETE_BY_INDEX = "num";
    private static final String DELETE_BY_BOOK = "bk";
    private static final String DELETE_BY_ID = "id";

    private static final String UNDO = "undo";
    private static final String STATISTICS = "statistics";

    private static final int SPLIT_INTO_TWO = 2;
    private static final int SPLIT_INTO_THREE = 3;
    private static final int MIN_PARTS_TWO = 2;
    private static final int MIN_PARTS_THREE = 3;
    private static final int LENGTH_LIMIT_THREE = 3;
    private static final int LENGTH_LIMIT_TWO = 2;

    private static final Set<String> GENRES = new HashSet<>(Arrays.asList("R", "AC", "H", "MY", "NF", "AD", "SCIF"));

    //@@author jenmarieng

    /**
     * Parses the book index (1-based user input) to a 0-based index.
     *
     * @param indexString The user input index.
     * @return The 0-based index.
     * @throws LeBookException If the input is invalid.
     */
    private static int parseIndex(String indexString) throws LeBookException {
        if (indexString == null || indexString.trim().isEmpty()) {
            throw new LeBookException("Index cannot be empty.");
        }
        try {
            int index = Integer.parseInt(indexString.trim());
            return index - 1;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new LeBookException("Invalid index format. Please provide a number.");
        }
    }

    //@@author jenmarieng

    /**
     * Parses the borrower's name from the full borrow command details string.
     *
     * @param borrowDetails The borrow command details.
     * @return The parsed borrower's name.
     * @throws LeBookException If the format is invalid or borrower name is missing.
     */
    private static String parseBorrowCommand(String borrowDetails) throws LeBookException {
        String[] parts = borrowDetails.split("/", SPLIT_INTO_TWO);
        if (parts.length < MIN_PARTS_TWO || parts[1].trim().isEmpty()) {
            throw new LeBookException("Invalid format. Missing borrower name. "
                    + "Use: borrow BOOK_INDEX / MEMBER_NAME");
        }
        return parts[1].trim();
    }

    //@@author jenmarieng

    /**
     * Parses details for the add command.
     *
     * @param bookDetails The book details string.
     * @return An AddCommand object with parsed details.
     * @throws LeBookException If the format is invalid or details are missing.
     */
    private static Command parseAddCommand(String bookDetails) throws LeBookException {
        if (bookDetails == null || bookDetails.trim().isEmpty()) {
            throw new LeBookException("Missing book details. Format: add TITLE / AUTHOR / GENRE");
        }
        String[] parts = bookDetails.split("/", SPLIT_INTO_THREE);
        if (parts.length < MIN_PARTS_THREE) {
            throw new LeBookException("Invalid format. Format: add TITLE / AUTHOR / GENRE");
        }

        String title = parts[0].trim();
        String author = parts[1].trim();
        String genre = parts[2].trim().toLowerCase();

        return new AddCommand(title, author, genre);
    }

    //@@author jenmarieng

    /**
     * Parses details for the list shelf command.
     * Expects input in the format "shelf / GENRE / SHELF_NUMBER".
     *
     * @param bookDetails The input string containing list shelf command details.
     * @return A ListShelfCommand object containing parsed details.
     * @throws LeBookException If the format is incorrect.
     */
    private static Command parseListShelfCommand(String bookDetails) throws LeBookException {
        String[] parts = bookDetails.split("/", SPLIT_INTO_TWO);
        if (parts.length < LENGTH_LIMIT_TWO) {
            throw new LeBookException("Invalid format. It should be: shelf / GENRE / INDEX");
        }

        String[] shelfDetails = parts[1].split("/", SPLIT_INTO_TWO);
        if (shelfDetails.length < LENGTH_LIMIT_TWO) {
            throw new LeBookException("Invalid format. It should be: shelf / GENRE / INDEX");
        }

        String genre = shelfDetails[0].trim().toLowerCase();
        try {
            String indexString = shelfDetails[1].trim();
            String baseZeroIndex = Integer.toString(Integer.parseInt(indexString) + 1);
            int shelfIndex = parseIndex(baseZeroIndex);
            return new ListShelfCommand(genre, shelfIndex);
        } catch (NumberFormatException e) {
            throw new LeBookException("Invalid format. It should be: shelf / GENRE / INDEX");
        }
    }

    //@@author jenmarieng

    /**
     * Parses the details for the list command and determines the specific list type.
     *
     * @param inputDetails The input string containing the list type.
     * @return A command object corresponding to the specified list type.
     * @throws LeBookException If the list type is unrecognised.
     */
    private static Command parseListCommand(String inputDetails) throws LeBookException {
        String listCommandType = inputDetails.trim().toLowerCase();

        if (listCommandType.isEmpty()) {
            return new ListCommand();
        }

        switch (listCommandType) {
        case LIST_OVERDUE:
            return new ListOverdueCommand();
        case LIST_BORROWED:
            return new ListBorrowedCommand();
        case LIST_OVERDUE_USERS:
            return new ListOverdueUsersCommand();
        default:
            throw new LeBookException("Unknown list type: '" + listCommandType +
                    "'. Valid options: list overdue, list borrowed, list overdue users.");
        }
    }

    //@@author Deanson-Choo

    /**
     * Parses details for the delete command.
     * Supports deletion by book index or book title and author or bookID.
     *
     * @param userInput The input string containing delete command details.
     * @return A DeleteCommand object based on the parsed details.
     * @throws LeBookException If the format is invalid.
     */
    private static Command parseDeleteCommand(String userInput) throws LeBookException {
        String[] parts = userInput.split("/", SPLIT_INTO_TWO);
        if (parts.length < LENGTH_LIMIT_TWO) {
            throw new LeBookException("Invalid format. It should be: delete bk/BOOK_TITLE/AUTHOR_NAME "
                    + "or delete num/BOOK_INDEX or delete id/GENRE-SHELFNUM-SLOTNUM");
        }
        String deleteCommandType = parts[0].trim();
        switch (deleteCommandType) {
        case DELETE_BY_INDEX:
            int bookIndex = parseIndex(parts[1].trim());
            return new DeleteByIndexCommand(bookIndex);
        case DELETE_BY_BOOK:
            String[] bookDetails = parts[1].split("/", SPLIT_INTO_TWO); //should split into title and author
            if (bookDetails.length < LENGTH_LIMIT_TWO) {
                throw new LeBookException("Invalid format. It should be: delete bk/BOOK_TITLE/AUTHOR_NAME");
            }
            String bookTitle = bookDetails[0].trim();
            if (bookTitle.trim().isEmpty()) {
                throw new LeBookException("Book title cannot be empty.");
            }
            String authorName = bookDetails[1].trim();
            if (authorName.trim().isEmpty()) {
                throw new LeBookException("Author name cannot be empty.");
            }
            return new DeleteByBookCommand(bookTitle, authorName);
        case DELETE_BY_ID:
            String bookID = parts[1].trim();
            String[] bookIDSplit = bookID.split("-");
            if (bookIDSplit.length < LENGTH_LIMIT_THREE) {
                throw new LeBookException("Invalid format. It should be: delete id/GENRE-SHELFNUM-SLOTNUM, e.g R-0-0");
            }
            String genre = bookIDSplit[0].trim();
            if (!GENRES.contains(genre)) {
                throw new LeBookException("Invalid Genre!");
            }
            return new DeleteByIDCommand(bookID);
        default:
            throw new LeBookException("Invalid format. It should be: delete bk/BOOK_TITLE/AUTHOR_NAME "
                    + "or delete num/BOOK_INDEX or delete id/GENRE-SHELFNUM-SLOTNUM");
        }
    }


    //@@author WayneCh0y
    private static Command parseListQuantityCommand(String inputDetails) throws LeBookException {
        String[] parts = inputDetails.split("/", SPLIT_INTO_THREE);
        if (parts.length < LENGTH_LIMIT_THREE) {
            throw new LeBookException("Invalid format. It should be: quantity / BOOK_TITLE / AUTHOR_NAME");
        }
        String title = parts[1].trim();
        String author = parts[2].trim();
        return new ListBookQuantityCommand(title, author);
    }

    //@@author jenmarieng

    /**
     * Parses details for the find command, extracting search criteria and term.
     *
     * @param findDetails The input string containing the search criteria and term.
     * @return A command object corresponding to the specified search criteria.
     * @throws LeBookException If the format is incorrect.
     */
    private static Command parseFindCommand(String findDetails) throws LeBookException {
        if (findDetails == null || findDetails.trim().isEmpty()) {
            throw new LeBookException("Missing search criteria and term. Format: find CRITERIA SEARCH_TERM");
        }

        String[] parts = findDetails.trim().split("\\s+", 2);
        if (parts.length < MIN_PARTS_TWO || parts[1].trim().isEmpty()) {
            throw new LeBookException("Missing search term. Format: find CRITERIA SEARCH_TERM");
        }

        String criteria = parts[0].toLowerCase().trim();
        String searchTerm = parts[1].trim();

        switch (criteria) {
        case "title":
            return new SearchByTitleCommand(searchTerm);
        case "author":
            return new SearchByAuthorCommand(searchTerm);
        case "genre":
            return new SearchByGenreCommand(searchTerm.toLowerCase());
        case "id":
            return new SearchByIDCommand(searchTerm);
        default:
            throw new LeBookException("Invalid search criteria '" + criteria +
                    "'. Use 'title', 'author', 'genre', or 'id'.");
        }
    }

    //@@author eth4n22

    /**
     * Parses the input details for the undo command.
     * Determines the number of commands to undo and validates that the value is a positive integer.
     * If no number is specified, defaults to undoing the most recent command (count = 1).
     *
     * @param undoDetails The user input following the "undo" command.
     * @return A new UndoCommand object with the parsed undo count.
     * @throws LeBookException If the format is invalid, the number is non-positive,
     *                         or the value exceeds the maximum allowed integer.
     */
    private static Command parseUndoCommand(String undoDetails) throws LeBookException {
        int count = 1;
        if (!undoDetails.isEmpty()) {
            try {
                BigInteger bigNum = new BigInteger(undoDetails.trim());

                if (bigNum.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0) {
                    throw new LeBookException("Undo count is too large. Maximum allowed is: "
                            + Integer.MAX_VALUE + ".");
                }

                if (bigNum.compareTo(BigInteger.ONE) < 0) {
                    throw new LeBookException("Invalid undo count. Must be at least 1.");
                }

                count = bigNum.intValue(); // only convert to int after the check

            } catch (NumberFormatException e) {
                throw new LeBookException("Invalid undo format. It should be: undo <INTEGER ≥ 1>");
            }
        }
        return new UndoCommand(count);
    }

    //@@author jenmarieng

    /**
     * Parses the full user input string into a Command object.
     *
     * @param userInput The raw user input.
     * @return A Command object corresponding to the user input.
     * @throws LeBookException If the input is invalid or cannot be parsed.
     */
    public static Command parse(String userInput) throws LeBookException {
        assert userInput != null : "User input should not be null";

        String trimmedInput = userInput.trim();
        if (trimmedInput.isEmpty()) {
            throw new LeBookException("Command cannot be empty!");
        }

        String[] fullInput = trimmedInput.split(" ", SPLIT_INTO_TWO);
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
            return parseDeleteCommand(inputDetails);
        case ADD:
            return parseAddCommand(inputDetails);
        case HELP:
            return new HelpCommand();
        case UNDO:
            return parseUndoCommand(inputDetails);
        case FIND:
            return parseFindCommand(inputDetails);
        case LIST_SHELF:
            return parseListShelfCommand(inputDetails);
        case LIST_QUANTITY:
            return parseListQuantityCommand(inputDetails);
        case STATISTICS:
            return new StatisticsCommand();
        default:
            throw new LeBookException("I don't understand. Try \"help\" to see the available commands!");
        }
    }
}
