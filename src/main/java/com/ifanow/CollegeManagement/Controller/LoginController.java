package com.ifanow.CollegeManagement.Controller;

import com.google.gson.Gson;
import com.ifanow.CollegeManagement.Models.LoginModel;
import com.ifanow.CollegeManagement.Services.LoginServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ifanow.CollegeManagement.Models.checkLoginModel;
import com.ifanow.CollegeManagement.Models.Register;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    LoginServices service;
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(path = "/getLoginCount", method = RequestMethod.GET)
    @ResponseBody
    public int getRegistercount()
    {
        int totaldepartment= service.totallogin();
        return totaldepartment;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(path = "/getLogin", method = RequestMethod.GET)
    @ResponseBody

    public String getLogingson() {

        List<String> listLogin;
        LoginServices loginservice = new LoginServices();
        List<LoginModel> ListDao = new ArrayList<>();
        listLogin = loginservice.getLogin();
        String gsonlistDepartment=service.convertListtoJson(listLogin);
        return gsonlistDepartment;
    }


   /* @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(path = "/updateLogin", method = RequestMethod.PUT)
    @ResponseBody
    public int updateDepartment(@RequestParam int userId,@RequestParam String userName,@RequestParam String password) throws IOException
    {
        int userIdString = userId;
        String username = userName;
        String passwprd = password;
        int count = service.update(userId,userName,password);
        return count;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(path = "/deleteLogin", method = RequestMethod.DELETE)
    @ResponseBody
    public int deleteDepartment(@RequestParam int userId )throws IOException
    {
        int userIdString = userId;
        int count = service.delete(userIdString);
        return count;
    }*/
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "/findregisteredUser")
    @ResponseBody
    public String  findregisteredUser(@RequestBody checkLoginModel loginModel )throws IOException
    {
        String emailstring=loginModel.getEmail();
        String passwordString=loginModel.getPassword();
        String  find=service.finduser(emailstring,passwordString);
        Gson gson = new Gson();
        return gson.toJson(find);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(path = "/insertLogin", method = RequestMethod.POST)
    @ResponseBody
    public String  LoginInsert(@RequestBody String r) throws IOException {
        System.out.println(r);
        Gson gson=new Gson();
        Register regi = gson.fromJson(r, Register.class);
        String  usernameString=regi.getUserName();
        String  emailstring=regi.getEmail();
        String passwordstring = regi.getPassword();
        int count = service.Insert(usernameString,emailstring,passwordstring);
        return gson.toJson(String.valueOf(count));
       // return count;
    }
}
