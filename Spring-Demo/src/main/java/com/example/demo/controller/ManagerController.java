package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Employee;
import com.example.demo.dto.Manager;
import com.example.demo.response.Response;
import com.example.demo.service.ManagerService;

@RestController
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	private ManagerService managerService;

	@GetMapping("/home")
	public String home() {
		return "manager home";
	}

//	@PreAuthorize("principal.username == #username")
	@GetMapping("/details/{mangerId}")
	public ResponseEntity<Response<Manager>> getDetails(@PathVariable("mangerId") int managerId) {

		return managerService.getDetails(managerId);
	}

	@PutMapping("/assignEmployeeManager")//also assign employee project if manager.project != null
	public ResponseEntity<Response<Manager>> assignEmployeeManager(
			@RequestParam("managerId") int managerId,@RequestBody List<Integer> empList){
		
		return managerService.assignEmployeeManager(managerId, empList);
		
	}
	
	
	@PutMapping("/assignEmployeeProject")//
	public ResponseEntity<Response<Manager>> assignEmployeeProject(
			@RequestParam("managerId") int managerId){
		return managerService.assignEmployeeProject(managerId);
	}
	
	
	@PutMapping("/removeEmployeeFromProject")
	public ResponseEntity<Response<Employee>> employeeListOfManager(@RequestParam("employeeId") int employeeId){
		return managerService.removeEmployeeFromProject(employeeId);
	}
	
}
