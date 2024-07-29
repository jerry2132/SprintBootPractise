package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Interface.SubjectInterface;
import com.example.demo.ResponseApi.ResponseApi;
import com.example.demo.dto.StudentDto;
import com.example.demo.dto.SubjectDto;

import jakarta.validation.Valid;

@RestController
public class SubjectController {

	@Autowired
	private SubjectInterface subjectInterface;
	
	@PostMapping(value="/saveSubjectsOnly", produces = "application/json")
	public ResponseEntity<ResponseApi<List<SubjectDto>>> saveMultipleSubjectsOnly(@Valid  @RequestBody List<SubjectDto> subjectDto){
		
		return subjectInterface.saveMultipleSubjectsOnly(subjectDto);
	}
	
	@PostMapping("/assocaiteASubjectToAllStudents")
	public ResponseEntity<ResponseApi<List<StudentDto>>> associateASubjectToAllStudents(@Valid  @RequestBody SubjectDto subjectDto){
	
		return subjectInterface.assocaiteASubjectToAllStudents(subjectDto);
		
	}
}
