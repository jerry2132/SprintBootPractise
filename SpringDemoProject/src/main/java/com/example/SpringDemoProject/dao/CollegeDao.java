package com.example.SpringDemoProject.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.example.SpringDemoProject.dto.College;
import com.example.SpringDemoProject.repository.CollegeRepository;

@Repository
public class CollegeDao {
	
	
	@Autowired
	CollegeRepository collegeRepository;
	
	public void saveCollegeDao(College college) {
		 collegeRepository.save(college);
	}
	
	public List<College> getAllCollege() {
		return collegeRepository.findAll();
		
	}
	
	public void   saveMultipleCollege(List<College> college) {
		
		collegeRepository.saveAll(college);
		
	}
	
	public Optional<College> findCollegeById(int collegeId) {
		
		return collegeRepository.findById(collegeId);
	}
	
	public List<College> findByName(String name){
		return collegeRepository.findByCollegeName(name);
	}
	
	
	public List<College> finCollegeBySorting(String field){
		
		return collegeRepository.findAll(Sort.by(field));
	}
	
	
	public List<College> paginationAndSorting(Integer offSet , Integer pageSize,String field){
		
		Page<College> col = collegeRepository.findAll(PageRequest.of(offSet,pageSize).withSort(Sort.by(field)));
		
		return col.getContent();
	}

}
