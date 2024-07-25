package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Student;
import com.example.demo.Entity.Subject;
import com.example.demo.ResponseApi.ResponseApi;
import com.example.demo.Service.SubjectInterface;

@RestController
public class SubjectController {

	@Autowired
	private SubjectInterface subjectInterface;

	@PostMapping("/saveSubjectWithAssociation/{studentId}")
	public ResponseEntity<ResponseApi<Student>> saveSubjectAndAssociateWithStudent(@PathVariable Integer studentId,
			@RequestBody List<Subject> subject) {

		return subjectInterface.saveSubjectAssociateStudent(studentId, subject);
	}
	
	@PutMapping("/updateSingleSubject")
	public ResponseEntity<ResponseApi<Subject>> updateSubject(@RequestBody Subject subject){
		
		return subjectInterface.updateSingleSubject(subject);
	}

}
