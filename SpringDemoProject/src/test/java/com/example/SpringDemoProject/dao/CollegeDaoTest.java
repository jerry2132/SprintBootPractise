package com.example.SpringDemoProject.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.SpringDemoProject.dto.College;
import com.example.SpringDemoProject.dto.College.CollegeBuilder;
import com.example.SpringDemoProject.repository.CollegeRepository;

@ExtendWith(MockitoExtension.class)
class CollegeDaoTest {

	@Mock
	CollegeRepository collegeRepo;
	
	@InjectMocks
	CollegeDao coldao;
	
	
	College college;
	
	@BeforeEach
	void createCollege() {
		college = College.builder().id(121).collegeName("hnbgu").address("kotdwara").type("private").build();
		
	}
	
	@Test
	void testFindCollegeById() {
		when(collegeRepo.findById(121)).thenReturn(Optional.of(college));
		College college = coldao.findCollegeById(121).get();
		
		assertEquals(121, college.getId());
		
	}
	
	

}
