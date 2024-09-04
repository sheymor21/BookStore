package org.example.Views;



import org.example.Interfaces.BasicMenu;

import java.util.Scanner;

public class MainMenu {

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Book Store");
        System.out.println("[1] -- Books");
        System.out.println("[2] -- Users");
        System.out.println("[z] -- exit");
        System.out.println("Select an option:");
        String option = scanner.nextLine();
        switch (option) {
            case "1":
                BasicMenu booksMenus = new BooksMenus(scanner);
                booksMenus.menu();
                break;
            case "2":
                BasicMenu usersMenus = new UsersMenus(scanner);
                usersMenus.menu();
                break;
            case "z":
                break;
            default:
                System.out.println("Invalid option");
                break;
        }

    }
}
