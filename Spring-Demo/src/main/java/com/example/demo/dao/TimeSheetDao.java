package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.TimeSheet;
import com.example.demo.repo.TimeSheetRepo;

@Repository
public class TimeSheetDao {

	@Autowired
	private TimeSheetRepo timeSheetRepo;

	public void saveTimeSheet(TimeSheet timeSheet) {
		timeSheetRepo.save(timeSheet);
	}
	
	public Optional<TimeSheet> findById(int timeSheetId){
		return timeSheetRepo.findById(timeSheetId);
	}
	
	public List<TimeSheet> findByManagerId(int managerId){
		return timeSheetRepo.findByManagerIdOrderByWeekEndDateDesc(managerId);
	}
	
	public List<TimeSheet> findByEmployeeId(int employeeId){
		return timeSheetRepo.findByEmployeeIdOrderByWeekEndDateDesc(employeeId);
	}
}
