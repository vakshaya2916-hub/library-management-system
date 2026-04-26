package strategy;

import model.Book;
import java.util.*;
import java.util.stream.Collectors;

public class TitleSearchStrategy implements SearchStrategy {
    public List<Book> search(List<Book> books, String query) {
        return books.stream()
                .filter(b -> b.getTitle().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
}