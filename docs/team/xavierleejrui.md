# Xavier Lee Jingrui - Project Portfolio Page

## Overview

LeBook is a CLI-based library book management system that allows users to add, delete, borrow, return, and search for books in a personal catalogue. It aims to help users and librarians manage their inventory efficiently.

## Summary of Contributions

### Features implemented:

- **Core Book Catalogue Management (v1.0)**
  - **What it does**:
    - Established the essential backend system (`BookManager`) for managing the library's book catalogue. This includes adding, deleting, and listing books, as well as managing borrowed status, borrower name, and due dates.
  - **Justification**:
    - This foundational component was key to enabling early user stories such as adding books (#17), deleting books (#18), and listing all books (#19).
  - **Highlights**:
    - Created the `BookManager` skeleton (#26), ensured consistency with assertions (#31), and documented the design (#59). The class was implemented as a **Singleton** to ensure a consistent system state. Testing was done extensively (#85) to ensure reliability for future development.

- **Advanced Search Functionality (v2.0)**
  - **What it does**:
    - Implemented the `find` command which enables users to search the catalogue based on Title, Author, Genre, or unique Book ID. Introduced a `BookFinder` utility class to handle all search logic.
  - **Justification**:
    - Supports user story #109 by helping users locate books more efficiently in larger libraries. Greatly improves usability and flexibility for librarians and users.
  <div style="page-break-after: always;"></div>
  - **Highlights**:
    - Designed the `BookFinder` class to separate search logic from the main catalogue manager, improving modularity. Developed multiple `SearchBy...Command` classes (#111, #112, #113) to perform case-insensitive and keyword-based searches across various fields.

### Code contributed:

**Code contributed:**  
[TP Dashboard](https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2025-02-21&tabOpen=true&tabType=authorship&tabAuthor=Xavierleejrui&tabRepo=AY2425S2-CS2113-T13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Enhancements implemented:

- Designed and implemented the `BookManager` class as a centralized book catalogue manager.
- Enhanced catalogue logic to support:
  - Adding books with genre validation and auto-ID assignment.
  - Deleting books by title-author match or index.
  - Tracking borrower name, borrowed state, and due dates.
- Created the `BookFinder` class and integrated search commands with case-insensitive keyword handling.
- Improved code modularity by separating search logic from catalogue logic.
- Wrote unit tests for `BookManager`, `BookFinder`, and associated commands.

### Contributions to the UG:

- Documented core commands (`add`, `delete`, `list`, `borrow`, `return`).
- Wrote and refined the `find` command section with search criteria explanations and example formats.

### Contributions to the DG:

- Documented the design of:
  - **Book Management** (`BookManager`)
  - **Advanced Searching** (`BookFinder`, `SearchBy...Command`)
- Created and updated:
  - **Class Diagram**: Depicting relationships between `BookManager`, `Book`, `BookFinder`, and Command classes.
  - **Sequence Diagram**: Showing the flow of the "Find Book by Title" command.

<div style="page-break-after: always;"></div>

### Contributions to team-based tasks:

- Participated in Git workflow and helped resolve merge conflicts.
- Actively reviewed teammates’ PRs and gave suggestions to improve code clarity and consistency.
- Supported debugging sessions and shared insights on backend logic.
- Assisted with updating shared sections in the Developer Guide.
- Helped with JAR packaging and testing across systems.

### Review/Mentoring Contributions:

- Reviewed multiple pull requests (e.g., #124, #214), focusing on logic correctness and adherence to coding standards.
- Collaborated with teammates to troubleshoot search and catalogue integration issues.
- Shared implementation guidance for developing commands that interact with `BookManager` and `BookFinder`.

---
