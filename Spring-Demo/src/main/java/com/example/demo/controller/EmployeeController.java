package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Employee;
import com.example.demo.dto.ManagerLim;
import com.example.demo.response.Response;
import com.example.demo.service.EmployeeService;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
public class EmployeeController {
	
	@Autowired
	private EmployeeService empService;
	
	@GetMapping("/profile")
	public ResponseEntity<Response<Employee>> getProfile(@RequestParam("empId") int empId){
		
		return empService.getProfile(empId);
	}
	
	
	@GetMapping("/managerDetails")
	public ResponseEntity<Response<ManagerLim>> getManagerDetails(){
		log.info("inside controller");
		return empService.getManagerDetails();
	}

}
