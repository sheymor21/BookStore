package org.example.Views;


import org.example.Interfaces.BasicMenu;
import org.example.Model.User;
import org.example.Repository.UserRepositoryImp;
import org.example.Services.UserService;

import java.util.List;
import java.util.Scanner;

public class UsersMenus implements BasicMenu {
    private final Scanner scanner;
    private final UserService userService;

    public UsersMenus(Scanner scanner) {
        this.scanner = scanner;
        this.userService = new UserService(new UserRepositoryImp());

    }

    @Override
    public void menu() {
        loop:
        while (true) {
            System.out.println("Welcome to Users");
            System.out.println("[1] -- Show Users");
            System.out.println("[2] -- Add User");
            System.out.println("[3] -- Update User");
            System.out.println("[4] -- Delete User");
            System.out.println("[z] -- Go Back");
            System.out.println("Select an option:");
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    if (userExists()) {
                        show();
                    } else {
                        System.out.println("No users found");
                    }
                    waitForEnter(scanner);
                    break;
                case "2":
                    add();
                    break;
                case "3":
                    if (userExists()) {
                        update();
                    } else {
                        System.out.println("No users found");
                        waitForEnter(scanner);
                    }
                    break;
                case "4":
                    if (userExists()) {
                        delete();
                    } else {
                        System.out.println("No users found");
                        waitForEnter(scanner);
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
        System.out.println("welcome to show users");
        Iterable<User> users = userService.getAllUsers();
        for (User user : users) {
            String formattedString = String.format("\nDni: %s\nName: %s %s\nEmail: %s", user.Dni, user.FirstName, user.LastName, user.Email);
            System.out.println(formattedString);
        }

    }

    @Override
    public void add() {
        Scanner scanner = new Scanner(System.in);
        User user = new User();
        System.out.println("Welcome to Add User");
        System.out.println("User Dni:");
        user.Dni = scanner.nextLine();
        System.out.println("User First Name:");
        user.FirstName = scanner.nextLine();
        System.out.println("User Last Name:");
        user.LastName = scanner.nextLine();
        System.out.println("User Email:");
        user.Email = scanner.nextLine();
        userService.createUser(user);
    }

    @Override
    public void update() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Update User");
        System.out.println("Search by Dni: ");
        String dni = scanner.nextLine();
        User user = userService.getUser(dni);
        if (user == null) {

            System.out.println("That Dni doesn't exist");
            waitForEnter(scanner);
            update();
        } else {
            System.out.printf("\nActual FirstName: %s\nNew FirstName:", user.FirstName);
            user.FirstName = scanner.nextLine();
            System.out.printf("\nActual LastName: %s\nNew LastName:", user.FirstName);
            user.LastName = scanner.nextLine();
            System.out.printf("\nActual Email: %s\nNew Email:", user.FirstName);
            user.Email = scanner.nextLine();
            userService.updateUser(user);

        }

    }

    @Override
    public void delete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Delete User");
        System.out.println("Search by Dni: ");
        String dni = scanner.nextLine();
        User user = userService.getUser(dni);
        if (user == null) {
            System.out.println("That Dni doesn't exist");
            waitForEnter(scanner);
            delete();

        } else {

            userService.deleteUser(user.Id);
        }
    }

    @Override
    public void waitForEnter(Scanner scanner) {
        scanner.nextLine();
    }

    private boolean userExists() {
        List<User> users = userService.getAllUsers();
        return !users.isEmpty();
    }
}
