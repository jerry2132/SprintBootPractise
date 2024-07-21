package com.example.demo.ServiceInterface;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Response.ResponseApi;
import com.example.demo.dto.Subject;

@Service
public interface SubjectInterface {

	public ResponseEntity<ResponseApi<List<Subject>>> saveSubject(List<Subject> sub);
	
	public ResponseEntity<ResponseApi<List<Subject>>> getAllSubject(Integer offset,Integer pageSize);
	
	public ResponseEntity<ResponseApi<List<Subject>>> assocaiteSubjectToPresentStudent(List<Subject> sub);
	
}
