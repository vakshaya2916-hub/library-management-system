import model.*;
import repository.*;
import service.*;
import strategy.*;
import observer.*;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BookRepository repo = new InMemoryBookRepository();
        BookService bookService = new BookService(repo);
        PatronService patronService = new PatronService();
        LendingService lendingService = new LendingService();

        NotificationService notificationService = new NotificationService();
        ReservationService reservationService = new ReservationService(notificationService);
        RecommendationService recommendationService = new RecommendationService();

        notificationService.subscribe(new PatronNotifier("Admin"));

        boolean running = true;

        while (running) {
            try {
                System.out.println("\n===== LIBRARY MENU =====");
                System.out.println("1. Add Book");
                System.out.println("2. Search Book");
                System.out.println("3. Add Patron");
                System.out.println("4. Borrow Book");
                System.out.println("5. Return Book");
                System.out.println("6. Reserve Book");
                System.out.println("7. Recommend Books");
                System.out.println("8. Remove Book");
                System.out.println("9. View All Books");
                System.out.println("10. Exit");

                int choice = sc.nextInt();
                sc.nextLine(); // clear buffer

                switch (choice) {

                    case 1:
                        System.out.print("Title: ");
                        String t = sc.nextLine();

                        System.out.print("Author: ");
                        String a = sc.nextLine();

                        System.out.print("ISBN: ");
                        String i = sc.nextLine();

                        System.out.print("Year: ");
                        int year = sc.nextInt();
                        sc.nextLine();

                        bookService.addBook(new Book(t, a, i, year));
                        System.out.println("Book added.");
                        break;

                    case 2:
                        System.out.println("Search by: 1.Title 2.Author 3.ISBN");
                        int type = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Query: ");
                        String q = sc.nextLine();

                        SearchStrategy strategy;
                        if (type == 1) strategy = new TitleSearchStrategy();
                        else if (type == 2) strategy = new AuthorSearchStrategy();
                        else strategy = new IsbnSearchStrategy();

                        List<Book> results = bookService.search(q, strategy);

                        if (results.isEmpty()) {
                            System.out.println("No books found.");
                        } else {
                            for (Book b : results) {
                                System.out.println(b);
                            }
                        }
                        break;

                    case 3:
                        try {
                            System.out.print("Patron ID: ");
                            String pid = sc.nextLine();

                            System.out.print("Name: ");
                            String name = sc.nextLine();

                            patronService.addPatron(new Patron(pid, name));
                            System.out.println("Patron added.");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 4:
                        System.out.print("ISBN: ");
                        String borrowIsbn = sc.nextLine();

                        System.out.print("Patron ID: ");
                        String borrowPid = sc.nextLine();

                        Book book = repo.findById(borrowIsbn).orElse(null);

                        if (book == null) {
                            System.out.println("Book not found.");
                            break;
                        }

                        Patron patron = patronService.getPatron(borrowPid);

                        if (patron == null) {
                            System.out.println("Invalid Patron.");
                            break;
                        }

                        lendingService.checkout(book, patron, reservationService);
                        System.out.println("Book borrowed.");
                        break;

                    case 5:
                        System.out.print("ISBN: ");
                        String returnIsbn = sc.nextLine();

                        Book returnBook = repo.findById(returnIsbn).orElse(null);

                        if (returnBook == null) {
                            System.out.println("Book not found.");
                            break;
                        }

                        lendingService.returnBook(returnIsbn);
                        reservationService.onBookReturned(returnBook);

                        System.out.println("Book returned.");
                        break;

                    case 6:
                        System.out.print("ISBN: ");
                        String resIsbn = sc.nextLine();

                        System.out.print("Patron ID: ");
                        String resPid = sc.nextLine();

                        Book resBook = repo.findById(resIsbn).orElse(null);

                        if (resBook == null) {
                            System.out.println("Book not found.");
                            break;
                        }

                        Patron resPatron = patronService.getPatron(resPid);

                        if (resPatron == null) {
                            System.out.println("Invalid Patron.");
                            break;
                        }

                        reservationService.reserve(resBook, resPatron);
                        System.out.println("Book reserved.");
                        break;

                    case 7:
                        System.out.print("Patron ID: ");
                        String recId = sc.nextLine();

                        Patron recPatron = patronService.getPatron(recId);

                        if (recPatron == null) {
                            System.out.println("Patron not found.");
                            break;
                        }

                        List<Book> recs = recommendationService.recommend(
                                recPatron,
                                bookService.getAllBooks(),
                                new AuthorRecommendationStrategy()
                        );

                        if (recs.isEmpty()) {
                            System.out.println("No recommendations.");
                        } else {
                            System.out.println("Recommended Books:");
                            for (Book b : recs) {
                                System.out.println(b);
                            }
                        }
                        break;

                    case 8:
                        System.out.print("Enter ISBN to remove: ");
                        String removeIsbn = sc.nextLine();

                        bookService.removeBook(removeIsbn);
                        System.out.println("Book removed.");
                        break;

                    case 9:
                        List<Book> allBooks = bookService.getAllBooks();

                        if (allBooks.isEmpty()) {
                            System.out.println("No books available.");
                        } else {
                            System.out.println("All Books:");
                            for (Book b : allBooks) {
                                System.out.println(b);
                            }
                        }
                        break;

                    case 10:
                        running = false;
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                sc.nextLine(); // clear buffer

            } catch (IllegalStateException e) {
                System.out.println("Operation failed: " + e.getMessage());

            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }

        sc.close();
    }
}