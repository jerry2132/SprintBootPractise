package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Department;
import com.example.demo.dto.Employee;
import com.example.demo.dto.Manager;
import com.example.demo.dto.Project;
import com.example.demo.response.Response;
import com.example.demo.service.CeoService;

@RestController
@RequestMapping("/ceo")
public class CeoController {
	
//	@Autowired
//	private ProjectService projectService;
//	
	@Autowired
	private CeoService ceoService;

	@PostMapping("/saveProject")
	public ResponseEntity<Response<List<Project>>> addProject(@RequestBody List<Project> project){
		
		return ceoService.addProject(project);
		
	}
	
	@PutMapping("/assignProjectToManager")
	public ResponseEntity<Response<Manager>> assignProjectToManager(@RequestParam("projectId") int projectId,
			@RequestParam("managerId") int managerId){
		return ceoService.assignManagerProject(projectId, managerId);
	}
	
	@PutMapping("/associateDepartmentAndManager")
	public ResponseEntity<Response<Department>> associateDepartmentAManager(@RequestParam("deptId") int deptId,
			@RequestParam("managerId") int managerId){
		
		return ceoService.associateDepartmentWithManager(deptId, managerId);
	}
	
	@GetMapping("/freeManagers")
	public ResponseEntity<Response<List<Manager>>> getAllFreeManager(){
		return ceoService.allFreeManagers();
	}
	
	@PutMapping("/assignEmployeeDepartment")
	public ResponseEntity<Response<Department>> assignEmployeeToDepartment(@RequestParam("deptId") int deptId,
			@RequestBody List<Integer> empId){
		return ceoService.assignEmployeeDepartment(deptId, empId);
	}
	
}
