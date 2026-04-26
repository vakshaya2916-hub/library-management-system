package observer;

import model.Book;

public class PatronNotifier implements Observer {

    private final String name;

    public PatronNotifier(String name) {
        this.name = name;
    }

    public void update(Book book) {
        System.out.println(name + " notified: " + book.getTitle());
    }
}