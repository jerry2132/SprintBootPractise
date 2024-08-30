package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.WeeklyFeedBackReport;
import com.example.demo.repo.WeeklyFeedBackReportRepo;

@Repository
public class WeeklyFeedBackReportDao {

	@Autowired
	private WeeklyFeedBackReportRepo weeklyFeedBackReportRepo;

	public WeeklyFeedBackReport saveFeedBack(WeeklyFeedBackReport weeklyFeedBackReport) {
		return weeklyFeedBackReportRepo.save(weeklyFeedBackReport);
	}

	public List<WeeklyFeedBackReport> findByManagerIdOrderByCreatedOn(int managerId) {
		return weeklyFeedBackReportRepo.findByManagerIdOrderByCreatedOnDesc(managerId);
	}
	
	public Optional<WeeklyFeedBackReport> findByFeedbackId(int feedbackId){
		return weeklyFeedBackReportRepo.findById(feedbackId);
	}

}
