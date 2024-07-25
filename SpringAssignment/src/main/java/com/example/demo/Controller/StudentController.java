package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Student;
import com.example.demo.ResponseApi.ResponseApi;
import com.example.demo.Service.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	
	@PostMapping(value="/saveStudentOnly",produces = "application/json")
	public ResponseEntity<ResponseApi<Student>> onlySaveStudent(@RequestBody Student student){
		
		return studentService.saveStudentsOnly(student);
	}

}
