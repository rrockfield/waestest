package com.waes.rockfield.waesscalableweb.model;

public class ReceivedResponse {

	private ReceivedStatus status;
	private String message;

	public ReceivedResponse() {
	}

	public ReceivedResponse(ReceivedStatus status) {
		this.status = status;
	}

	public ReceivedResponse(ReceivedStatus status, String message) {
		this(status);
		this.message = message;
	}

	public ReceivedStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public void setStatus(ReceivedStatus status) {
		this.status = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
