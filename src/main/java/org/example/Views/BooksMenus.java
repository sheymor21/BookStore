package org.example.Views;


import org.example.Interfaces.BasicMenu;

import java.util.Scanner;

public class BooksMenus implements BasicMenu {
    private final Scanner scanner;

    public BooksMenus(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void menu() {
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
                show();
                break;
            case "2":
                add();
                break;
            case "3":
                update();
                break;
            case "4":
                delete();
                break;
            case "z":
                MainMenu mainMenu = new MainMenu();
                mainMenu.menu();
                break;
            default:
                System.out.println("Invalid option");
                break;

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
}
