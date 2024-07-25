package com.example.demo.dto;

import java.util.List;
import java.util.Objects;

import com.example.demo.ValidationAnnotation.EmailHostNameValidation;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	
	@EmbeddedId
	private StudentKey id;
	
	@NotEmpty(message ="name can not be empty")
	private String name;
	
	@NotEmpty(message="email can not be empty")
	@Email(message="fromat is not valid")
	@EmailHostNameValidation
	private String email;
	
	@OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Subject> subject;
	
	

	public StudentKey getId() {
		return id;
	}

	public void setId(StudentKey id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

//	@JsonProperty(value="subject")
	public List<Subject> getSubject() {
		return subject;
	}

//	@JsonProperty(value="subject")
	public void setSubject(List<Subject> subject) {
		this.subject = subject;
	}
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", email=" + email + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, name, subject);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(subject, other.subject);
	}
	
}


//owner subject
//save 1 student and associate it with 5 subjects 
//use mapping and make owner

//to readd 10 string type o finputs from user and store  in collection or list and reverse each and every string 
//to found the sum o prime numbers pressent in arraylist // ierator
//