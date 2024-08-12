package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

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
	
	public List<Project> saveAllProjects(List<Project> project){
		return projectRepo.saveAll(project);
	}
	
	public Optional<Project> findProject(int projectId) {
		return projectRepo.findById(projectId);
	}
}
