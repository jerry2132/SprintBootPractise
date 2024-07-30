package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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

	@PostMapping(value = "/saveSubjectsOnly", produces = "application/json")
	public ResponseEntity<ResponseApi<List<SubjectDto>>> saveMultipleSubjectsOnly(
			@Valid @RequestBody List<SubjectDto> subjectDto) {

		return subjectInterface.saveMultipleSubjectsOnly(subjectDto);
	}

	@PostMapping("/assocaiteASubjectToAllStudents")
	public ResponseEntity<ResponseApi<List<StudentDto>>> associateASubjectToAllStudents(
			@Valid @RequestBody SubjectDto subjectDto) {

		return subjectInterface.assocaiteASubjectToAllStudents(subjectDto);

	}

	@PutMapping("/updatePrice")
	public ResponseEntity<ResponseApi<SubjectDto>> updatePriceBySubjectId(@RequestParam("subjectId") int subjectId,
			@RequestParam("price") double price) {

		return subjectInterface.updatePriceBySubjectId(subjectId, price);
	}
	
	@PutMapping("/associateSingleSubjectsToMultipleStudents")
	public ResponseEntity<ResponseApi<List<StudentDto>>> associateSingleSubjectsToMultipleStudents(
			@RequestParam("subjectId") int subjectId, @RequestBody List<StudentDto> studentDto) {

		return subjectInterface.associateSingleSubjectToMultipleStudents(subjectId, studentDto);
		
	}

}
