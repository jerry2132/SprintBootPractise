package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.InquiryChannel;
import com.example.demo.repo.InquiryChannelRepo;

@Repository
public class InquiryChannelDao {

	@Autowired
	private InquiryChannelRepo inquiryChannelRepo;
	
	
	public InquiryChannel saveInquiry(InquiryChannel inquiryChannel) {
		return inquiryChannelRepo.save(inquiryChannel);
	}
}
