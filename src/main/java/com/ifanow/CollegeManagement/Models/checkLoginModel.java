package com.ifanow.CollegeManagement.Models;

public class checkLoginModel {


        String password,email;




        public checkLoginModel(String password,String email) {
            this.password = password;
            this.email=email;
        }


        public String getEmail() {
            return email;
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


}
