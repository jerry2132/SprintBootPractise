package com.example.demo.dto;

import java.time.LocalDate;

import org.hibernate.annotations.CurrentTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Project {

	@Id
	private int projectId;
	
	private String projectName;
	
	@CurrentTimestamp
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	@Enumerated(EnumType.STRING)
	private ProjectStatus status;
	
	@OneToOne(mappedBy = "project")
	private  Manager manager;
	
}
