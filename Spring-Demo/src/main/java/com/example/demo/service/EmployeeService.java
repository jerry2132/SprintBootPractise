package com.example.demo.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Employee;
import com.example.demo.response.Response;

@Service
public interface EmployeeService {
	
	public ResponseEntity<Response<Employee>> saveEmployee(Employee employee);

}
