package com.waes.rockfield.waesscalableweb.exception;

public class EmptyStringException extends Exception {
	
	public EmptyStringException() {
		super("Encoded Base64 String data cannot be empty");
	}
}
