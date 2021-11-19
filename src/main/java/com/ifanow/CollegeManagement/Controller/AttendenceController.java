package com.ifanow.CollegeManagement.Controller;

import com.google.gson.Gson;
import com.ifanow.CollegeManagement.Models.AttendenceInsertModel;
import com.ifanow.CollegeManagement.Models.AttendenceModel;
import com.ifanow.CollegeManagement.Models.AttendenceUpdateModel;
import com.ifanow.CollegeManagement.Services.AttendenceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class AttendenceController {
    Gson gson=new Gson();
    @Autowired
    AttendenceServices attendence;
    @PostMapping(value = "/attendence/insert")
    @CrossOrigin(origins = "http://localhost:4200")
    @ResponseBody
    public String AttendenceInsert(@RequestBody AttendenceInsertModel attendModel) throws IOException {
        System.out.println(attendModel);
        int attendencePercentage=(int)attendence.attendencePercentage(attendModel.getStudentId());
        int count =attendence.insertAttendence(attendModel,attendencePercentage);
        return "Row Inserted Succesfully="+count;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "/attendence")
    public String attendenceHome() throws IOException {
        Gson gson=new Gson();
        String listModel = gson.toJson(attendence.selectAttendence());
        String encodedList=attendence.Encode(listModel);
        String decodedlist=attendence.Decode(encodedList);
        return decodedlist;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping(path = "/attendence/delete")
    public String attendenceDelete(@RequestParam("srNo") int srNo) throws IOException {
        String  deletedRow;
        deletedRow= gson.toJson("Successfully Deleted..No of Rows="+attendence.Delete(srNo));
        return deletedRow;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(path = "/attendence/update")
    public String attendenceUpdate(@RequestBody AttendenceUpdateModel attendenceupdatemodel) throws IOException {
        int updatedRow=0;
        int attendencePercentage= (int)attendence.attendencePercentage(attendenceupdatemodel.getStudentId());
        updatedRow=attendence.attendenceUpdate(attendenceupdatemodel,attendencePercentage);
        return gson.toJson("Successfully..Updated Rows="+String.valueOf(updatedRow));


    }
    @CrossOrigin("localhost:4200")
    @PostMapping(path = "/attendence/insertBatch")
    public String setAttendenceBatch(@RequestBody AttendenceInsertModel[] attendenceBatch)
    {
        int insertedRows = attendence.attdendenceInsertBatch(attendenceBatch);
        return gson.toJson("InsertedRows="+insertedRows);
    }
}
