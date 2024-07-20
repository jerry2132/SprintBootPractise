package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Response.ResponseApi;
import com.example.demo.ServiceInterface.StudentInterface;
import com.example.demo.dto.Student;

@RestController
public class StudentController {

	@Autowired
	private StudentInterface studentInterface;
	
	
	@PostMapping("/saveStudent")
	public ResponseEntity<ResponseApi<Student>> saveStudent(@RequestBody Student student){
		
		return studentInterface.saveStudent(student);
	}
	
	@PutMapping(value="/associateStudentSubject")
	public ResponseEntity<ResponseApi<Student>> associateStudentToSubject(@RequestBody Student student){
	
		return studentInterface.associateStudentToSubject(student);
	}
	
}
