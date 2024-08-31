package com.example.demo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Employee;
import com.example.demo.dto.InquiryChannel;
import com.example.demo.dto.ManagerLim;
import com.example.demo.dto.TimeSheet;
import com.example.demo.dto.WeeklyFeedBackReport;
import com.example.demo.response.Response;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/user")
public class EmployeeController {

	@Autowired
	private EmployeeService empService;

	@GetMapping("/home")
	public ResponseEntity<Response<String>> homePage(Principal principal) {
		Response<String> response = Response.<String>builder().status("success").message("welcome user/employee")
				.data(principal.getName()).build();
		return new ResponseEntity<Response<String>>(response, HttpStatus.ACCEPTED);
	}

	@GetMapping("/profile")
	public ResponseEntity<Response<Employee>> getProfile(@RequestParam("empId") int empId) {

		return empService.getProfile(empId);
	}

	@GetMapping("/managerDetails")
	public ResponseEntity<Response<ManagerLim>> getManagerDetails() {
		return empService.getManagerDetails();
	}

	@PostMapping("/raiseRequest")
	public ResponseEntity<Response<InquiryChannel>> raiseInquiryRequest(@RequestBody InquiryChannel inquiryChannel) {

		return empService.raiseInquiryRequest(inquiryChannel);

	}
	
	@GetMapping("/getRequestStatus")
	public ResponseEntity<Response<List<InquiryChannel>>> getRequestStatus(){
		return empService.getRequestStatus();
	}
	
	@PostMapping("/sendFeedBack")
	public ResponseEntity<Response<WeeklyFeedBackReport>> sendFeedback(@RequestBody WeeklyFeedBackReport weeklyFeedBackReport){
		return empService.sendFeedBack(weeklyFeedBackReport);
	}
	
	@GetMapping("/viewFeedbackStatus")
	public ResponseEntity<Response<List<WeeklyFeedBackReport>>> viewFeedbackStatus(){
		return empService.viewFeedbackStatus();
	}
	
	@PostMapping("/fillTimeSheet")
	public ResponseEntity<Response<TimeSheet>> fillTimeSheet(@RequestBody TimeSheet timeSheet){
		return empService.fillTimeSheet(timeSheet);
	}
	
	@PutMapping("/updateTimeSheet")
	public ResponseEntity<Response<TimeSheet>> updateTimeSheet(
			@RequestBody TimeSheet timeSheet){
		return empService.fillTimeSheet(timeSheet);
	}

}
