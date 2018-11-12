package com.waes.rockfield.waesscalableweb.exception;

/**
 * Exception thrown when the String parameter is empty
 *
 * @author lroca
 */
public class EmptyStringException extends Exception {

	public EmptyStringException() {
		super("Encoded Base64 String data cannot be empty");
	}
}
