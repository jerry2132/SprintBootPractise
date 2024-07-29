package com.example.demo.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "studentId"))
public class Student {

	@EmbeddedId
	private StudentKey studentKey;

	private String name;

	private String email;

	@ManyToMany(cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Subject> subject;

	@Override
	public String toString() {
		return "Student{" + "studentKey=" + studentKey + ", name='" + name + '\'' + ", email='" + email + '\''
				+ ", subjects=" + (subject != null ? subject.stream().map(Subject::getSubjectKey).toList() : null) + '}';
	}
}
