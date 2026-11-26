# Object-Oriented Software Design Lab

Semester 4 - MIT Manipal, Data Science & Engineering.

## What this covers

Object-Oriented Software Design lab in Java. The exercises build up one running example - a hotel management system - across the whole semester, starting from plain classes and inheritance and adding enums, threads, synchronization, file I/O, serialization, generics, collections and finally a GUI. The mini project pulls the same domain into a JavaFX desktop application backed by SQLite.

Topics: Classes and inheritance, enums, multithreading, synchronization, I/O streams, serialization, generics, collections, JavaFX and Swing GUIs.

## Contents

### Lab 1 and Lab 2 - classes, inheritance and enums

The start of the hotel-management example that runs through the whole lab. Lab 1 defines a Room base class with StandardRoom and LuxuryRoom subclasses, an amenities class, a Book class, a RoomType enum, and driver classes for a hotel management and a hotel billing system; q2 to q4 are progressive reworkings of the same hierarchy. Lab 2 revisits billing and room booking with the enum in place. The lab-system-handout folder holds the same first weeks as handed out, plus the lecture slides for them.

### Lab 3, 4 and 5 - threads, synchronization and streams

Lab 3 introduces threads: HotelServiceManagement and OrderProcessingSystem each run work on their own thread classes. Lab 4 adds synchronization - a BankAccount guarded by synchronized methods against concurrent Customer threads, and booking/release tasks contending over the same Hotel object. Lab 5 moves to I/O with a byte-stream file copy.

### Lab 6 and Lab 7 - serialization and generics

Lab 6 covers persistence: serializing Room objects to disk, a RandomAccessFile demo, a MiniHotel driver, a BookManager and an exercise writing palindromes out to a file. Lab 7 is the generics week - a generic Pair, bounded type parameters, generic methods and generic arrays, applied back to rooms and charges, with eleven further worked examples in examples/.

### Lab 8, Lab 9 and practice sets

Lab 8 reworks the hotel management system around Room and Customer collections. Lab 9 puts a GUI on it - a button demo, a hotel GUI and a room-price window. Also includes the endsem attempt, two mixed problems (a JavaFX hotel app and a console hotel system), and the practice directory: weeks 3 to 5 lab exercises and tutorials on threads, producer-consumer, synchronization and file streams, plus a midsem cricket-batting simulation that puts batting, fielding and umpire threads against shared state.

## Folders

- [`endsem/`](endsem) - 1 Java source
- [`Lab-01/`](Lab-01) - 8 Java sources, and a few others
- [`Lab-02/`](Lab-02) - 2 Java sources
- [`Lab-03/`](Lab-03) - 2 Java sources
- [`Lab-04/`](Lab-04) - 2 Java sources
- [`Lab-05/`](Lab-05) - 2 Files, 1 Java source
- [`Lab-06/`](Lab-06) - 6 Java sources, 5 Texts, 2 Data files
- [`Lab-07/`](Lab-07) - 15 Java sources, 1 File
- [`Lab-08/`](Lab-08) - 4 Java sources, 1 Text
- [`Lab-09/`](Lab-09) - 3 Java sources, 1 Notes
- [`lab-system-handout/`](lab-system-handout) - 8 Java sources, 2 Slidess, 1 Word document
- [`slides/`](slides) - 4 Slidess, and a few others

## Requirements

JDK 17 or newer. The GUI labs (Lab 9 onward) and the mini project additionally need the JavaFX SDK - the bundled SDK copy is deliberately not committed, so point --module-path at your own JavaFX installation. The mini project is a Maven build and fetches its own dependencies.

## How to run

```sh
javac HotelManagementSystem.java
java HotelManagementSystem

# JavaFX labs
javac --module-path $PATH_TO_FX/lib --add-modules javafx.controls HotelGUI.java

# mini project
cd mini-project && mvn clean javafx:run
```

`Lab-Manual.pdf` in this folder is the lab manual these exercises were set from.

---

Part of my college lab work, shared openly - juniors and classmates are welcome
to use it.
