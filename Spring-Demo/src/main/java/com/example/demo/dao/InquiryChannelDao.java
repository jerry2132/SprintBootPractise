package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

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
	
	public List<InquiryChannel> getAll(){
		return inquiryChannelRepo.findAll();
	}
	
	public Optional<InquiryChannel> findById(int channelId) {
		return inquiryChannelRepo.findById(channelId);
	}
}
