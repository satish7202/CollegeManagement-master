package com.ifanow.CollegeManagement.Controller;

import com.google.gson.Gson;
import com.ifanow.CollegeManagement.Models.AttendenceInsertModel;
import com.ifanow.CollegeManagement.Models.AttendenceUpdateModel;
import com.ifanow.CollegeManagement.Services.AttendenceServices;
import com.ifanow.CollegeManagement.Services.ExternalApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
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
        int attendencePercentage=0;
        attendencePercentage = (int)attendence.attendencePercentage(attendenceupdatemodel.getStudentId());
        updatedRow=attendence.attendenceUpdate(attendenceupdatemodel,attendencePercentage);
        return gson.toJson("Successfully..Updated Rows="+String.valueOf(updatedRow));


    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "/attendence/insertBatch")
    public String setAttendenceBatch(@RequestBody AttendenceInsertModel[] attendenceBatch)
    {
        int insertedRows = attendence.attdendenceInsertBatch(attendenceBatch);
        return gson.toJson("InsertedRows="+insertedRows);
    }
    @CrossOrigin( origins="http://localhost:4200")
    @PutMapping(path = "/attendence/deleteBatch")
   public String attendenceDelete(@RequestBody int[] attendenceDeleteModel )
  {
      System.out.println(attendenceDeleteModel);
        int deletedRows = attendence.deleteBatch(attendenceDeleteModel);
        return gson.toJson("DeletedRows="+deletedRows);

  }
    @CrossOrigin( origins="http://localhost:4200")
    @PostMapping(path = "/attendence/exteranalApi")
    public String attendenceDelete() throws IOException {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://localhost:8080")
                .addConverterFactory(ScalarsConverterFactory.create()).build();
        ExternalApi externalApi = retrofit.create(ExternalApi.class);
        final Call<String> call = externalApi.listRepos("Hello");
       String  repos = String.valueOf(call.execute().body());
        return repos;
    }
    @CrossOrigin( origins="http://localhost:4200")
    @GetMapping(path = "/attendence/count")
    public int attendenceCount()
    {
        int length=attendence.count();
        return length;

    }



}
