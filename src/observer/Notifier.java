package observer;

import model.Book;

public interface Notifier {
    void notify(Book book);
}