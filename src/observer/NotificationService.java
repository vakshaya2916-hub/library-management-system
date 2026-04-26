package observer;

import java.util.*;
import model.Book;

public class NotificationService implements Notifier {

    private final List<Observer> observers = new ArrayList<>();

    public void subscribe(Observer o) {
        observers.add(o);
    }

    public void notify(Book book) {
        observers.forEach(o -> o.update(book));
    }
}