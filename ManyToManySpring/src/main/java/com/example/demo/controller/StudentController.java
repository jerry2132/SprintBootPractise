package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Student;
import com.example.demo.Interface.StudentInterface;
import com.example.demo.ResponseApi.ResponseApi;
import com.example.demo.dto.StudentDto;
import com.example.demo.dto.SubjectDto;
import com.example.demo.dto.SubjectDto;

import jakarta.validation.Valid;

@RestController
public class StudentController {

	@Autowired
	StudentInterface studentInterface;

	@PostMapping(value = "/saveStudentandSubject", produces = "application/json")
	public ResponseEntity<ResponseApi<List<Student>>> saveStudentAndSubject(@RequestBody List<StudentDto> studentDto) {

		return studentInterface.saveStudentAndSubject(studentDto);
	}

	@GetMapping("/normal")
	public String getData() {
		return " hajbddjaknldsdm";
	}

	@PostMapping(value = "/saveOnlyStudents", produces = "application/json")
	public ResponseEntity<ResponseApi<List<StudentDto>>> saveOnlyStudents(
			@Valid @RequestBody List<StudentDto> studentDto) {

		return studentInterface.saveOnlyStudents(studentDto);
	}

	@PostMapping("/saveAStudentAndAssociateAllSubjects")
	public ResponseEntity<ResponseApi<StudentDto>> saveAStudentAndAssociateAllSubjects(
			@Valid @RequestBody StudentDto studentDto) {
		
		return studentInterface.saveStudentAndAssociateAllSubjects(studentDto);

	}
	
	
	@PostMapping("/associateAlreadyPresentSubjectToStudent")
	public ResponseEntity<ResponseApi<StudentDto>> associateStudentWithAlredyPersentSubject(@RequestParam("studentId") int studentId,
			@RequestBody List<SubjectDto> subjectDto){
		
		return studentInterface.associateStudentWithAlreadyPresentSubject(studentId,subjectDto);
	}

}
