package com.ifanow.CollegeManagement.Query;

import org.springframework.stereotype.Component;

@Component
public class Queries {
    //----------------------------Student Table----------------------------------------------
    public static String SelectStudent="select * from student";
    public static String InsertStudent = "INSERT INTO `student` (studentId,studentName,departmentName,studentMobileNo,studentAdmissionDate)VALUES (?,?,?,?,?)";
    public static String UpdateStudent ="UPDATE `student` SET studentName = ? WHERE studentId = ?";
    public static String DeleteStudent=  "delete from student where studentId=(?);";
    public static String conutStudent=" select count(*) as total from student";

    //----------------------------Library Table----------------------------------------------
    public final String getAllLibraryData = "Select * from library";
    public final String storeLibraryData = "insert into library(studentId,studentName,bookName,issueDate,returnDate,numberOfBook,librarian) values(?,?,?,?,?,?,?)";
    public final String updateLibraryData = "Update library set studentName=?, bookName = ? ,issueDate=?, returnDate=?, numberOfBook =?,librarian=? where srNo =?";
    public  final String deleteLibraryData  = "DELETE FROM library  WHERE srNo= ? ";
    public final String countLibraryData = "SELECT COUNT(*) AS totalLibraryData FROM library";

    ////----------------------------Department Table----------------------------------------------
    public static final String selectQuery="select * from department";
    public static final String updateQueryDepartment="update department set departmentName =?,departmentHead=?,teachersAll=? where departmentId=?";
    public static final String insertQueryDepartment= "INSERT INTO department(departmentName,departmentHead,teachersAll) VALUES ((?),(?),(?))";
    public static final String deleteQueryDepartment="delete from department where departmentId=?";
    public static final String totalcountdepartment="select count(*) as total from department";

    ///-----------------------------Attendence Table------------------------------------------------
    public static String insertAttendence="INSERT INTO attendence(`studentId`,`studentName`, `department`,`loginTime`,`logoutTime`,`attendancePercentage`)VALUES((?),(?),(?),(?),(?),(?));";
    public static String selectAttendence="SELECT * FROM attendence;";
    public static String deleteAttendence="DELETE FROM `attendence`WHERE srNo=(?);";
    public static String updateAttendence="UPDATE `attendence`SET`studentName` = (?) ,`department` = (?),`loginTime` = (?),`logoutTime` = (?),`attendancePercentage`=(?) WHERE `srNo`= (?);";
    public static String percentageAttendenceQuery="SELECT studentName FROM `attendence`WHERE studentId= ?;";

}
