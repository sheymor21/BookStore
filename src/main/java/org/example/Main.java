package org.example;

import org.example.Context.Context;
import org.example.Views.MainMenu;

public class Main {
    public static void main(String[] args) {
        Context context = Context.getInstance();
        context.createDatabase();

        MainMenu mainMenu = new MainMenu();
        mainMenu.menu();
    }
}