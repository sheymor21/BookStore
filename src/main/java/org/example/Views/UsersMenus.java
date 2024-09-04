package org.example.Views;


import org.example.Interfaces.BasicMenu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class UsersMenus implements BasicMenu {
    private final Scanner scanner;

    public UsersMenus(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void menu() {
        System.out.println("Welcome to Books");
        System.out.println("[1] -- Show Users");
        System.out.println("[2] -- Add User");
        System.out.println("[3] -- Update User");
        System.out.println("[4] -- Delete User");
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
        Scanner scanner = new Scanner(System.in);
//        User user = new User();
//        System.out.println("Welcome to Add User");
//        System.out.println("User Dni:");
//        user.Dni = scanner.nextLine();
//        System.out.println("User First Name:");
//        user.FirstName = scanner.nextLine();
//        System.out.println("User Last Name:");
//        user.LastName = scanner.nextLine();
//        System.out.println("User Email:");
//        user.Email = scanner.nextLine();
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test", "root", "password!1");
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM Users";
            var result = statement.executeQuery(query);
            while (result.next()) {
                System.out.println(result.getString("Dni"));
                System.out.println(result.getString("FirstName"));
                System.out.println(result.getString("LastName"));
                System.out.println(result.getString("Email"));
                System.out.println(result.getString("CreateDate"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }
}
