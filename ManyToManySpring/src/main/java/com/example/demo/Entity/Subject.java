package com.example.demo.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "subjectId"))
public class Subject {

	@EmbeddedId
	private SubjectKey subjectKey;

	private String name;

	private String author;

	private double price;

	@ManyToMany(mappedBy = "subject")
	@JsonBackReference
	private List<Student> student;

	@Override
	public String toString() {
		return "Subject{" + "subjectKey=" + subjectKey + ", name='" + name + '\'' + ", author='" + author + '\''
				+ ", price=" + price + ",student "+(student != null ?student.stream().map(Student :: getStudentKey).toList():null) +'}';
	}
}
//one parameter list of student 