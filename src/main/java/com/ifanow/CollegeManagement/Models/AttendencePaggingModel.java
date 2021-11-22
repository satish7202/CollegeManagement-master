package com.ifanow.CollegeManagement.Models;

import com.ifanow.CollegeManagement.Services.AttendenceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class AttendencePaggingModel {
	int length,pageIndex,pageSize,previousPageIndex,totalNoPage;

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPreviousPageIndex() {
		return previousPageIndex;
	}

	public void setPreviousPageIndex(int previousPageIndex) {
		this.previousPageIndex = previousPageIndex;
	}

	public int getTotalNoPage() {
		return totalNoPage;
	}

	public void setTotalNoPage(int totalNoPage) {
		this.totalNoPage = totalNoPage;
	}

	public AttendencePaggingModel(int length, int pageIndex, int pageSize, int previousPageIndex, int totalNoPage) {
		this.length = length;
		this.totalNoPage=totalNoPage;
		for (int i=0;i<=pageSize;i++) {
			if (pageIndex == 0) {
				this.pageIndex = pageIndex;
				break;
			}
			else if (pageIndex == i)
			{
				this.pageIndex=pageIndex*pageSize;
				break;
			}

		}
		this.pageSize = pageSize;
		this.previousPageIndex = previousPageIndex;
	}
}
