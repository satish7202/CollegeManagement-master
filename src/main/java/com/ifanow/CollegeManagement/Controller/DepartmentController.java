package com.ifanow.CollegeManagement.Controller;

import com.google.gson.Gson;
import com.ifanow.CollegeManagement.Models.AttendenceInsertModel;
import com.ifanow.CollegeManagement.Models.AttendenceUpdateModel;
import com.ifanow.CollegeManagement.Models.DepartmentModel;
import com.ifanow.CollegeManagement.Services.DepartmentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DepartmentController {
    Gson gson=new Gson();
    @Autowired
    DepartmentServices departmentServices;

    @RequestMapping(path = "/getDepartmentCount", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = " http://localhost:4200")
    public int getdepartmentcount()
    {
        int totaldepartment= departmentServices.totaldepartments();
        return totaldepartment;
    }


    @RequestMapping(path = "/getDepartment", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin(origins = " http://localhost:4200")

    public String  getdepartmentgson() {

        List<String> listdepartment;
        Gson deptModeljson=new Gson();
        DepartmentServices deptservice = new DepartmentServices();
        String deptsonObj =deptModeljson.toJson(deptservice.getDepartment());
        // String get = departmentServices.getDepartment();
        // String gsonlistDepartment=departmentServices.convertListtoJson(deptsonObj);
        return deptsonObj;

    }
    @CrossOrigin(origins = " http://localhost:4200")
    @RequestMapping(path = "/insertDepartment", method = RequestMethod.POST)
    @ResponseBody
    public String  DepartmentInsert(@RequestBody String departmentmodel) throws IOException {
        DepartmentModel deptmodel=gson.fromJson(departmentmodel, DepartmentModel.class);

        String deptname = deptmodel.getDepartmentName();
        String depthead=deptmodel.getDepartmentHead();
        // ArrayList<String> teachersAll=deptmodel.g
        String [] teachersAll= deptmodel.getTeachersAll();
        //ArrayList<String>   teachersall=deptmodel.getTeachersAll();
        departmentServices.Insert(deptname,depthead,teachersAll);
        String count =gson.toJson("Data Successfully  Inserted..No of Rows="+departmentServices.Insert(deptname,depthead,teachersAll));
        return count;
    }
    @CrossOrigin(origins = " http://localhost:4200")
    @RequestMapping(path = "/updateDepartment", method = RequestMethod.PUT)
    @ResponseBody
    public String updateDepartment(@RequestBody String updatedepartment) throws IOException
    {
        int updatedRow=0;
        Gson gson=new Gson();
        DepartmentModel deptmodel=gson.fromJson(updatedepartment,DepartmentModel.class);
        int deptId=deptmodel.getDepartmentId();
        String deptname=deptmodel.getDepartmentName();
        String deptHead=deptmodel.getDepartmentHead();
        //  String teachersall=deptmodel.getTeachersAll();
        // String[] teachersall= deptmodel.getTeachersAll();
        updatedRow=departmentServices.update(deptId,deptname,deptHead);
        return gson.toJson("Successfully..Updated Rows="+String.valueOf(updatedRow));

    }
    @CrossOrigin(origins = " http://localhost:4200")
    @RequestMapping(path = "/deleteDepartment", method = RequestMethod.DELETE)
    @ResponseBody
    public int deleteDepartment(@RequestParam("deptId") int deptId )throws IOException
    {
        int deptIdstring = deptId;
        int count = departmentServices.delete(deptId);
        return count;
    }
    @CrossOrigin(origins = " http://localhost:4200")
    @RequestMapping(path = "/insertbatchdepartment", method = RequestMethod.POST)
    @ResponseBody
    public String insertbatchdepartment(@RequestBody DepartmentModel[] departmentModels)
    {
        int[] updated_row= new int[0];
        Gson gson = new Gson();
        updated_row = departmentServices.insertbatchdepartment(departmentModels);
        // departmentServices.insertbatchdepartment(DepartmentModel);
        return gson.toJson(updated_row);
    }

    @CrossOrigin(origins = " http://localhost:4200")
    @DeleteMapping("/DeleteMultiplDepartment")
    public void deletestudent(@RequestBody int[] deptId) throws SQLException {
        departmentServices.DeleteMultipaldepartment(deptId);



    }

}
