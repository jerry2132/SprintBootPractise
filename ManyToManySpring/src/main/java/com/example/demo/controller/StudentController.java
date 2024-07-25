package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Student;
import com.example.demo.Interface.StudentInterface;
import com.example.demo.ResponseApi.ResponseApi;
import com.example.demo.dto.StudentDto;

@RestController
public class StudentController {

	@Autowired
	StudentInterface studentInterface;
	
	
	@PostMapping("/saveStudentandSubject")
	public ResponseEntity<ResponseApi<List<Student>>> saveStudentAndSubject(@RequestBody List<StudentDto> studentDto){
		
		return studentInterface.saveStudentAndSubject(studentDto);
	}
	
}
