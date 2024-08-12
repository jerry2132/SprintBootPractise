package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Project;
import com.example.demo.response.Response;

@Service
public interface ProjectService {

	public ResponseEntity<Response<List<Project>>> addProject(List<Project> project);
	
}
