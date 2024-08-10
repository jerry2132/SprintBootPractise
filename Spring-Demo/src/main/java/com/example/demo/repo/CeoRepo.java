package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.Ceo;

public interface CeoRepo extends JpaRepository<Ceo, Integer>{

}
