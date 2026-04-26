package service;

import model.*;

import java.util.*;
import java.util.logging.Logger;

public class LendingService {

    private static final Logger logger = Logger.getLogger(LendingService.class.getName());
    private final Map<String, Loan> activeLoans = new HashMap<>();

    public void checkout(Book book, Patron patron, ReservationService reservationService) {

        if (!book.isAvailable()) {
            throw new IllegalStateException("Book not available");
        }

        if (reservationService.isReservedByOther(book, patron)) {
            throw new IllegalStateException("Book reserved for another patron");
        }

        book.borrow();
        Loan loan = new Loan(book, patron);

        activeLoans.put(book.getIsbn(), loan);
        patron.addLoan(loan);

    }

    public void returnBook(String isbn) {
        Loan loan = activeLoans.get(isbn);

        if (loan == null || !loan.isActive())
            throw new IllegalStateException("No active loan");

        loan.close();
        loan.getBook().returnBook();

        logger.info("Book returned by: " + loan.getPatron().getName());

        activeLoans.remove(isbn);
    }
}