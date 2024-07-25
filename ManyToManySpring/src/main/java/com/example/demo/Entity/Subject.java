package com.example.demo.Entity;

import java.util.List;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Subject {
	
	
	@EmbeddedId
	private SubjectKey subjectKey;
	
	private String name ;
	
	private String author;

	private double price;
	
	@ManyToMany(mappedBy = "subject")
	private List<Student> student;
}
//one parameter list of student 