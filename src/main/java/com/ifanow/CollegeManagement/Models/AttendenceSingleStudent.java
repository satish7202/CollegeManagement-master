package com.ifanow.CollegeManagement.Models;

public class AttendenceSingleStudent {
	String studentName;
	String departmentName;

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public AttendenceSingleStudent(String studentName, String departmentName) {
		this.studentName = studentName;
		this.departmentName = departmentName;
	}
}
