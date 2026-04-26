package service;

import model.Patron;
import java.util.*;

public class PatronService {

    private final Map<String, Patron> patrons = new HashMap<>();

    public void addPatron(Patron patron) {
        if (patrons.containsKey(patron.getId())) {
            throw new IllegalStateException("Patron already exists");
        }
        patrons.put(patron.getId(), patron);
    }

    public Patron getPatron(String id) {
        return patrons.get(id);
    }

    public void updatePatronName(String id, String newName) {
        Patron p = patrons.get(id);
        if (p != null) {
            p.updateName(newName);
        }
    }
}