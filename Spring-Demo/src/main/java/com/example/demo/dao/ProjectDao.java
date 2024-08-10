package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.Project;
import com.example.demo.repo.ProjectRepo;

@Repository
public class ProjectDao {

	@Autowired
	private ProjectRepo projectRepo;
	
	public Project saveProject(Project project) {
		return projectRepo.save(project);
	}
	
}
