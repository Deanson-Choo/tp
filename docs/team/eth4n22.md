# Ethan Tang Wen Yi - Project Portfolio Page

## Overview

LeBook is a CLI-based library book management system that allows users to
add, borrow, return, and track books efficiently.

## Summary of Contributions

### Features Implemented:

- **Implemented Date tracking of books**
  - What it does:
    - When a book is borrowed, the system automatically assigns a return due date that is 2 weeks from the borrowing date.
  - Justification:
    - Enables tracking of overdue books, improving the accountability for borrowed items.
  - Highlights:
    - Due date is stored and shown in book listing.
    - Used as foundation for other features like `list overdue` ,`list overdue users` and `statistics`.

- **Implemented listing of overdue books**
  - What it does:
    - List all books who have yet to have been returned despite its due date being passed.
  - Justification:
    - Allows librarians to quickly identify overdue books and follow up with the respective members.
  - Highlights:
    - Integrates with due date tracking.
    - Displays borrower name and the respective due dates of overdue books.

<div style="page-break-after: always;"></div>

- **Implemented Undo functionality with multi-level support**
  - What it does:
    - Allows librarians to undo the effects of previously made commands (e.g., `add`, `delete`, `borrow` and `return`).
    - Also provides the option to specify how many commands to undo.
  - Justification:
    - Allows users to have the flexibility to revert accidental errors.
  - Highlights:
    - `UndoManager` maintains a stack of commands that are undoable.
    - Accepts commands such as `undo` or `undo 5` for multi-level undo.
    - Validates undo count and displays the appropriate error/success messages.

- **Implemented user confirmation for undo requests**
  - What it does:
    - Asks users to confirm their undo request with `y/n` after entering an `undo` command.
  - Justification:
    - Prevents accidental undoes such as inputting `20` instead of `2`.
    - Gives users a chance to reconsider undo action.
  - Highlights:
    - Accepts only `y`, `Y`, `n` or `N` as valid responses.
    - Displays warning for invalid inputs.

- **Implemented statistics feature**
  - What it does:
    - Displays key library statistics such as total number of book copies, total number of borrowed books, total number of overdue books,
      total number of unique titles and list of unique titles.
  - Justification:
    - Gives users a quick overview of the library's status in order to aid inventory assessments.
  - Highlights:
    - Statistics is dynamically computed based on current state of catalogue.
    - Neatly formatted output for readability.

### Code contributed:

**Code contributed:** [TP Dashboard](https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2025-02-21&tabOpen=true&tabType=authorship&tabAuthor=eth4n22&tabRepo=AY2425S2-CS2113-T13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

- Implemented the core logic for `Book`, `Ui`, `UndoManager`, `StatisticsCommand`.
- Implemented the following commands:
    - `list overdue`
    - `undo`
    - `undo {number}`
    - `statistics`

<div style="page-break-after: always;"></div>

### Enhancements implemented:

- Introduced multi-level undo using a stack-based system in `UndoManager`.
- Validated undo inputs and implemented confirmation prompts for safety.
- Extended the borrow feature to include auto-generated return due dates.
- Wrote JUnit tests for key components and logic classes.
- Added validation logic for undo command:
  - Prevents undoing more commands than available.
  - Checks if input exceeds `Integer.MAX_VALUE` and provides a clear error message.
  - Rejects invalid inputs like negative or zero.
  - Ensures only successful commands are pushed to the undo stack.

### Contributions to the UG:

- Documented command formats, usage, and examples for:
  - `list overdue`
  - `statistics`
  - `undo`, `undo {number}`
- Ensured clarity and alignment with application behavior.

### Contributions to the DG:

- Authored sections explaining `UndoCommand`, `UndoManager`, and the `StatisticsCommand`.
- Created UML class and sequence diagrams for Undo and Statistics features.
- Maintained consistency and clarity in formatting across the DG.

### Contributions to team-based tasks:

- Actively reviewed PRs submitted by Team members.
- Helped in the resolution of Git merge conflicts.
- Engaged in discussions regarding project work flow.

### Review/Mentoring Contributions:

- Provided reviews for several Pull Requests.
- Reviewed PRs [PR#207, #206, etc].
- Assisted group members in debugging process and understanding implementation concepts.
---
