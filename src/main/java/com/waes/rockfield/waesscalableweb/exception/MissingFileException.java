package com.waes.rockfield.waesscalableweb.exception;

/**
 * Exception thrown when the file you are looking for cannot be found
 *
 * @author lroca
 */
public class MissingFileException extends Exception {

	public MissingFileException(String message) {
		super(message);
	}
}
