package com.example.demo.dto;

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
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InquiryChannel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "channel_seq")
    @SequenceGenerator(name = "channel_seq", sequenceName = "channel_sequence", initialValue = 8000)
	private int channelId;

	@CurrentTimestamp
	private LocalDateTime createdOn;

	private String message;

	private int createdById;

	private int inquireToId;

	private String topic;

	@Enumerated(EnumType.STRING)
	private PriorityLevel priorityLevel;

	@Enumerated(EnumType.STRING)
	private InquiryStatus inquiryStatus;

}
