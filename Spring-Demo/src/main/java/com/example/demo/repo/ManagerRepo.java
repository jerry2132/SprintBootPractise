package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.Manager;

public interface ManagerRepo  extends JpaRepository<Manager, Integer>{

	public Optional<Manager> findByUserName(String userName);
}
