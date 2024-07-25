package com.example.demo.Interface;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Student;
import com.example.demo.ResponseApi.ResponseApi;
import com.example.demo.dto.StudentDto;

@Service
public interface StudentInterface {

	
	public ResponseEntity<ResponseApi<List<Student>>> saveStudentAndSubject(List<StudentDto> studentDto);
}
