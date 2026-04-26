package model;

import java.util.*;

public class Patron {
    private final String id;
    private String name;
    private final List<Loan> history = new ArrayList<>();

    public Patron(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public String getName() { return name; }

    public void updateName(String name) {
        this.name = name;
    }

    public void addLoan(Loan loan) {
        history.add(loan);
    }

    public List<Loan> getHistory() {
        return Collections.unmodifiableList(history);
    }
}