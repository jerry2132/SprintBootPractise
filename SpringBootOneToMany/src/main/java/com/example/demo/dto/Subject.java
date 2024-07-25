package com.example.demo.dto;

import java.util.Objects;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Subject {


	@Id
	private int id;

	
	private int subjectId;

	@NotBlank(message = "AUTHOR CAN NOT BE EMPTY OR NULL, GIVE SOME VALUE TO AUTHOR")
//	@validateAnnotation(message ="can not be empty")
	private String author;

	@NotEmpty(message="NAME CANNOT BE EMPTY OR NULL")
//	@validateAnnotation(message ="can not be empty")
	private String name;

	@ManyToOne
//	@JsonIgnore
	@JsonBackReference
	private Student student;
	
//	@JsonProperty("student")
//	public Student getStudent() {
//		return student;
//	}
//	
//	@JsonProperty("student")
//	public void setStudent(Student student) {
//		this.student = student;
//	}

	
	
	
	@Override
	public String toString() {
		return "Subject [id=" + id + ", subjectId=" + subjectId + ", author=" + author + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, id, name, student, subjectId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Subject other = (Subject) obj;
		return id == other.id && subjectId == other.subjectId;
	}


}
