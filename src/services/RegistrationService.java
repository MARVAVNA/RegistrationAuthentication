package services;

import model.RegistrationModel;
import org.w3c.dom.ls.LSOutput;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.Scanner;

public class RegistrationService {
    private static RegistrationService instance;
    private RegistrationService() {}

    public static RegistrationService getInstance() {
        if (instance == null) {
            instance = new RegistrationService();
        }

        return instance;
    }
    public void register() {
        Scanner scanner = new Scanner(System.in);

        String fullName = null;
        boolean check = false;
        while (!check) {
            System.out.print("Please enter full name: ");
            fullName = scanner.nextLine();
            check = Validation.fullName(fullName);
        }

        String email = null;
        check = false;
        while (!check) {
            System.out.print("Please enter email: ");
            email = scanner.nextLine();
            check = Validation.email(email);
        }

        String username = null;
        check = false;
        while (!check) {
            System.out.print("Please enter username: ");
            username = scanner.nextLine();
            check = Validation.username(username);
        }

        String password = null;
        check = false;
        while (!check) {
            System.out.print("Please enter password: ");
            password = scanner.nextLine();
            check = Validation.password(password);
        }

        RegistrationModel user = null;
        try {
            user = new RegistrationModel(fullName, username, email, password);
            save(user);
        } catch (IOException e) {
            System.out.println("Something went wrong while saving the user, please try again");
        }
    }

    private void save(RegistrationModel user) throws IOException {
        StringBuilder userInfo = new StringBuilder(user.getFullName())
                .append(", ")
                .append(user.getUserName())
                .append(", ")
                .append(user.getEmail())
                .append(", ")
                .append(user.getPassword())
                .append("\n");
        if (!createFile(System.getProperty("user.dir"), "database.txt")) {
            System.out.println("Failed to create database, please try again");
        } else {
            if (Validation.checkUser(user)) {
                Files.write(Paths.get("database.txt"), userInfo.toString().getBytes(), StandardOpenOption.APPEND);
                System.out.println("User created successfully");
            }
        }
    }

    private boolean createFile(String path, String fileName) throws IOException {
        File file = new File(path + File.separator + fileName);

        if (file.exists()) {
            return true;
        }

        try {
            return file.createNewFile();
        } catch (IOException e) {
            return false;
        }
    }
}
