package com.waes.rockfield.waesscalableweb.exception;

public class InvalidFileNameException extends Exception {

	public InvalidFileNameException(String fileName) {
		super(fileName + " is an invalid ID");
	}
}
