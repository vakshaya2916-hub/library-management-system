package strategy;

import model.Book;
import java.util.*;
import java.util.stream.Collectors;

public class AuthorSearchStrategy implements SearchStrategy {
    public List<Book> search(List<Book> books, String query) {
        return books.stream()
                .filter(b -> b.getAuthor().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
}