package com.example.demo.dto;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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

	private String author;

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
