package com.example.demo.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TimeSheet {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "timesheet_seq")
    @SequenceGenerator(name = "timesheet_seq", sequenceName = "timesheet_sequence", initialValue = 7000)
    private int timesheetId;

    private int employeeId;
    private String employeeName;
    
    private int managerId;

    private LocalDate weekStartDate;
    private LocalDate weekEndDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "timesheetId")
    private List<DailyEntry> dailyEntries;

    private double totalHoursWorked;
    
    @Enumerated(EnumType.STRING)
    private FeedBackStatus status;


}
