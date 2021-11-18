package com.ifanow.CollegeManagement.Controller;

import com.ifanow.CollegeManagement.Models.DepartmentModel;
import com.ifanow.CollegeManagement.Services.DepartmentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController

@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    DepartmentServices departmentServices;

    @RequestMapping(path = "/getDepartmentCount", method = RequestMethod.GET)
    @ResponseBody
    public int getdepartmentcount()
    {
        int totaldepartment= departmentServices.totaldepartments();
        return totaldepartment;
    }


    @RequestMapping(path = "/getDepartment", method = RequestMethod.GET)
    @ResponseBody

    public String getdepartmentgson() {

        List<String> listdepartment;
        DepartmentServices deptservice = new DepartmentServices();
        List<DepartmentModel> Listifa = new ArrayList<>();
        System.out.println("hello world");
        listdepartment = departmentServices.getDepartment();
        String gsonlistDepartment=departmentServices.convertListtoJson(listdepartment);
        return gsonlistDepartment;

    }



    @RequestMapping(path = "/insertDepartment", method = RequestMethod.POST)
    @ResponseBody
    public int DepartmentInsert(@RequestParam String deptname,@RequestParam String deptHead,@RequestParam String teachersall) throws IOException {
        //Declaration
        //int deptIdd=deptId;
        String deptnamestring = deptname;
        String deptHeadstring = deptHead;
        String teachersallstring =teachersall;
        int count = departmentServices.Insert(deptname,deptHead,teachersall);
        return count;
    }





    @RequestMapping(path = "/updateDepartment", method = RequestMethod.PUT)
    @ResponseBody
    public int updateDepartment(@RequestParam int deptId,@RequestParam String deptname,@RequestParam String deptHead,@RequestParam String teachersall) throws IOException
    {
        int deptIdstring = deptId;
        String deptnamestring = deptname;
        String deptHeadstring = deptHead;
        String teachersallstring = teachersall;
        int count = departmentServices.update(deptId,deptname,deptHead,teachersall);
        return count;

    }
    @RequestMapping(path = "/deleteDepartment", method = RequestMethod.DELETE)
    @ResponseBody
    public int deleteDepartment(@RequestParam("deptId") int deptId )throws IOException
    {
        int deptIdstring = deptId;
        int count = departmentServices.delete(deptId);
        return count;
    }


}
