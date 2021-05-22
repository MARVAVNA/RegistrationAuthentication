package model;

import custom.Hash;
import services.Validation;

public class RegistrationModel {
    private String fullName;
    private String userName;
    private String email;
    private String password;

    public RegistrationModel(String fullName, String userName, String email, String password) {
        setFullName(fullName);
        setUserName(userName);
        setEmail(email);
        setPassword(password);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        fullName = fullName.trim();
        if (Validation.fullName(fullName)) {
            this.fullName = fullName;
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        username = username.trim();
        if (Validation.username(username)) {
            this.userName = username;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        email = email.trim();
        if (Validation.email(email)) {
            this.email = email;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (Validation.password(password)) {
            this.password = Hash.md5(password);
        }
    }

    public void print() {
        System.out.println("full name: " + fullName);
        System.out.println("user name: " + userName);
        System.out.println("email: " + email);
        System.out.println("password: " + password);
    }
}
