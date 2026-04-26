package model;

import java.time.LocalDate;

public class Loan {
    private final Book book;
    private final Patron patron;
    private final LocalDate issueDate;
    private LocalDate returnDate;

    public Loan(Book book, Patron patron) {
        this.book = book;
        this.patron = patron;
        this.issueDate = LocalDate.now();
    }

    public Book getBook() { return book; }
    public Patron getPatron() { return patron; }
   // public LocalDate getIssueDate() { return issueDate; }

    public boolean isActive() {
        return returnDate == null;
    }

    public void close() {
        if (!isActive()) throw new IllegalStateException("Already returned");
        returnDate = LocalDate.now();
    }
}