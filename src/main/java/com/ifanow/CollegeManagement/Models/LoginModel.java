package com.ifanow.CollegeManagement.Models;

public class LoginModel {
    int userId;
    String userName;
    String password,email;

    public int getUserId() {
        return userId;
    }

    public LoginModel(int userId, String userName, String password,String email) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email=email;
    }
    public LoginModel(String password,String email) {
        this.password = password;
        this.email=email;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
