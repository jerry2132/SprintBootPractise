package com.example.demo.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Student;
import com.example.demo.ResponseApi.ResponseApi;

@Service
public interface StudentService {
	
	public ResponseEntity<ResponseApi<Student>> saveStudentsOnly(Student student);

}
