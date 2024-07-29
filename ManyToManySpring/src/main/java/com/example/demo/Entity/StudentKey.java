package com.example.demo.Entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentKey {

	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	

	private int studentId;



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
