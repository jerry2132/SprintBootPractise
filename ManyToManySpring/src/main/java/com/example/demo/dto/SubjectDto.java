package com.example.demo.dto;

import java.util.List;

import com.example.demo.Entity.Student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubjectDto {

	private int subjectId;

	private String name;

	private String author;

	private double price;

	private List<StudentDto> studentDto;

}
