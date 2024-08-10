package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Department;
import com.example.demo.dto.Employee;
import com.example.demo.dto.Manager;
import com.example.demo.response.Response;
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

}
