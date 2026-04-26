package service;

import model.*;
import strategy.RecommendationStrategy;

import java.util.List;

public class RecommendationService {

    public List<Book> recommend(Patron patron,
                                List<Book> books,
                                RecommendationStrategy strategy) {

        return strategy.recommend(patron, books);
    }
}