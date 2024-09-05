package org.example.Views;

import org.example.Interfaces.BasicMenu;

import java.util.Scanner;

public class OrdersMenu implements BasicMenu {

    private final Scanner scanner;

    public OrdersMenu(Scanner scanner) {
        this.scanner = scanner;
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
            String option = scanner.nextLine();
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

    }

    @Override
    public void add() {

    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void waitForEnter(Scanner scanner) {
        scanner.nextLine();
    }
}
