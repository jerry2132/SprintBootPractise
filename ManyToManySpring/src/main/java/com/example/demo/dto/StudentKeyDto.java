package com.example.demo.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentKeyDto {

	
	private int id;
	
	@Column(unique=true)
	@NotEmpty
	private int studentId;
	
}
