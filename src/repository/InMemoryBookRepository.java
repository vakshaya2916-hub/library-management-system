package repository;

import model.Book;
import java.util.*;

public class InMemoryBookRepository implements BookRepository {

    private final Map<String, Book> storage = new HashMap<>();

    public void save(Book book) { storage.put(book.getIsbn(), book); }
    public Optional<Book> findById(String id) { return Optional.ofNullable(storage.get(id)); }
    public List<Book> findAll() { return new ArrayList<>(storage.values()); }
    public void delete(String id) { storage.remove(id); }
}