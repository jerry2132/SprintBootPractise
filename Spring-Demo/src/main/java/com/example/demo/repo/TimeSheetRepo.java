package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.TimeSheet;

public interface TimeSheetRepo extends JpaRepository<TimeSheet, Integer>{

	
	List<TimeSheet> findByManagerIdOrderByWeekEndDateDesc(int managerId);
	
	List<TimeSheet> findByEmployeeIdOrderByWeekEndDateDesc(int employeeId);
}
