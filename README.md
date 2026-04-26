# Library Management System

## Overview

This project is a simple Library Management System built using Java.
The goal of this project is to understand Object-Oriented Programming concepts, SOLID principles, and basic design patterns.

The system allows managing books, patrons, and lending operations like borrowing, returning, and reserving books.

---

## Features

### Book Management

* Add new books
* Remove books
* View all books
* Search books by:

    * Title
    * Author
    * ISBN

### Patron Management

* Add new patrons
* Each patron has a borrowing history

### Lending System

* Borrow a book
* Return a book
* Tracks active loans

### Reservation System

* Users can reserve books that are already borrowed
* Reservation works as a queue (first come, first served)
* When a book is returned, the next user is notified

### Recommendation System

* Recommends books based on previously borrowed authors

---

## Design Approach

This project follows basic OOP principles:

* Encapsulation → data is kept inside classes
* Abstraction → services handle logic
* Composition → objects are used inside other objects instead of inheritance

---

## Design Patterns Used

### Strategy Pattern

Used for:

* Searching books (title, author, ISBN)
* Recommendation system

### Observer Pattern

Used for:

* Notifying users when a reserved book becomes available

---

## Example Flow

1. Add a book
2. Add two patrons
3. Patron 1 borrows the book
4. Patron 2 reserves the same book
5. When book is returned → Patron 2 gets notified

---



## Class Structure (Simplified)

* model → Book, Patron, Loan, Reservation
* service → BookService, LendingService, ReservationService, etc.
* repository → in-memory storage
* strategy → search and recommendation
* observer → notification system

---

Submitted for assignment
