package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Response.ResponseApi;
import com.example.demo.ServiceInterface.SubjectInterface;
import com.example.demo.dto.Subject;

import jakarta.websocket.server.PathParam;

@RestController
public class SubjectController {

	
	@Autowired
	SubjectInterface subjectInterface;
	
	
	@PostMapping("/saveSubject")
	public ResponseEntity<ResponseApi<List<Subject>>> saveSubject(@RequestBody List<Subject> sub){
		
		return subjectInterface.saveSubject(sub);
	}
	 
	@GetMapping("/getAllSubject/{offset}/{pageSize}")
	public ResponseEntity<ResponseApi<List<Subject>>> getAllSubject(@PathVariable Integer offset,@PathVariable Integer pageSize){
		return subjectInterface.getAllSubject(offset, pageSize);
	}
	
	@PutMapping("/associateSubjectToStudent")
	public ResponseEntity<ResponseApi<List<Subject>>> associateSubjectToStudent(@RequestBody List<Subject> subject){
		return subjectInterface.assocaiteSubjectToPresentStudent(subject);
	}

}
