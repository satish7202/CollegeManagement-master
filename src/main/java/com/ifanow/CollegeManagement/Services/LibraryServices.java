package com.ifanow.CollegeManagement.Services;

import com.ifanow.CollegeManagement.Connection.DbConnection;
import com.ifanow.CollegeManagement.Models.LibraryModel;
import com.ifanow.CollegeManagement.Query.Queries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
@Component
public class LibraryServices {
    @Autowired
    DbConnection dbConnection = new DbConnection();
    Connection connection;
    @Autowired
    Queries queries;
    @Autowired
    LibraryModel libraryModel = new LibraryModel();



    public int saveLibraryDetails(int studentId, String studentName, String bookName, String issueDate,String returnDate, String librarian){
        int show = 0;
        try {

            connection = dbConnection.getconnect();

            PreparedStatement stmt = connection.prepareStatement(queries.storeLibraryData);

            libraryModel.setStudentId(studentId);
            libraryModel.setStudentName(studentName);
            libraryModel.setBookName(bookName);
            libraryModel.setIssueDate(issueDate);
            libraryModel.setReturnDate(returnDate);
            libraryModel.setLibrarian(librarian);

            stmt.setInt(1,libraryModel.getStudentId());
            stmt.setString(2,libraryModel.getStudentName() );
            stmt.setString(3,libraryModel.getBookName());
            stmt.setString(4,libraryModel.getIssueDate());
            stmt.setString(5, libraryModel.getReturnDate());
            stmt.setString(6,libraryModel.getLibrarian());

            show = stmt.executeUpdate();
            System.out.println("Records inserted successfully");

            connection.close();
            System.out.println("Connection closed");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return show;
    }
    public List showAllLibraryDetail() {
        int length=0;
        LibraryModel[] libraryModels = new LibraryModel[100];
        List<LibraryModel> libraryModelList = new ArrayList<>();
        try {
            connection = dbConnection.getconnect();

            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery(queries.getAllLibraryData);

            while (result.next()) {

                libraryModels[length] = new LibraryModel(result.getInt("srNo"),
                        result.getInt("studentId"),
                        result.getString("studentName"),
                        result.getString("bookName"),
                        result.getString("issueDate"),
                        result.getString("returnDate"),
                        result.getString("studentReturnDate"),
                        result.getInt("Penalty"),
                        result.getString("Status"),
                        result.getString("librarian"),
                        result.getString("UpdateTimeStamp"));
                libraryModelList.add(libraryModels[length]);

            }

            connection.close();
            System.out.println("Connection closed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libraryModelList;
    }
    public int deleteLibraryDetail(int srNo)
    {
        int deleted_row=0;
        try {
            connection= dbConnection.getconnect();
            Statement stmt = connection.createStatement();
            PreparedStatement ps = connection.prepareStatement(queries.deleteLibraryData);
            ps.setInt(1,srNo);
            deleted_row = ps.executeUpdate();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return deleted_row;
    }


    public int updateLibraryDetail(int srNo, String bookName, String issueDate, String returnDate,String studentReturnDate, String librarian, String Status){
        int updated_row=0;
        try {
            connection = dbConnection.getconnect();
            Statement stmt = connection.createStatement();
            PreparedStatement ps = connection.prepareStatement(queries.updateLibraryData);

            ps.setString(1,bookName);
            ps.setString(2,issueDate);
            ps.setString(3,returnDate);
            ps.setString(4,studentReturnDate);
            ps.setString(5,librarian);
            ps.setString(6,Status);
            ps.setInt(7,srNo);

            updated_row = ps.executeUpdate();
            System.out.println("Records Updated Successfully");
            showAllLibraryDetail();
            connection.close();
            System.out.println("Connection closed");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return updated_row;
    }
    public int LibraryDetails(){
        int count = 0;
        try{
            connection = dbConnection.getconnect();

            PreparedStatement ps=connection.prepareStatement(queries.countLibraryData);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt("totalLibraryData");
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return count;
    }
    public int[] saveMultiLibraryDetails(LibraryModel[] libraryInsertDetail){
        int show[] = new int[0];
        try {

            connection = dbConnection.getconnect();
            PreparedStatement stmt = connection.prepareStatement(queries.storeLibraryData);
            for(int i=0;i<libraryInsertDetail.length;i++) {
                stmt.setInt(1, libraryInsertDetail[i].getStudentId());
                stmt.setString(2, libraryInsertDetail[i].getStudentName());
                stmt.setString(3, libraryInsertDetail[i].getBookName());
                stmt.setString(4, libraryInsertDetail[i].getIssueDate());
                //stmt.setString(5, libraryInsertDetail[i].getReturnDate());
                //stmt.setInt(5, libraryInsertDetail[i].getNumberOfBook());
                stmt.setString(5, libraryInsertDetail[i].getLibrarian());
                stmt.addBatch();
            }
            show = stmt.executeBatch();
            System.out.println("Records inserted successfully");

            connection.close();
            System.out.println("Connection closed");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return show;
    }
//    public int[] updateMultipleLibraryDetail(LibraryModel[] libraryUpdateDetail){
//        int updated_row[] = new int[0];
//        try {
//            connection = dbConnection.getconnect();
//            PreparedStatement ps = connection.prepareStatement(queries.updateLibraryData);
//            for(int i=0;i<libraryUpdateDetail.length;i++){
//                ps.setString(1,libraryUpdateDetail[i].studentName);
//                ps.setString(2,libraryUpdateDetail[i].bookName);
//                ps.setString(3,libraryUpdateDetail[i].issueDate);
//                ps.setString(4,libraryUpdateDetail[i].returnDate);
//                ps.setInt(5,libraryUpdateDetail[i].numberOfBook);
//                ps.setString(6,libraryUpdateDetail[i].librarian);
//                ps.setString(7,libraryUpdateDetail[i].Status);
//                ps.setInt(8,libraryUpdateDetail[i].srNo);
//                ps.addBatch();
//            }
//            updated_row = ps.executeBatch();
//            System.out.println("Records inserted successfully");
//
//            connection.close();
//            System.out.println("Connection closed");
//        }
//        catch(SQLException e){
//            e.printStackTrace();
//        }
//        return updated_row;
//    }
    public String encodefunc(String e){
        String encode = Base64.getEncoder().encodeToString(e.getBytes());
        return encode;
    }
    public String decodefunc(String d) {
        byte[] decodedBytes = Base64.getDecoder().decode(d);
        String decodedString = new String(decodedBytes);
        System.out.println(decodedString);
        return decodedString;
    }

    public int[] deleteLibraryDetailBatch(int[] srNo)
    {
        LibraryModel libraryModel;
        int[] rowsAffected= new int[0];
        try {
            connection = dbConnection.getconnect();
            Statement stmt = connection.createStatement();
            PreparedStatement ps = connection.prepareStatement(queries.deleteLibraryData);
            for(int i=0;i<srNo.length;i++) {
                ps.setInt(1,srNo[i]);
                ps.addBatch();
            }
            rowsAffected = ps.executeBatch();
            System.out.println("Successfully record deleted");
            showAllLibraryDetail();
            connection.close();
            System.out.println("Connection closed");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return rowsAffected;
    }
}



