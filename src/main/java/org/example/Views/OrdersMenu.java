package org.example.Views;

import org.example.Dto.OrderDTO;
import org.example.Dto.UserBookIds;
import org.example.Interfaces.BasicMenu;
import org.example.Model.Book;
import org.example.Model.Order;
import org.example.Model.User;
import org.example.Repository.BookRepositoryImp;
import org.example.Repository.OrderRepositoryImp;
import org.example.Repository.UserRepositoryImp;
import org.example.Services.BookService;
import org.example.Services.OrderService;
import org.example.Services.UserService;

import java.util.List;
import java.util.Scanner;

public class OrdersMenu implements BasicMenu {

    private final Scanner scanner;
    private final OrderService orderService;
    private final BookService bookService;
    private final UserService userService;

    public OrdersMenu(Scanner scanner) {
        this.scanner = scanner;
        this.orderService = new OrderService(new OrderRepositoryImp());
        this.bookService = new BookService(new BookRepositoryImp());
        this.userService = new UserService(new UserRepositoryImp());
    }

    @Override
    public void menu() {
        loop:
        while (true) {
            System.out.println("Welcome to Orders");
            System.out.println("[1] -- Show Orders");
            System.out.println("[2] -- Add Orders");
            System.out.println("[3] -- Update Orders");
            System.out.println("[4] -- Delete Orders");
            System.out.println("[z] -- Go Back");
            System.out.println("Select an option:");
            String option = this.scanner.nextLine();
            switch (option) {
                case "1":
                    show();
                    waitForEnter(scanner);
                    break;
                case "2":
                    add();
                    break;
                case "3":
                    update();
                    break;
                case "z":
                    MainMenu mainMenu = new MainMenu();
                    mainMenu.menu();
                    break loop;
                default:
                    System.out.println("Invalid option");
                    waitForEnter(scanner);
                    break;
            }
        }

    }

    @Override
    public void show() {
        loop:
        while (true) {
            Scanner scanner = new Scanner(System.in);
            OrderDTO order;
            System.out.println("welcome to show orders");
            System.out.println("[1] -- Show All");
            System.out.println("[2] -- Search by Id");
            System.out.println("[3] -- Search by Book Title");
            System.out.println("[4] -- Search by User Dni");
            System.out.println("[z] -- Go Back");
            System.out.println("Select an option:");
            String option = scanner.nextLine();
            String formattedString;
            switch (option) {
                case "1":
                    Iterable<OrderDTO> orders = orderService.getAll();
                    for (OrderDTO orderDTO : orders) {
                        formattedString = String.format("\nClient Name: %s\nBook Name: %s\nAuthor: %s\nPrice: %s\nDate: %s", orderDTO.UserName, orderDTO.BookTitle, orderDTO.BookAuthor, orderDTO.BookPrice, orderDTO.PurchaseDate);
                        System.out.println(formattedString);
                    }
                    waitForEnter(scanner);
                    break;
                case "2":
                    System.out.println("Search by Id:");
                    String id = scanner.nextLine();
                    order = orderService.get(id);
                    formattedString = String.format("\nClient Name: %s\nBook Name: %s\nAuthor: %s\nPrice: %s\nDate: %s", order.UserName, order.BookTitle, order.BookAuthor, order.BookPrice, order.PurchaseDate);
                    System.out.println(formattedString);
                    waitForEnter(scanner);
                    break;
                case "3":
                    System.out.println("Search by Book Title:");
                    String title = scanner.nextLine();
                    order = orderService.getByBook(title);
                    formattedString = String.format("\nClient Name: %s\nBook Name: %s\nAuthor: %s\nPrice: %s\nDate: %s", order.UserName, order.BookTitle, order.BookAuthor, order.BookPrice, order.PurchaseDate);
                    System.out.println(formattedString);
                    waitForEnter(scanner);
                    break;
                case "4":
                    System.out.println("Search by User Dni:");
                    String dni = scanner.nextLine();
                    order = orderService.getByUser(dni);
                    formattedString = String.format("\nClient Name: %s\nBook Name: %s\nAuthor: %s\nPrice: %s\nDate: %s", order.UserName, order.BookTitle, order.BookAuthor, order.BookPrice, order.PurchaseDate);
                    System.out.println(formattedString);
                    waitForEnter(scanner);
                    break;
                case "z":
                    menu();
                    break loop;
                default:
                    System.out.println("Invalid option");
                    waitForEnter(scanner);
                    break;
            }
        }
    }

    @Override
    public void add() {
        Scanner scanner = new Scanner(System.in);
        UserBookIds ids = selectBookAndUser(scanner);

        Order order = new Order();
        order.UserId = ids.getUserId();
        order.BookId = ids.getBookId();

        orderService.save(order);

    }

    @Override
    public void update() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Update Orders");
        OrderDTO orderDTO = selectOrderDTO(scanner);
        UserBookIds ids = selectBookAndUser(scanner);
        Order order = new Order();
        order.Id = orderDTO.getId();
        order.BookId = ids.getBookId();
        order.UserId = ids.getUserId();
        orderService.update(order);
    }

    @Override
    public void delete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Delete Orders");
        OrderDTO orderDTO = selectOrderDTO(scanner);
        orderService.delete(orderDTO.getId());

    }

    @Override
    public void waitForEnter(Scanner scanner) {
        scanner.nextLine();
    }

    private OrderDTO selectOrderDTO(Scanner scanner) {
        List<OrderDTO> orders = orderService.getAll();
        int i = 1;
        for (OrderDTO orderDTO : orders) {
            String formattedString = String.format("\n--[%s]--\nClient Name: %s\nBook Name: %s\nAuthor: %s\nPrice: %s\nDate: %s", i, orderDTO.UserName, orderDTO.BookTitle, orderDTO.BookAuthor, orderDTO.BookPrice, orderDTO.PurchaseDate);
            System.out.println(formattedString);
            i++;
        }
        System.out.println("Select by number: ");
        int orderIndex = scanner.nextInt();
        return orders.get(orderIndex - 1);
    }

    private void printUsers(Iterable<User> users) {
        int i = 1;
        for (User user : users) {
            String formattedString = String.format("[%s] --  %s %s", i, user.FirstName, user.LastName);
            System.out.println(formattedString);
            i++;
        }
    }

    private void printBooks(Iterable<Book> books) {
        int i = 1;
        for (Book book : books) {
            String formattedString = String.format("[%s] --  %s by %s", i, book.Title, book.Author);
            System.out.println(formattedString);
            i++;
        }
    }

    private UserBookIds selectBookAndUser(Scanner scanner) {
        System.out.println("Welcome to Update Orders");
        List<Book> books = bookService.getAllBooks();

        System.out.println("Welcome to Add Order");
        printBooks(books);
        System.out.println("Select the book by number: ");
        int bookIndex = scanner.nextInt();
        String bookId = books.get(bookIndex - 1).Id;


        List<User> users = userService.getAllUsers();
        printUsers(users);
        System.out.println("Select the user by number: ");
        int userIndex = scanner.nextInt();
        String userId = users.get(userIndex - 1).Id;
        UserBookIds userBookIds = new UserBookIds();
        userBookIds.setBookId(bookId);
        userBookIds.setUserId(userId);
        return userBookIds;
    }

}
