package seedu.duke.member;

import seedu.duke.book.Book;

import java.util.ArrayList;
import java.util.List;

public class MemberManager {
    private final List<Member> members;

    public MemberManager() {
        this.members = new ArrayList<>();
    }

    public Member getMemberByName(String name) {
        for (Member member : members) {
            if (member.getName().equalsIgnoreCase(name)) {
                return member;
            }
        }
        Member newMember = new Member(name);
        members.add(newMember);
        return newMember;
    }

    public String listMembersWithOverdueBooks() {
        StringBuilder output = new StringBuilder("Members with overdue books:\n");
        boolean hasOverdueMembers = false;
        int memberIndex = 1;

        for (Member member : members) {
            List<Book> overdueBooks = member.getOverdueBooks();
            if (!overdueBooks.isEmpty()) {
                output.append(memberIndex).append(". ").append(member.getName()).append(" - ");
                for (int i = 0; i < overdueBooks.size(); i++) {
                    output.append(overdueBooks.get(i).getTitle());
                    if (i < overdueBooks.size() - 1) {
                        output.append(", ");
                    }
                }
                output.append("\n");
                hasOverdueMembers = true;
                memberIndex++;
            }
        }
        return hasOverdueMembers ? output.toString() : "No members have overdue books.";
    }
}
