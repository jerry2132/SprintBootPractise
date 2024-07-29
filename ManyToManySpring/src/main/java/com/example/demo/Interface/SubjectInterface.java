package com.example.demo.Interface;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.ResponseApi.ResponseApi;
import com.example.demo.dto.StudentDto;
import com.example.demo.dto.SubjectDto;

@Service
public interface SubjectInterface {

	
	public ResponseEntity<ResponseApi<List<SubjectDto>>> saveMultipleSubjectsOnly(List<SubjectDto> subjectDto);
	
	public ResponseEntity<ResponseApi<List<StudentDto>>> assocaiteASubjectToAllStudents(SubjectDto subjectDto);
	
}
