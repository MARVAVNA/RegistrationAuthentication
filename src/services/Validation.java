package services;

import custom.CustomFiles;
import model.RegistrationModel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public static boolean fullName(String txt) {
        txt = txt.replaceAll(" +", " ").trim();
        if (!regexp("^[0-9a-zA-Z'\\s]{3,30}$", txt)) {
            return false;
        }
        String[] name = txt.split(" ");
        int length = txt.length();
        return length > 3 && length < 30 && name.length > 1;
    }

    public static boolean username(String txt) {
        return regexp("^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){8,20}[a-zA-Z0-9]$", txt);
    }

    public static boolean email(String txt) {
        return regexp("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", txt);
    }

    public static boolean password(String txt) {
        return regexp("^(?=.*[0-9]{3,})(?=.*[a-z])(?=.*[A-Z]{2,})(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", txt);
    }

    public static boolean regexp(String regexp, String txt) {
        Pattern pattern = Pattern.compile(regexp, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(txt);
        return matcher.find();
    }

    public static boolean checkUser(RegistrationModel user) {
        try {
            List<String> userList = CustomFiles.readLines(System.getProperty("user.dir") + File.separator + "database.txt");
            for (String list : userList) {
                String[] userAllInfo = list.split(", ");
                if (userAllInfo.length == 4 && (userAllInfo[1].equals(user.getUserName()) || userAllInfo[2].equals(user.getEmail()))) {
                    System.out.println("Invalid data entered");
                    return false;
                }
            }
        } catch (IOException e) {
            System.out.println("Some error has occurred, please try again");
            return false;
        }

        return true;
    }
}
