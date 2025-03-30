package seedu.duke.shelf.shelftest;

import seedu.duke.shelving.shelves.Shelf;
import seedu.duke.book.Book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class UpdateStatusTest {
    private Shelf shelf;
    @BeforeEach
    public void setup() throws NoSuchFieldException, IllegalAccessException {
        shelf = new Shelf(0, "R");
        List<Book> books = new ArrayList<>();
        Book book1 = new Book("Harry Potter", "J.K Rowling", false, LocalDate.of(2025, 4, 15));
        Book book2 = new Book("Jerry Potter", "J.K Howling", true, LocalDate.of(2024, 4, 15));
        Book book3 = new Book("Garry Potter", "J.K Dowling", false, LocalDate.of(2023, 4, 15));
        books.add(book1);
        books.add(book2);
        books.add(book3);

        Field shelfField = Shelf.class.getDeclaredField("shelfBooks");
        shelfField.setAccessible(true);

        Field booksNumField = Shelf.class.getDeclaredField("booksCurrentlyOnShelf");
        booksNumField.setAccessible(true);

        shelfField.set(shelf, books);
        booksNumField.set(shelf, 3);
    }

    @Test
    public void borrowBookInShelfTest() {
        shelf.updateBookStatusInShelf("borrow", 0);
        List<Book> books = shelf.getShelfBooks();
        Book book = books.get(0);
        assertTrue(book.isBorrowed(), "Book should be borrowed");
    }

    @Test
    public void returnBookInShelfTest() {
        shelf.updateBookStatusInShelf("return", 1);
        List<Book> books = shelf.getShelfBooks();
        Book book = books.get(1);
        assertFalse(book.isBorrowed(), "Book should not be borrowed");
    }
}
