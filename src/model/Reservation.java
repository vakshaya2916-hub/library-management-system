package model;

import java.util.*;

public class Reservation {
    private final Queue<Patron> queue = new LinkedList<>();

    public void add(Patron patron) {
        queue.offer(patron);
    }

    public Optional<Patron> next() {
        return Optional.ofNullable(queue.poll());
    }

    public Optional<Patron> peek() {
        return Optional.ofNullable(queue.peek());
    }
    public boolean hasNext() {
        return !queue.isEmpty();
    }
}