package seedu.duke.member;

import seedu.duke.book.Book;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private final String name;
    private final List<Book> borrowedBooks;

    public Member(String name) {
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    public List<Book> getOverdueBooks() {
        List<Book> overdueBooks = new ArrayList<>();
        for (Book book : borrowedBooks) {
            if (book.isOverdue()) {
                overdueBooks.add(book);
            }
        }
        return overdueBooks;
    }

    @Override
    public String toString() {
        return name;
    }
}
