package com.example.demo.ServiceInterface;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Response.ResponseApi;
import com.example.demo.dto.Student;

@Service
public interface StudentInterface {
	
	
	public ResponseEntity<ResponseApi<Student>> saveStudent(Student student);
	
	public ResponseEntity<ResponseApi<Student>> associateStudentToSubject(Student sudent);

	public ResponseEntity<ResponseApi<List<Student>>> getAllStudents(int offset, int pageSize);
	
	public ResponseEntity<ResponseApi<Student>> deleteStudentByStudentId(int studentId);
	
	public ResponseEntity<ResponseApi<Student>> deleteOnlyStudent(int studentId);
}


