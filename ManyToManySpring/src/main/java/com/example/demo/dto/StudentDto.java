package com.example.demo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDto {

	
	private StudentKeyDto studentKey;

	@NotBlank(message="name can be null or blank")
	@Size(min = 3, message="name must be greater than 3 characters")
	private String name;

	@NotBlank(message="email can not  be null or  blank")
	@Column(unique = true)
	private String email;

	private List<SubjectDto> subjectDto;

}
