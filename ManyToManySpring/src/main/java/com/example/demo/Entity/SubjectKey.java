package com.example.demo.Entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubjectKey implements Serializable{

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int subjectId;

	

	
	
	@Override
	public int hashCode() {
		return Objects.hash(id, subjectId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubjectKey other = (SubjectKey) obj;
		return id == other.id && subjectId == other.subjectId;
	}


	
	
	
}
