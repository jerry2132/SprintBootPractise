package com.example.SpringDemoProject.service;


import java.io.IOException;
import java.util.List;

import javax.naming.NameNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.SpringDemoProject.apiResponse.ApiResponse;
import com.example.SpringDemoProject.dto.College;


public interface CollegeInterface {
	
public ResponseEntity<ApiResponse<College>> updateCollege(Integer id, String name);
	
public ResponseEntity<ApiResponse<College>> saveCollege(College college, MultipartFile file);

public ResponseEntity<ApiResponse<List<College>>> getCollegeDetails();

public ResponseEntity<ApiResponse<List<College>>> saveMultipleCollege(List<College> college, MultipartFile[] file);

public ResponseEntity<ApiResponse<List<College>>> saveMultipleCollegeWithValidation(List<College> college);

public ResponseEntity<ApiResponse<College>> findCollegeById(int collegeId);

public ResponseEntity<ApiResponse<List<College>>> findAllByName(String name);

public ResponseEntity<ApiResponse<List<College>>> sortByField(String field);

public ResponseEntity<ApiResponse<List<College>>> sortAndPaginationGetAll(String field , Integer offset,Integer pageSize);

public ResponseEntity<ApiResponse<College>> deleteCollege(College college);
}
