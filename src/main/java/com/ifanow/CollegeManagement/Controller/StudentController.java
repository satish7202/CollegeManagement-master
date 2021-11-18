package com.ifanow.CollegeManagement.Controller;

import com.google.gson.Gson;
import com.ifanow.CollegeManagement.Services.studentServices;
import com.ifanow.CollegeManagement.Models.studentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@RestController
@EnableSwagger2
@RequestMapping("/api")
public class StudentController {
    @Autowired
    studentServices sqloperation;
    List<studentModel> studentmodelsList=new ArrayList<>();


    @GetMapping(path = "/showStudentDetails")

    public String getstudentDetails() {

        Gson studentmodeljson=new Gson();
        String studentjsonObj=studentmodeljson.toJson(sqloperation.SelectStudent());
        System.out.println(studentjsonObj);

        return studentjsonObj;

    }


    @RequestMapping(path = "/inserstudentDetails",method = RequestMethod.POST)
    public  int insertStudentDetails(@RequestParam int studentId ,@RequestParam String studentName ,@RequestParam String departmentName,@RequestParam String studentMobileNo ,@RequestParam String studentAddmissionDate ){
        int counter=0;
        counter=sqloperation.InsertStudent(studentId,studentName,departmentName,studentMobileNo,studentAddmissionDate);

        return counter;
    }

   @PutMapping("/updateStudentDetails")

    public int updateStudentdetails(@RequestParam ("studenId") int studentId, @RequestParam("studentName") String studentName) {
        int count = 0;
       count=sqloperation.UpdateStudent(studentId,studentName);
        return count;
    }

    @RequestMapping(path = "/deleteStudentDetails",method = RequestMethod.DELETE)
    public int deleteStudent(@RequestParam int studentId) throws Exception{

        int count=0;

     count=sqloperation.DeleteStudent(studentId);

    return count;
    }
    @GetMapping("/totalStudentDetails")
    public int totalDetails(){
        int counter=0;
        counter=sqloperation.counttotalDetails();
        return counter;
    }





}
