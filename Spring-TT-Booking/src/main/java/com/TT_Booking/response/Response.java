package com.TT_Booking.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Response <T> {
	
	private String status;
	
	private String message;
	
	private T data;

}
