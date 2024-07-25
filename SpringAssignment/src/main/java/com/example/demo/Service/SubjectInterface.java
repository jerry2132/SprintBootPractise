package com.example.demo.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Student;
import com.example.demo.Entity.Subject;
import com.example.demo.ResponseApi.ResponseApi;

@Service
public interface SubjectInterface {

	
	public ResponseEntity<ResponseApi<Student>> saveSubjectAssociateStudent(int studentId , List<Subject> subject);
	
	public ResponseEntity<ResponseApi<Subject>> updateSingleSubject(Subject subject);
}
