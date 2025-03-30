package seedu.duke.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.book.Book;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MemberTest {
    private Member member;
    private Book book1;

    @BeforeEach
    void setUp() {
        member = new Member("John");
        book1 = new Book("Harry Potter", "J.K. Rowling");
    }

    @Test
    void testBorrowBook() {
        member.borrowBook(book1);
        assertTrue(member.getOverdueBooks().isEmpty());
        assertEquals(0, member.getOverdueBooks().size());
    }

    @Test
    void testReturnBook() {
        member.borrowBook(book1);
        member.returnBook(book1);
        assertEquals(0, member.getOverdueBooks().size());
    }

    @Test
    void testGetOverdueBooks() {
        member.borrowBook(book1);
        book1.setReturnDueDate(LocalDate.now().minusDays(5));
        book1.setStatus(true);
        List<Book> overdueBooks = member.getOverdueBooks();
        assertEquals(1, overdueBooks.size());
        assertEquals("Harry Potter", overdueBooks.get(0).getTitle());
    }

    @Test
    void testToString() {
        assertEquals("John", member.toString());
    }
}
