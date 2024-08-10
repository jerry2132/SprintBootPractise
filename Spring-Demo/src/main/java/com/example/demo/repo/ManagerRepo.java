package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.Manager;

public interface ManagerRepo  extends JpaRepository<Manager, Integer>{

}
