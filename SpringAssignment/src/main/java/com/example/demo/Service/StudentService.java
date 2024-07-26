package com.example.demo.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Student;
import com.example.demo.ResponseApi.ResponseApi;

@Service
public interface StudentService {
	
	public ResponseEntity<ResponseApi<Student>> saveStudentsOnly(Student student);
	
	public ResponseEntity<ResponseApi<List<Student>>> findAllWithSubjectMathsAndScience(int offset, int pageSize);
	
	public ResponseEntity<ResponseApi<List<Student>>> deleteFirstFiveScienceStudents();
	

}
