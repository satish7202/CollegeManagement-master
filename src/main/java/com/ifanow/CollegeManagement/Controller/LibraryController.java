package com.ifanow.CollegeManagement.Controller;
import com.google.gson.Gson;
import com.ifanow.CollegeManagement.Models.LibraryModel;
import com.ifanow.CollegeManagement.Services.LibraryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/api")

public class LibraryController {
    @Autowired
    LibraryServices libraryServices;


    @CrossOrigin("http://localhost:4200")
    @PostMapping("/storeLibraryDetails")
    public String savLibraryDetails(@RequestBody String libraryInsertDetail) throws IOException {
        System.out.println(libraryInsertDetail);
//        byte[] decodedBytes = Base64.getDecoder().decode(libraryInsertDetail);
//        String decodedString = new String(decodedBytes);
//        System.out.println(decodedString);
        Gson gson = new Gson();
        LibraryModel libraryJsonModel = gson.fromJson(libraryInsertDetail, LibraryModel.class);
        System.out.println("fromJson"+libraryJsonModel);
        int studentId = libraryJsonModel.getStudentId();
       // int numberOfBook = libraryJsonModel.getNumberOfBook();
        String studentName = libraryJsonModel.getStudentName();
        String bookName = libraryJsonModel.getBookName();
        String issueDate = libraryJsonModel.getIssueDate();
        String returnDate = libraryJsonModel.getReturnDate();
        String librarian = libraryJsonModel.getLibrarian();
        String count = gson.toJson("Data Successfully Inserted..No of Rows=" + libraryServices.saveLibraryDetails(studentId, studentName, bookName, issueDate, returnDate,librarian));
        System.out.println("toJson"+count);
        return count;
    }
    @CrossOrigin("http://localhost:4200")
    @GetMapping("/showLibraryDetails")
    public String showLibrayDetails()
    {
        Gson libraryModeljson=new Gson();
        String libraryjsonObj =libraryModeljson.toJson(libraryServices.showAllLibraryDetail());
        return libraryjsonObj;
    }
    @CrossOrigin("http://localhost:4200")
    @PutMapping("/updateLibraryDetails")
    public String updateLibraryDetails(@RequestBody String libraryUpdateDetail){

        int updateRow =0;
        System.out.println(libraryUpdateDetail);
      Gson gson = new Gson();
      LibraryModel libraryModel = gson.fromJson(libraryUpdateDetail, LibraryModel.class);
        int srNo = libraryModel.getSrNo();
        //int numberOfBook = libraryModel.getNumberOfBook();
        String bookName = libraryModel.getBookName();
        String issueDate = libraryModel.getIssueDate();
        String returnDate = libraryModel.getReturnDate();
        String studentReturnDate = libraryModel.getStudentReturnDate();
        String librarian = libraryModel.getLibrarian();
        String Status = libraryModel.getStatus();
        updateRow =libraryServices.updateLibraryDetail( srNo,  bookName,  issueDate,  returnDate , studentReturnDate ,  librarian ,Status);
        return gson.toJson("Successfully..Updated Rows="+String.valueOf(updateRow));
    }

    @CrossOrigin("http://localhost:4200")
    @DeleteMapping("/deleteLibraryDetails")
    public void deleteLibraryDetail(@RequestBody int srNo) throws IOException {
        libraryServices.deleteLibraryDetail(srNo);
    }

    @CrossOrigin("http://localhost:4200")
    @GetMapping("/totalLibraryDetails")
    public int totalLibrrayDetail(){
        Gson gson = new Gson();
        int count = 0;
        count = libraryServices.LibraryDetails();
        return count;
    }

    @CrossOrigin("http://localhost:4200")
    @PostMapping("insertMultipleLibraryDetail")
    public String insertMultipleLibraryDetail(@RequestBody LibraryModel[] libraryInsertDetail) {
        System.out.println(libraryInsertDetail);
//        byte[] decodedBytes = Base64.getDecoder().decode(libraryInsertDetail);
//        String decodedString = new String(decodedBytes);
//        System.out.println(decodedString);
        int[] updated_row= new int[0];
        Gson gson = new Gson();
        updated_row = libraryServices.saveMultiLibraryDetails(libraryInsertDetail);
        return "Inserted Successfully";
    }

//    @CrossOrigin("http://localhost:4200")
//    @PutMapping("updateMultipleLibraryDetail")
//    public void updateMultipleLibraryDetail(@RequestBody LibraryModel[] libraryUpdateDetail){
//        System.out.println(libraryUpdateDetail);
//        libraryServices.updateMultipleLibraryDetail(libraryUpdateDetail);
//    }


    @CrossOrigin("http://localhost:4200")
    @DeleteMapping("/deleteLibraryDetailsBatch")
    public void deleteLibraryDetail(@RequestBody int[] srNo) throws IOException {
        libraryServices.deleteLibraryDetailBatch(srNo);
    }

    @GetMapping("getStudentDetails")
    public List<Object> getStudentDetail(){
        String url = "http://localhost:8080/api/showStudentDetails";
        RestTemplate restTemplate = new RestTemplate();
        Object[] getStudentDetail = restTemplate.getForObject(url,Object[].class);
        return Arrays.asList(getStudentDetail);
    }

}