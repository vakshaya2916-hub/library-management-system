package model;

public class Book {
    private final String title;
    private final String author;
    private final String isbn;
    private final int year;
    private boolean available = true;

    public Book(String title, String author, String isbn, int year) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.year = year;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
  //  public int getYear() { return year; }
    public boolean isAvailable() { return available; }

    public void borrow() {
        if (!available) throw new IllegalStateException("Book not available");
        available = false;
    }

    public void returnBook() {
        available = true;
    }

    @Override
    public String toString() {
        return title + " by " + author + " (" + year + ")";
    }
}