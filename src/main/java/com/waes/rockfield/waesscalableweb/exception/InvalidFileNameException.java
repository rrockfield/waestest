package com.waes.rockfield.waesscalableweb.exception;

/**
 * Exception thrown when the file name doesn't fulfill the current OS
 * requirements
 *
 * @author lroca
 */
public class InvalidFileNameException extends Exception {

	public InvalidFileNameException(String fileName) {
		super(fileName + " is an invalid ID");
	}
}
