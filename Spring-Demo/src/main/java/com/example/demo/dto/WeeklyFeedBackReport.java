package com.example.demo.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CurrentTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeeklyFeedBackReport {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "feedback_seq")
	@SequenceGenerator(name = "feedback_seq", sequenceName = "feedback_sequence", initialValue = 9000)
	private int feedbackId;

	private int employeeId;

	private int managerId;

	@CurrentTimestamp
	private LocalDateTime createdOn;

	private LocalDate weekEnding;

	private String content;

	private int rating;

	@Enumerated(EnumType.STRING)
	private FeedBackStatus feedBackStatus;

	private String comments;

	private LocalDateTime approvedOn;

}
