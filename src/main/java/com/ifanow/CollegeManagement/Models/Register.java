package com.ifanow.CollegeManagement.Models;

public class Register {
    String email;
    String password;
    String userName;

    public String getEmail() {
        return email;
    }

    public Register(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.userName = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
