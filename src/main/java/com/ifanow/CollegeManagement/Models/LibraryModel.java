package com.ifanow.CollegeManagement.Models;

import org.springframework.stereotype.Component;

@Component
public class LibraryModel {
    public int srNo, studentId,numberOfBook;
    public String studentName;
    public String bookName;
    public String issueDate;
    public String returnDate;
    public String librarian;
    public String Status;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }



    public int getSrNo() {
        return srNo;
    }

    public void setSrNo(int srNo) {
        this.srNo = srNo;
    }

    public LibraryModel() {

    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getNumberOfBook() {
        return numberOfBook;
    }

    public void setNumberOfBook(int numberOfBook) {
        this.numberOfBook = numberOfBook;
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

    public String getLibrarian() {
        return librarian;
    }

    public void setLibrarian(String librarian) {
        this.librarian = librarian;
    }

    public LibraryModel(int srNo,int studentId, int numberOfBook, String studentName, String bookName, String issueDate, String returnDate, String librarian,String Status) {
        this.srNo = srNo;
        this.studentId = studentId;
        this.numberOfBook = numberOfBook;
        this.studentName = studentName;
        this.bookName = bookName;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
        this.librarian = librarian;
        this.Status=Status;
    }

    @Override
    public String toString() {
        return "LibraryModel{" +
                "srNo=" + srNo +
                ", studentId=" + studentId +
                ", numberOfBook=" + numberOfBook +
                ", studentName='" + studentName + '\'' +
                ", bookName='" + bookName + '\'' +
                ", issueDate='" + issueDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", librarian='" + librarian + '\'' +
                ", Status='" + Status + '\'' +
                '}';
    }
}


