package com.ifanow.CollegeManagement.Models;

import org.springframework.stereotype.Component;

@Component
public class LibraryModel {
    public int srNo, studentId;

    public String studentName;
    public String bookName;
    public String issueDate;
    public String returnDate;
    public String studentReturnDate;
    public int penalty;
    public String Status;
    public String librarian;
    public String UpdateTimeStamp;

    public LibraryModel() {

    }

    public int getSrNo() {
        return srNo;
    }

    public void setSrNo(int srNo) {
        this.srNo = srNo;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getStudentReturnDate() {
        return studentReturnDate;
    }

    public void setStudentReturnDate(String studentReturnDate) {
        this.studentReturnDate = studentReturnDate;
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getLibrarian() {
        return librarian;
    }

    public void setLibrarian(String librarian) {
        this.librarian = librarian;
    }

    public String getUpdateTimeStamp() {
        return UpdateTimeStamp;
    }

    public void setUpdateTimeStamp(String updateTimeStamp) {
        UpdateTimeStamp = updateTimeStamp;
    }

    public LibraryModel(int srNo, int studentId, String studentName, String bookName, String issueDate, String returnDate, String studentReturnDate, int penalty, String status, String librarian, String updateTimeStamp) {
        this.srNo = srNo;
        this.studentId = studentId;
        this.studentName = studentName;
        this.bookName = bookName;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
        this.studentReturnDate = studentReturnDate;
        this.penalty = penalty;
        this.Status = status;
        this.librarian = librarian;
        UpdateTimeStamp = updateTimeStamp;
    }

    @Override
    public String toString() {
        return "LibraryModel{" +
                "srNo=" + srNo +
                ", studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", bookName='" + bookName + '\'' +
                ", issueDate='" + issueDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", studentReturnDate='" + studentReturnDate + '\'' +
                ", penalty=" + penalty +
                ", Status='" + Status + '\'' +
                ", librarian='" + librarian + '\'' +
                ", UpdateTimeStamp='" + UpdateTimeStamp + '\'' +
                '}';
    }
}
















