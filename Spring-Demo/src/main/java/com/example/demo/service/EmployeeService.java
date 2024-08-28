package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Employee;
import com.example.demo.dto.InquiryChannel;
import com.example.demo.dto.ManagerLim;
import com.example.demo.response.Response;

@Service
public interface EmployeeService {
	
	public ResponseEntity<Response<Employee>> saveEmployee(Employee employee);
	
	public ResponseEntity<Response<List<Employee>>> getAllemployee(int pageNumber, int size);

	public ResponseEntity<Response<Employee>> deleteemployee(int empId);
	
	public ResponseEntity<Response<Employee>> getProfile(int empId);
	
	public ResponseEntity<Response<ManagerLim>> getManagerDetails();
	
	public ResponseEntity<Response<InquiryChannel>> raiseInquiryRequest(InquiryChannel inquiryChannel);
}
