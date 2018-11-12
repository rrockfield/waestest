package com.waes.rockfield.waesscalableweb.exception;

/**
 * Exception thrown when there is no data to be processed
 *
 * @author lroca
 */
public class NullDataException extends Exception {

	public NullDataException() {
		super("There is no data to save");
	}
}
