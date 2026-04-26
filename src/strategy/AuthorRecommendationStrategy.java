package strategy;

import model.*;
import java.util.*;
import java.util.stream.Collectors;

public class AuthorRecommendationStrategy implements RecommendationStrategy {

    public List<Book> recommend(Patron patron, List<Book> books) {

        Set<String> authors = patron.getHistory().stream()
                .map(l -> l.getBook().getAuthor())
                .collect(Collectors.toSet());

        return books.stream()
                .filter(b -> authors.contains(b.getAuthor()))
                .collect(Collectors.toList());
    }
}