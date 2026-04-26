package strategy;

import model.*;
import java.util.List;

public interface RecommendationStrategy {
    List<Book> recommend(Patron patron, List<Book> books);
}