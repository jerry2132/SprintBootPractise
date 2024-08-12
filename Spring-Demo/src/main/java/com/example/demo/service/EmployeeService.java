package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Employee;
import com.example.demo.response.Response;

@Service
public interface EmployeeService {
	
	public ResponseEntity<Response<Employee>> saveEmployee(Employee employee);
	
	public ResponseEntity<Response<List<Employee>>> getAllemployee(int pageNumber, int size);

	public ResponseEntity<Response<Employee>> deleteemployee(int empId);
}
