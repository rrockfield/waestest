package com.waes.rockfield.waesscalableweb.manager;

import com.waes.rockfield.waesscalableweb.exception.EmptyStringException;
import java.util.Base64;
import org.springframework.stereotype.Service;

/**
 * Base64Manager implementation using Java 8's Base64 decoder
 * 
 * @author lroca
 */
@Service("java8Base64Manager")
public class Java8Base64Manager implements Base64Manager {

	/**
	 * @param data Base64 encoded data to be decoded
	 * @return byte array with decoded data
	 * @throws EmptyStringException when the data input is null or empty
	 */
	@Override
	public byte[] decode(String data) throws EmptyStringException {
		if (data == null || data.isEmpty()) {
			throw new EmptyStringException();
		}
		return Base64.getDecoder().decode(data);
	}
}
