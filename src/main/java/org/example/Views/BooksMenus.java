package org.example.Views;


import org.example.Interfaces.BasicMenu;
import org.example.Model.Book;
import org.example.Repository.BookRepositoryImp;
import org.example.Services.BookService;

import java.util.List;
import java.util.Scanner;

public class BooksMenus implements BasicMenu {
    private final Scanner scanner;
    private final BookService bookService;

    public BooksMenus(Scanner scanner) {
        this.scanner = scanner;
        bookService = new BookService(new BookRepositoryImp());

    }

    @Override
    public void menu() {

        loop:
        while (true) {
            System.out.println("Welcome to Books");
            System.out.println("[1] -- Show Books");
            System.out.println("[2] -- Add Books");
            System.out.println("[3] -- Update Books");
            System.out.println("[4] -- Delete Books");
            System.out.println("[z] -- Go Back");
            System.out.println("Select an option:");
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    if (booksExists()) {
                        show();
                    } else {
                        System.out.println("No books found");
                    }
                    waitForEnter(scanner);
                    break;
                case "2":
                    add();
                    break;
                case "3":
                    if (booksExists()) {
                        update();
                    } else {
                        System.out.println("No books found");
                    }
                    break;
                case "4":
                    if (booksExists()) {
                        delete();
                    } else {
                        System.out.println("No books found");
                    }
                    break;
                case "z":
                    break loop;
                default:
                    System.out.println("Invalid option");
                    waitForEnter(scanner);
                    break;

            }
        }
        MainMenu mainMenu = MainMenu.getInstance();
        mainMenu.menu();
    }

    @Override
    public void show() {
        System.out.println("welcome to show books");
        List<Book> books = bookService.getAllBooks();
        int size = books.size();
        if (size != 0) {
            for (Book book : books) {
                String formattedString = String.format("\nId: %s\nTitle: %s\nAuthor: %s\nReleaseYear: %s", book.Id, book.Title, book.Author, book.ReleaseYear);
                System.out.println(formattedString);
            }

        } else {
            System.out.println("No books found");
        }
    }

    @Override
    public void add() {
        Book book = new Book();
        System.out.println("Welcome to Add Books");
        System.out.println("Book Title:");
        book.Title = scanner.nextLine();
        System.out.println("Book Author:");
        book.Author = scanner.nextLine();
        System.out.println("Book Release Year:");
        try {
            book.ReleaseYear = Integer.parseInt(scanner.nextLine());
            bookService.createBook(book);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Release Year");
            waitForEnter(scanner);
            add();
        }
    }

    @Override
    public void update() {
        System.out.println("Welcome to Update Books");
        System.out.println("Search by Id: ");
        String id = scanner.nextLine();
        Book book = bookService.getBook(id);
        System.out.printf("\nActual Title: %s\nNew Title:", book.Title);
        book.Title = scanner.nextLine();
        System.out.printf("\nActual Author: %s\nNew Author:", book.Title);
        System.out.printf("\nActual ReleaseYear: %s\nNew ReleaseYear:", book.Title);
        bookService.updateBook(book);

    }

    @Override
    public void delete() {
        System.out.println("Welcome to Delete Books");
        System.out.println("Search by Id: ");
        String id = scanner.nextLine();
        bookService.deleteBook(id);
        menu();

    }

    @Override
    public void waitForEnter(Scanner scanner) {
        scanner.nextLine();
    }

    private boolean booksExists() {
        List<Book> books = bookService.getAllBooks();
        return !books.isEmpty();
    }
}
