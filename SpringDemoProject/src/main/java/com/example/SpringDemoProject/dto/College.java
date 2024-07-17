package com.example.SpringDemoProject.dto;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class College {
	
	@Id
	private int id;
	
	private String collegeName;
	
	private String address;
	
	private String type;
	
	private String imageUrl;
	
	@Transient
	@JsonIgnore
	private MultipartFile orginalImage;
		
	
}
