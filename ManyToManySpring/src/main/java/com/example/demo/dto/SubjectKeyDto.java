package com.example.demo.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubjectKeyDto {

	private int id;
	
	@NotBlank
	@Column(unique=true)
	private int subjectId;
}
