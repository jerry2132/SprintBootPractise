package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.InquiryChannel;

public interface InquiryChannelRepo extends JpaRepository<InquiryChannel, Integer>{

}
