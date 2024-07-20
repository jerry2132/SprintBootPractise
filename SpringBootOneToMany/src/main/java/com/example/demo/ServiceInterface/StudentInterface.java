package com.example.demo.ServiceInterface;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Response.ResponseApi;
import com.example.demo.dto.Student;

@Service
public interface StudentInterface {
	
	
	public ResponseEntity<ResponseApi<Student>> saveStudent(Student student);
	
	public ResponseEntity<ResponseApi<Student>> associateStudentToSubject(Student sudent);

}


