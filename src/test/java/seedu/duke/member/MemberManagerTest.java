package seedu.duke.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.book.Book;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MemberManagerTest {
    private MemberManager memberManager;

    @BeforeEach
    void setUp() {
        memberManager = new MemberManager();
    }

    @Test
    void testGetMemberByName_newMember() {
        Member member = memberManager.getMemberByName("Alice");
        assertNotNull(member);
        assertEquals("Alice", member.getName());
    }

    @Test
    void testGetMemberByName_existingMember() {
        Member member1 = memberManager.getMemberByName("Bob");
        Member member2 = memberManager.getMemberByName("Bob");
        assertSame(member1, member2);
    }

    @Test
    void testListMembersWithOverdueBooks_noOverdue() {
        String result = memberManager.listMembersWithOverdueBooks();
        assertEquals("No members have overdue books.", result);
    }

    @Test
    void testListMembersWithOverdueBooks_withOverdue() {
        Member member = memberManager.getMemberByName("Charlie");
        Book book1 = new Book("Harry Potter", "J.K. Rowling");
        Book book2 = new Book("Romeo and Juliet", "Shakespeare");
        book1.setReturnDueDate(LocalDate.now().minusDays(3));
        book2.setReturnDueDate(LocalDate.now().minusDays(2));
        member.borrowBook(book1);
        book1.setStatus(true);
        member.borrowBook(book2);
        book2.setStatus(true);

        String result = memberManager.listMembersWithOverdueBooks();
        assertTrue(result.startsWith("Members with overdue books:\n"));
        assertTrue(result.contains("1. Charlie - Harry Potter, Romeo and Juliet"));
    }

}
