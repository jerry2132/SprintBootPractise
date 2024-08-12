package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Ceo;
import com.example.demo.dto.Department;
import com.example.demo.dto.Employee;
import com.example.demo.dto.Manager;
import com.example.demo.response.Response;
import com.example.demo.service.CeoService;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.ManagerService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private ManagerService managerService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private DepartmentService deptService;
	
	@Autowired
	private CeoService ceoService;

	@GetMapping("/saveManager")
	public ResponseEntity<Response<Manager>> saveManager(@RequestBody Manager manager) {

		return managerService.saveManager(manager);

	}

	@PostMapping("/saveEmployee")
	public ResponseEntity<Response<Employee>> saveEmployee(@RequestBody Employee employee) {

		return employeeService.saveEmployee(employee);
	}

	@PostMapping("/saveDept")
	public ResponseEntity<Response<Department>> saveDepartment(@RequestBody Department dept) {

		return deptService.saveDepartment(dept);
	}
	
	@GetMapping("/getAllManagers")
	public ResponseEntity<Response<List<Manager>>> getAllManager(@RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size){
		
		return managerService.getAllManagers(page,size);
	}

	@GetMapping("/getAllEmployee")
	public ResponseEntity<Response<List<Employee>>> getAllEmployee(@RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size){
		
		return employeeService.getAllemployee(page, size);
		
	}
	
	@GetMapping("/getAllDepartments")
	public ResponseEntity<Response<List<Department>>> getAllDepartment(@RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size){
		
		return deptService.getAllDepartment(page, size);
	}
	
	@GetMapping("/deletemployee/{employeeId}")
	public ResponseEntity<Response<Employee>> deleteEmployee(@PathVariable("employeeId") int empId){
		
		return employeeService.deleteemployee(empId);
	}
	
	@GetMapping("/addCeo")
	public ResponseEntity<Response<Ceo>> addCeo(@RequestBody Ceo ceo){
		return ceoService.addCeo(ceo);
	}
}
