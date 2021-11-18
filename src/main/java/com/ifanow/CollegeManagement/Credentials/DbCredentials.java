package com.ifanow.CollegeManagement.Credentials;

import org.springframework.stereotype.Component;

@Component
public class DbCredentials {

    public static final String url = "jdbc:mysql://localhost:3306/phpmyadmin";
    public static final String user = "phpmyadmin";
    public static final String pass = "root";
}
