package services;

import exception.EnumException;
import services.enums.MainMenu;

import java.util.Scanner;

public class MenuService {
    public static void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean check = false;

        StringBuilder sb = new StringBuilder("-------------------------------------------------\n");
        sb
                .append("Please select an action (selection must be by number):\n")
                .append("1. Registration\n")
                .append("2. Authorization\n")
                .append("3. Exit\n")
                .append("Select: ");

        while (true) {
            System.out.print(sb);

            String number = scanner.next();
            MainMenu selectMenu;
            try {
                selectMenu = MainMenu.getSelectedMenu(number);
            } catch (EnumException e) {
                System.out.println(e);
                continue;
            }

            switch (selectMenu) {
                case REGISTRATION:
                    RegistrationService.getInstance().register();
                    break;
                case AUTHORIZATION:
                    LoginService.getInstance().login();
                    break;
                case EXIT:
                    System.out.println("Exit");
                    check = true;
                    break;
                default:
                    System.out.println("Invalid command entered, please try one of the following: 1, 2 or 3");
            }

            if (check) {
                break;
            }
        }
    }
}