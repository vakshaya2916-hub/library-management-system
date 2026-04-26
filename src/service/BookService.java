package service;

import repository.BookRepository;
import model.Book;
import strategy.SearchStrategy;

import java.util.List;

public class BookService {

    private final BookRepository repo;

    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    public void addBook(Book book) {
        repo.save(book);
    }

    public void removeBook(String isbn) {
        if (!repo.findById(isbn).isPresent()) {
            throw new IllegalStateException("Book not found");
        }
        repo.delete(isbn);
    }

    public List<Book> search(String query, SearchStrategy strategy) {
        return strategy.search(repo.findAll(), query);
    }

    public List<Book> getAllBooks() {
        return repo.findAll();
    }
}