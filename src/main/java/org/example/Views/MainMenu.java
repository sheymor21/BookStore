package org.example.Views;


import org.example.Interfaces.BasicMenu;

import java.util.Scanner;

public class MainMenu {

    public void menu() {
        BasicMenu basicMenu;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Book Store");
        System.out.println("[1] -- Books");
        System.out.println("[2] -- Users");
        System.out.println("[3] -- Orders");
        System.out.println("[z] -- exit");
        System.out.println("Select an option:");
        String option = scanner.nextLine();
        switch (option) {
            case "1":
                basicMenu = new BooksMenus(scanner);
                basicMenu.menu();
                break;
            case "2":
                basicMenu = new UsersMenus(scanner);
                basicMenu.menu();
                break;
            case "3":
                basicMenu = new OrdersMenu(scanner);
                basicMenu.menu();
                break;
            case "z":
                System.out.println("Goodbye");
                break;
            default:
                System.out.println("Invalid option");
                scanner.nextLine();
                break;
        }

    }
}
