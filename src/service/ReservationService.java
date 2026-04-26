package service;

import model.*;
import observer.*;

import java.util.*;

public class ReservationService {

    private final Map<String, Reservation> reservations = new HashMap<>();
    private final Notifier notifier;

    public ReservationService(Notifier notifier) {
        this.notifier = notifier;
    }

    public void reserve(Book book, Patron patron) {

        if (book.isAvailable()) {
            System.out.println("Book is available, no need to reserve.");
            return;
        }

        reservations
                .computeIfAbsent(book.getIsbn(), k -> new Reservation())
                .add(patron);

        System.out.println("Book reserved successfully.");
    }

    public void onBookReturned(Book book) {
        Reservation r = reservations.get(book.getIsbn());

        if (r != null && r.hasNext()) {
            r.next();
            notifier.notify(book);
        }
    }


    public boolean isReservedByOther(Book book, Patron patron) {
        Reservation r = reservations.get(book.getIsbn());

        if (r == null) return false;

        Optional<Patron> next = r.peek();

        return next.isPresent() && !next.get().getId().equals(patron.getId());
    }
}