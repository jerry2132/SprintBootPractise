package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.DailyEntry;

public interface DailyEntryRepo extends JpaRepository<DailyEntry, Integer>{

}
