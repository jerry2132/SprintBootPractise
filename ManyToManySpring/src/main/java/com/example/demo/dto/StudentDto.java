package com.example.demo.dto;

import java.util.List;

import com.example.demo.Entity.StudentKey;
import com.example.demo.Entity.Subject;

import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@AllArgsConstructor
@NoArgsConstructor
@Setter
@Data
public class StudentDto {

	
	private int studentId;
	
	private String name;

	private String email;
	
	private List<SubjectDto> subjectDto;

}
