package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.Project;

public interface ProjectRepo extends JpaRepository<Project, Integer>{

}
