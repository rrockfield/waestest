package com.waes.rockfield.waesscalableweb.model;

public class DiffResponse {
	
	private DiffStatus status;
	private String message;

	public DiffResponse(DiffStatus status, String message) {
		this.status = status;
		this.message = message;
	}

	public DiffStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}
}
