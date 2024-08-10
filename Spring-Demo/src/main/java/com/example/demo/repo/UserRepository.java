package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	public Optional<User> findByUserName(String userName);

}
