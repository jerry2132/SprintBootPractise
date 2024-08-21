package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Ceo;
import com.example.demo.dto.Department;
import com.example.demo.dto.Manager;
import com.example.demo.dto.Project;
import com.example.demo.response.Response;

@Service
public interface CeoService {

	public ResponseEntity<Response<Ceo>> addCeo(Ceo ceo);
	
	public ResponseEntity<Response<List<Project>>> addProject(List<Project> project);

	public ResponseEntity<Response<Manager>> assignManagerProject(int projectId,int managerId);
	
	public ResponseEntity<Response<Department>> associateDepartmentWithManager(int deptId, int managerId);
	
	public ResponseEntity<Response<List<Manager>>> allFreeManagers();
	
	public ResponseEntity<Response<Department>> assignEmployeeDepartment(int deptId,List<Integer> empId);
	
	public ResponseEntity<Response<Map<Integer, Integer>>> getDepartmentIdAndEmployee();
}
