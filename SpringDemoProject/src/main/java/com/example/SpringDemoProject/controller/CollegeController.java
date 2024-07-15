package com.example.SpringDemoProject.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.SpringDemoProject.apiResponse.ApiResponse;
import com.example.SpringDemoProject.dto.College;
import com.example.SpringDemoProject.service.CollegeInterface;

@RestController
public class CollegeController {
	
	@Autowired
	private CollegeInterface collegeInterface;

	
	@GetMapping("/getDate")
	public String getDate() {
		
		return LocalDate.now()+"hjkad";
	}
	
	@PostMapping("/saveCollege")
	public ResponseEntity<ApiResponse<College>>  saveCollegeDetails(@ModelAttribute College college , @RequestParam("orginalImage") MultipartFile file) throws IOException {

		return collegeInterface.saveCollege(college, file);
		
	}
	
	
	@GetMapping("/getCollegeDetails")
	public ResponseEntity<ApiResponse<List<College>>> getCollegeDetails(){
	return collegeInterface.getCollegeDetails();
	}
	
	
	@PostMapping("/saveMultipleCollege")
	public ResponseEntity<ApiResponse<List<College>>> saveMultipleCollege(@RequestPart("colleges") List<College> colleges, 
			@RequestPart("orginalImage") MultipartFile[] file) {

		return collegeInterface.saveMultipleCollege(colleges,file);
		
	}
	
	@PostMapping("/saveMultipleCollegeWithValidation")
	public ResponseEntity<ApiResponse<List<College>>> saveMultipleCollegeWithValidation(@RequestBody List<College> college) {
		
		return collegeInterface.saveMultipleCollegeWithValidation(college);
	}
	
	
	@GetMapping("/getCollegeById/{collegeId}")
	public ResponseEntity<ApiResponse<College>> findCollegeById(@PathVariable int collegeId) {
		
		return collegeInterface.findCollegeById(collegeId);
		
	}
	
	@GetMapping("/getByName/{name}")
	public ResponseEntity<ApiResponse<List<College>>> getCollegeToUpdate(@PathVariable String name) {
		
		return collegeInterface.findAllByName(name);
	}
	
	@GetMapping("/getCollegeDetails/{field}")
	public ResponseEntity<ApiResponse<List<College>>> getSortedData(@PathVariable String field){
		return collegeInterface.sortByField(field);
	}
	
	@GetMapping("/getCollegeDetails/{offset}/{pageSize}/{field}")
	public ResponseEntity<ApiResponse<List<College>>> getAllSortedAndPaginatedData(@PathVariable String field,@PathVariable Integer offset,@PathVariable Integer pageSize){
		return collegeInterface.sortAndPaginationGetAll(field, offset, pageSize);
	}
//	
//	@GetMapping("/saveCollege/{id}")
//	public  ResponseEntity<ApiResponse<College>> updateCollege(@RequestBody College college ,@PathVariable Integer id){
//		
//		
//	}
//	
	

@PutMapping("/updateCollegeName/{id}/{name}")
public ResponseEntity<ApiResponse<College>> updateCollege(@PathVariable  Integer id, @PathVariable String name){
	
	return collegeInterface.updateCollege(id,name);
	
}
	
	
	
}
