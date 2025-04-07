# Choo Yan Wei, Deanson - Project Portfolio Page

## Overview

LeBook is a CLI-based library book management system that allows users to
add, borrow, return, and track books efficiently.

## Summary of Contributions

### Features implemented:

- **Implemented deletion of a book logic in `Library` (v2.0)**

  - **What does it do**: allows the user to delete a book from the global catalog as well as its
  respective shelf (#137). It does this by calling appropriate methods in `ShelvesManager` and `BookManager`
  - **Justification: Introduces a clear and flexible way for users to manage and organize the library collection**
  and supports the implementation of the shelves feature (#102)
  - **Highlights**: For deleting a book from the shelf, designed the method `deleteBookFromShelf` in `Shelf` such that it
        replaces the book to be deleted with a dummy book allowing for easier iteration of the book ID. Wrote thorough test cases
  to ensure correct catalog and shelf synchronization
  - **Credits**: Reused `deleteBook(int BookIndex)` in `BookManager` implemented by `xavierleejrui`
  to delete a book from the global catalogue.
  
- **Implemented base command classes (v1.0)**
  - **What does it do**: Provides an abstract structure (Command class) to support all future user commands. Also wrote the command classes
  for basic features like add and delete.
  - **Justification**: This component was crucial for supporting early user stories and for enforcing a clean separation of command logic.
- **Implemented base updateStatusCommand (v1.0)**
  - **What does it do**: allows the user to borrow and return a book
  - **Justification**: Enables core user stories such as borrowing (#20) and returning books (#21) as part of LeBook's MVP functionality.
- **Wrote the base LeBook code (v1.0)**
  - **What does it do**: Sets up the main program structure and startup logic
  - **Justification**: Provides the foundational framework and flow for integrating all future features and supporting modules.
  
### Code contributed:

**Code contributed:** [TP Dashboard](https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=&sort=totalCommits%20dsc&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2025-02-21&tabOpen=true&tabType=authorship&tabAuthor=Deanson-Choo&tabRepo=AY2425S2-CS2113-T13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Enhancements implemented:

- Allow a user to delete a book by its bookID
- Allow a user to delete a book by its unique title and author
- Added BookNotFoundException to gracefully handle missing books during deletion
- Designed and implemented dummy book replacement strategy for deleted books on shelves, ensuring ID iteration
- Implemented inheritance for different genres of shelves for better code organisation 
and efficiency. 

### Contributions to the UG:

- Helped with general formatting 
- Wrote `2. Deleting a Book` feature
- Wrote section on `BookID`
- Wrote section on `Genre / Genre_Code`
- Wrote section on `Shelves`

### Contributions to the DG:

- Wrote the content page
- Wrote the `Architecture` part
  - Generated high level architecture diagram using plantUML
- Wrote `Command component`
- Wrote `Library` component 
  - Generated Class diagram using plantUML
- Wrote `BookManager` component
- Wrote `Delete` feature
  - Generated a Sequence diagram using plantUML
- Wrote `List` feature
- Wrote `Book ID` section
- Helped with general formatting

### Contributions to team-based tasks:

- Participated in Git workflow and helped resolve merge conflicts.
- Assisted with assigning relevant issues from each milestones.
- Regularly attended and hosted group meetings to discuss next steps

### Review/Mentoring Contributions:

- Actively reviewed teammates’ PRs and gave suggestions to improve code clarity and consistency.