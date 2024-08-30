package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Employee;
import com.example.demo.dto.InquiryChannel;
import com.example.demo.dto.Manager;
import com.example.demo.dto.WeeklyFeedBackReport;
import com.example.demo.response.Response;

@Service
public interface ManagerService {

	public ResponseEntity<Response<Manager>> saveManager(Manager manager);
	
	public ResponseEntity<Response<Manager>> getDetails(int managerId);
	
	public ResponseEntity<Response<List<Manager>>> getAllManagers(int pageNumber,int pageSize);
	
	public ResponseEntity<Response<Manager>> assignManagerAProject(int projectId,int managerId);
	
	public ResponseEntity<Response<List<Manager>>> getAllFreeManagers();//those doesnt have any projects

	public ResponseEntity<Response<Manager>> assignEmployeeManager(int managerId,List<Integer> empList);
	
	public ResponseEntity<Response<Manager>> assignEmployeeProject(int managerId);
	
	public ResponseEntity<Response<Employee>> removeEmployeeFromProject(int employeeId);
	
	public ResponseEntity<Response<Manager>> changeProjectStatus(String status);
	
	public ResponseEntity<Response<List<Employee>>> getAllEmployeeWithGivenRating(int rating);
	
	public ResponseEntity<Response<List<InquiryChannel>>> getInquiryRequest();
	
	public ResponseEntity<Response<InquiryChannel>> updateInquiryStatus(int channelId , String status);
	
	public ResponseEntity<Response<WeeklyFeedBackReport>> respondFeedbackReport(int feedbackId,
			WeeklyFeedBackReport weeklyFeedBackReport);

	public ResponseEntity<Response<List<WeeklyFeedBackReport>>> getWeeklyFeedBackReport();

	
}
