package com.example.demo.ServiceInterface;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Response.ResponseApi;
import com.example.demo.dto.Subject;

@Service
public interface SubjectInterface {

	public ResponseEntity<ResponseApi<List<Subject>>> saveSubject(List<Subject> sub);
	
}
