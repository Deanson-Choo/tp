package seedu.duke.Shelving;

import seedu.duke.Shelving.Shelves.ActionShelves;
import seedu.duke.Shelving.Shelves.AdventureShelves;
import seedu.duke.Shelving.Shelves.HorrorShelves;
import seedu.duke.Shelving.Shelves.MysteryShelves;
import seedu.duke.Shelving.Shelves.NonFictionShelves;
import seedu.duke.Shelving.Shelves.RomanceShelves;
import seedu.duke.Shelving.Shelves.SciFiShelves;
import seedu.duke.exception.SectionFullException;

public class ShelvesManager {
    private RomanceShelves romanceShelves;
    private AdventureShelves adventureShelves;
    private ActionShelves actionShelves;
    private HorrorShelves horrorShelves;
    private MysteryShelves mysteryShelves;
    private NonFictionShelves nonFictionShelves;
    private SciFiShelves sciFiShelves;

    private static final String ROMANCE = "romance";
    private static final String ADVENTURE = "adventure";
    private static final String ACTION = "action";
    private static final String HORROR = "horror";
    private static final String MYSTERY = "mystery";
    private static final String NONFICTION = "nonfiction";
    private static final String SCIFI = "scifi";

    private static final String ROMANCE_ID = "R";
    private static final String ADVENTURE_ID = "AD";
    private static final String ACTION_ID = "AC";
    private static final String HORROR_ID = "H";
    private static final String MYSTERY_ID = "MY";
    private static final String NONFICTION_ID = "NF";
    private static final String SCIFI_ID = "SCIF";

    public ShelvesManager() {
        romanceShelves = new RomanceShelves();
        adventureShelves = new AdventureShelves();
        actionShelves = new ActionShelves();
        horrorShelves = new HorrorShelves();
        mysteryShelves = new MysteryShelves();
        nonFictionShelves = new NonFictionShelves();
        sciFiShelves = new SciFiShelves();
    }
    //iterate through shelves by genre
    //if free, add to shelf
    //else throw exception

    public String addBook(String bookDetails, String genre) {
        try {
            switch (genre) {
            case ROMANCE:
                return romanceShelves.addBookToSection(bookDetails);
            case ADVENTURE:
                return adventureShelves.addBookToSection(bookDetails);
            case ACTION:
                return actionShelves.addBookToSection(bookDetails);
            case HORROR:
                return horrorShelves.addBookToSection(bookDetails);
            case MYSTERY:
                return mysteryShelves.addBookToSection(bookDetails);
            case NONFICTION:
                return nonFictionShelves.addBookToSection(bookDetails);
            case SCIFI:
                return sciFiShelves.addBookToSection(bookDetails);
            }
        } catch (SectionFullException e) {
            System.out.println(e);
        }
        return "Added";
    }

    public void deleteBook(String bookID) {
        String[] parts = bookID.split("-");
        String shelfID = parts[0];
        int shelfNum = Integer.parseInt(parts[1]);
        int slotNum = Integer.parseInt(parts[2]);
        switch (shelfID) {
        case ROMANCE_ID:
            romanceShelves.deleteBookFromSection(shelfNum, slotNum);
            break;
        case ADVENTURE_ID:
            adventureShelves.deleteBookFromSection(shelfNum, slotNum);
            break;
        case ACTION_ID:
            actionShelves.deleteBookFromSection(shelfNum, slotNum);
            break;
        case HORROR_ID:
            horrorShelves.deleteBookFromSection(shelfNum, slotNum);
            break;
        case MYSTERY_ID:
            mysteryShelves.deleteBookFromSection(shelfNum, slotNum);
            break;
        case NONFICTION_ID:
            nonFictionShelves.deleteBookFromSection(shelfNum, slotNum);
            break;
        case SCIFI_ID:
            sciFiShelves.deleteBookFromSection(shelfNum, slotNum);
            break;
        default:
            break;
        }
    }

}
