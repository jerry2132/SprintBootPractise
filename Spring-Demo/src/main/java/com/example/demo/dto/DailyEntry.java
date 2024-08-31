package com.example.demo.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DailyEntry {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "daily_entry_seq")
	@SequenceGenerator(name = "daily_entry_seq", sequenceName = "daily_entry_sequence", initialValue = 1)
	private int dailyEntryId;

	private LocalDate date;
	private LocalTime checkInTime;
	private LocalTime checkOutTime;
	private double hoursWorked;
}
