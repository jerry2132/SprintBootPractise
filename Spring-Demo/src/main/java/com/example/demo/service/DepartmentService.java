package com.example.demo.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Department;
import com.example.demo.response.Response;

@Service
public interface DepartmentService {
	
	public ResponseEntity<Response<Department>> saveDepartment(Department dept);

}
