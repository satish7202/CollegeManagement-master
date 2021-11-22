package com.ifanow.CollegeManagement.Services;

import com.google.gson.Gson;
import com.ifanow.CollegeManagement.Connection.DbConnection;
import com.ifanow.CollegeManagement.Models.AttendenceSingleStudent;
import com.ifanow.CollegeManagement.Models.studentModel;
import com.ifanow.CollegeManagement.Query.Queries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class studentServices {
    @Autowired
    DbConnection dbConnection;
    @Autowired
    Queries queries;
    @Autowired
            private Gson gson;

    studentModel studentModel = new studentModel();

    Statement stmt;

    PreparedStatement pstmt;

    Connection connection;



    public List SelectStudent(){

        int count=0;

        studentModel[] studentmodel=new studentModel[100];
        List<studentModel> studentModelList=new ArrayList<>();
        try{
            connection=dbConnection.getconnect();
            stmt=connection.createStatement();

            ResultSet rs= stmt.executeQuery(queries.SelectStudent);

            while (rs.next()){
                System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)
                +" "+rs.getString(4)+" "+rs.getString(5));
                studentmodel[count] = new studentModel(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5)  );
                studentModelList.add(studentmodel[count]);
                count++;
            }
            connection.close();
        }catch (Exception e)
        {
            System.out.println(e);
        }
        return studentModelList;
    }


    public int InsertStudent(int studentId, String studentName, String departmentName, String studentMobileNo, String studentAddmissionDate){
        int counterinsert=0;
        try{
            connection=dbConnection.getconnect();

            pstmt=connection.prepareStatement(queries.InsertStudent);
            studentModel.setStudentId(studentId);
            studentModel.setStudentName(studentName);
            studentModel.setDepartmentName(departmentName);
            studentModel.setStudentMobileNo(studentMobileNo);
            studentModel.setStudentAddmissionDate(studentAddmissionDate);

            pstmt.setInt(1,studentModel.getStudentId());
            pstmt.setString(2,studentModel.getStudentName());
            pstmt.setString(3,studentModel.getDepartmentName());
            pstmt.setString(4,studentModel.getStudentMobileNo());
            pstmt.setString(5,studentModel.getStudentAddmissionDate());


            counterinsert=pstmt.executeUpdate();
            System.out.println("Record inserted sucessfully..");
            connection.close();
            System.out.println("Connection closed");
        }catch (Exception e){
            System.out.println(e);
        }
        return counterinsert;

    }

    public int UpdateStudent(int studentId,String studentName,String departmentName,String studentMobileNo,String studentAddmissionDate){

        int counterupdate=0;
        try{
            connection=dbConnection.getconnect();
            stmt=connection.createStatement();
            pstmt=connection.prepareStatement(queries.UpdateStudent);
            pstmt.setString(1,studentName);
            pstmt.setString(2,departmentName);
            pstmt.setString(3,studentMobileNo);
            pstmt.setString(4,studentAddmissionDate);
            pstmt.setInt(5,studentId);
            counterupdate=pstmt.executeUpdate();
            System.out.println("updated sucessfully");
            connection.close();
            System.out.println("Connection closed");
        }catch(Exception e){
            System.out.println(e);
        }

        return counterupdate;
    }
    public int DeleteStudent(int studentId){

        int deletecounter=0;
        try{

            connection=dbConnection.getconnect();
            pstmt=connection.prepareStatement(queries.DeleteStudent);
            pstmt.setInt(1,studentId);

            deletecounter=pstmt.executeUpdate();
            connection.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return deletecounter;
    }
    public int counttotalDetails(){
        int counter=0;
        try{
            connection=dbConnection.getconnect();
            stmt=connection.createStatement();
            ResultSet rs=stmt.executeQuery(queries.conutStudent);
            while (rs.next()){
               counter=rs.getInt("total");
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return counter;
    }
    public AttendenceSingleStudent SelectSingleStudent(int sid){

        int count=0;

        AttendenceSingleStudent attendenceSingleStudent=null;
        try{
            PreparedStatement ps;
            connection=dbConnection.getconnect();

            stmt=connection.createStatement();
            ps=connection.prepareStatement(queries.selectSingleStudent);
            ps.setInt(1,sid);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
//                System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)
//                        +" "+rs.getString(4)+" "+rs.getString(5));
                attendenceSingleStudent = new AttendenceSingleStudent(rs.getString(1),rs.getString(2));
                count++;
            }
            connection.close();
        }catch (Exception e)
        {
            System.out.println(e);
        }
        return attendenceSingleStudent;
    }
    //BatchInserting
    public int[] batchInsertStudent( studentModel[] models){
        int[] insert=new int[0];
        try{
            connection=dbConnection.getconnect();


            pstmt = connection.prepareStatement(queries.InsertStudent);

            for(int i=0;i<models.length;i++) {

                pstmt.setInt(1, models[i].getStudentId());
                pstmt.setString(2, models[i].getStudentName());
                pstmt.setString(3, models[i].getDepartmentName());
                pstmt.setString(4, models[i].getStudentMobileNo());
                pstmt.setString(5, models[i].getStudentAddmissionDate());

                pstmt.addBatch();
            }
            insert= pstmt.executeBatch();

            int count=0;
            for(int i=0;i<insert.length;i++){
                count++;
            }
            System.out.println("Record inserted sucessfully..");

            connection.close();
            System.out.println("Connection closed");
        }catch (Exception e){
            System.out.println(e);
        }
        return insert;
    }
    public int[] DeleteMultipalRecords(int[] studentId) throws SQLException {

        int delete[]=new int[0];
        try{
            connection=dbConnection.getconnect();
            pstmt=connection.prepareStatement(queries.DeleteStudent);
            for(int i=0;i<studentId.length;i++){
                pstmt.setInt(1,studentId[i]);
                pstmt.addBatch();
            }
            delete=pstmt.executeBatch();
            System.out.println("data Deleted successfully...");
            connection.close();
            System.out.println("Connection closed");
        }catch(Exception e){
            System.out.println(e);
        }
        return delete;
    }


}
