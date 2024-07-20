package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.Subject;

public interface SubjectRepo extends JpaRepository<Subject, Integer>{

}
