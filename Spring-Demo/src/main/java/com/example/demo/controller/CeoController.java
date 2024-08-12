package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Project;
import com.example.demo.response.Response;
import com.example.demo.service.ProjectService;

@RestController
@RequestMapping("/ceo")
public class CeoController {
	
	@Autowired
	private ProjectService projectService;

	public ResponseEntity<Response<List<Project>>> addProject(@RequestBody List<Project> project){
		
		return projectService.addProject(project);
		
	}
	
}
