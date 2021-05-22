package services;

import custom.CustomFiles;
import custom.Hash;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class LoginService {
    private static LoginService instance;
    private String username;
    private String password;

    private LoginService() {}

    public static LoginService getInstance() {
        if (instance == null) {
            instance = new LoginService();
        }

        return instance;
    }

    public void login() {
        enterUserLoginData();

        List<String> userLines = null;
        try {
            userLines = CustomFiles.readLines(System.getProperty("user.dir") + File.separator + "database.txt");
            if (userLines.size() == 0) {
                System.out.println("Database problems");
            }
        } catch (IOException e) {
            System.out.println("Some error has occurred, please try again");
        }

        if (userLines == null || userLines.size() != 0) {
            password = Hash.md5(password);
            username = username.trim();

            boolean check = false;
            for (String line : userLines) {
                String[] userInfo = line.split(", ");
                if (userInfo.length == 4 && userInfo[1].equals(username) && userInfo[3].equals(password)) {
                    System.out.println("Login success");
                    check = true;
                    break;
                }
            }

            if (!check) {
                System.out.println("Invalid input data");
            }
        }
    }

    private void enterUserLoginData() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        username = scanner.nextLine();

        System.out.print("Enter password: ");
        password = scanner.nextLine();
    }
}
