package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.WeeklyFeedBackReport;

public interface WeeklyFeedBackReportRepo extends JpaRepository<WeeklyFeedBackReport, Integer>{

	
	List<WeeklyFeedBackReport> findByManagerIdOrderByCreatedOnDesc(int managerId);
	
	List<WeeklyFeedBackReport> findByEmployeeIdOrderByCreatedOnDesc(int employeeId);
	
}
