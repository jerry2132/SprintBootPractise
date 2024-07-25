package com.example.demo.dto;

import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class StudentKey {
	
	@NotEmpty
	private int id;
	
	@NotEmpty
	private int studentId;

	
	
	public StudentKey() {

	}

	public StudentKey(int id, int studentId) {
		super();
		this.id = id;
		this.studentId = studentId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, studentId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentKey other = (StudentKey) obj;
		return id == other.id && studentId == other.studentId;
	}
	
	
	

}
