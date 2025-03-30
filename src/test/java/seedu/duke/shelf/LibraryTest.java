package seedu.duke.shelf;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.shelving.shelves.RomanceShelves;
import seedu.duke.book.Book;
import seedu.duke.library.Library;
import seedu.duke.shelving.shelves.Shelf;
import seedu.duke.shelving.ShelvesManager;
import seedu.duke.shelving.shelves.Shelves;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LibraryTest {

    private Library library;

    @BeforeEach
    public void setup() throws Exception {
        Book book = new Book("Test Book", "Author A", false, LocalDate.of(2025, 5, 1), "R-0-0");
        List<Book> catalogBooks = new ArrayList<>();
        catalogBooks.add(book);

        library = new Library(catalogBooks);

        ShelvesManager shelvesManager = new ShelvesManager();


        RomanceShelves romanceShelves = new RomanceShelves();

        Shelf shelf0 = new Shelf(0, "R");


        Field shelfField = Shelf.class.getDeclaredField("shelfBooks");
        shelfField.setAccessible(true);

        shelfField.set(shelf0, catalogBooks);

        Field shelvesField = Shelves.class.getDeclaredField("shelves");
        shelvesField.setAccessible(true);
        Shelf[] shelvesArray = (Shelf[]) shelvesField.get(romanceShelves);
        shelvesArray[0] = shelf0;


        Field romanceShelvesField = ShelvesManager.class.getDeclaredField("romanceShelves");
        romanceShelvesField.setAccessible(true);
        romanceShelvesField.set(shelvesManager, romanceShelves);

        Field shelvesManagerField = Library.class.getDeclaredField("shelvesManager");
        shelvesManagerField.setAccessible(true);
        shelvesManagerField.set(library, shelvesManager);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testDeleteBook_deletesFromCatalogueAndShelf() throws NoSuchFieldException, IllegalAccessException {

        String result = library.deleteBook(0);
        System.out.println(result);  // For debugging output
        assertTrue(result.contains("Book deleted"), "Should include catalog deletion message");
        assertTrue(result.contains("Now you have 0 books"), "Should confirm deletion");

        Field shelvesManagerField = Library.class.getDeclaredField("shelvesManager");
        shelvesManagerField.setAccessible(true);
        ShelvesManager shelvesManager = (ShelvesManager) shelvesManagerField.get(library);


        Field romanceShelvesField = ShelvesManager.class.getDeclaredField("romanceShelves");
        romanceShelvesField.setAccessible(true);
        RomanceShelves romanceShelves = (RomanceShelves) romanceShelvesField.get(shelvesManager);

        Field shelvesField = Shelves.class.getDeclaredField("shelves");
        shelvesField.setAccessible(true);
        Shelf[] shelvesArray = (Shelf[]) shelvesField.get(romanceShelves);
        Shelf shelf0 = shelvesArray[0];

        Field shelfBooksField = Shelf.class.getDeclaredField("shelfBooks");
        shelfBooksField.setAccessible(true);
        List<Book> booksOnShelf = (List<Book>) shelfBooksField.get(shelf0);

        Book bookAfterDelete = booksOnShelf.get(0);
        assertEquals("duMmy", bookAfterDelete.getTitle(), "Book on shelf should be dummy after deletion");
        assertEquals("duMmy", bookAfterDelete.getAuthor(), "Author should be dummy after deletion");
    }
    @Test
    public void testDeleteBook_noExist() {
        String result = library.deleteBook(1);
        System.out.println(result);
        assertTrue(result.contains("Book not found!"), "Book shouldn't exist");
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBorrowBook() throws NoSuchFieldException, IllegalAccessException {
        String result = library.updateBookStatus("borrow", 0);
        System.out.println(result);  // For debugging output
        assertTrue(result.contains("Borrowed"), "Should have borrowed book");
        assertTrue(result.contains("Test Book"), "Should include book title");

        Field shelvesManagerField = Library.class.getDeclaredField("shelvesManager");
        shelvesManagerField.setAccessible(true);
        ShelvesManager shelvesManager = (ShelvesManager) shelvesManagerField.get(library);


        Field romanceShelvesField = ShelvesManager.class.getDeclaredField("romanceShelves");
        romanceShelvesField.setAccessible(true);
        RomanceShelves romanceShelves = (RomanceShelves) romanceShelvesField.get(shelvesManager);

        Field shelvesField = Shelves.class.getDeclaredField("shelves");
        shelvesField.setAccessible(true);
        Shelf[] shelvesArray = (Shelf[]) shelvesField.get(romanceShelves);
        Shelf shelf0 = shelvesArray[0];

        Field shelfBooksField = Shelf.class.getDeclaredField("shelfBooks");
        shelfBooksField.setAccessible(true);
        List<Book> booksOnShelf = (List<Book>) shelfBooksField.get(shelf0);

        Book bookAfterBorrow = booksOnShelf.get(0);
        assertEquals("Test Book", bookAfterBorrow.getTitle(), "Book should be Test Book");
        assertTrue(bookAfterBorrow.isBorrowed(), "Should have borrowed book");
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testReturnBook() throws NoSuchFieldException, IllegalAccessException {
        library.updateBookStatus("borrow", 0); //borrow book first

        String result = library.updateBookStatus("return", 0);
        System.out.println(result);  // For debugging output
        assertTrue(result.contains("Returned"), "Should have borrowed book");
        assertTrue(result.contains("Test Book"), "Should include book title");

        Field shelvesManagerField = Library.class.getDeclaredField("shelvesManager");
        shelvesManagerField.setAccessible(true);
        ShelvesManager shelvesManager = (ShelvesManager) shelvesManagerField.get(library);


        Field romanceShelvesField = ShelvesManager.class.getDeclaredField("romanceShelves");
        romanceShelvesField.setAccessible(true);
        RomanceShelves romanceShelves = (RomanceShelves) romanceShelvesField.get(shelvesManager);

        Field shelvesField = Shelves.class.getDeclaredField("shelves");
        shelvesField.setAccessible(true);
        Shelf[] shelvesArray = (Shelf[]) shelvesField.get(romanceShelves);
        Shelf shelf0 = shelvesArray[0];

        Field shelfBooksField = Shelf.class.getDeclaredField("shelfBooks");
        shelfBooksField.setAccessible(true);
        List<Book> booksOnShelf = (List<Book>) shelfBooksField.get(shelf0);

        Book bookAfterBorrow = booksOnShelf.get(0);
        assertEquals("Test Book", bookAfterBorrow.getTitle(), "Book should be Test Book");
        assertFalse(bookAfterBorrow.isBorrowed(), "Should have returned book");
    }

    @Test
    public void testBorrowBook_noExist() {
        String result = library.updateBookStatus("borrow",1);
        System.out.println(result);
        assertTrue(result.contains("Book not found!"), "Book shouldn't exist");
    }

    @Test
    public void testReturnBook_noExist() {
        String result = library.updateBookStatus("borrow",1);
        System.out.println(result);
        assertTrue(result.contains("Book not found!"), "Book shouldn't exist");
    }
}

