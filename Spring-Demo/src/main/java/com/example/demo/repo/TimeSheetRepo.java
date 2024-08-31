package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.TimeSheet;

public interface TimeSheetRepo extends JpaRepository<TimeSheet, Integer>{

}
