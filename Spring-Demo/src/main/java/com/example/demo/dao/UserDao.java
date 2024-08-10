package com.example.demo.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.User;
import com.example.demo.repo.UserRepository;

@Repository
public class UserDao {

	@Autowired
	private UserRepository userRepository;
	
	
	
	public Optional<User> findByUserName(String userName){
		return userRepository.findByUserName(userName);
	}
	
	public Optional<User> findById(int userId) {
		return userRepository.findById(userId);
	}
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
}
