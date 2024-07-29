package com.example.demo.dto;

import java.util.List;

import com.example.demo.Entity.Student;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDto {

	private SubjectKeyDto subjectKey;

	@NotBlank(message ="name can not be empty or null")
	@Size(min=3, message="name must be grater than 3 characters ")
	private String name;

	@NotBlank(message="author can not be null or empty")
	private String author;


	@Min(value=1,message="price must be grater than 0")
	private double price;

	private List<StudentDto> studentDto;

}
