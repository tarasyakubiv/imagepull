package com.tarasyakubiv.demo.client.dto;

public class UserRequest {
	private final String start;
	private final String end;
	
	public UserRequest(String start, String end) {
		super();
		this.start = start;
		this.end = end;
	}
	
	public UserRequest() {
		this.start = null;
		this.end = null;
	}
	

	public String getStart() {
		return start;
	}

	public String getEnd() {
		return end;
	}
	
	
	
}
