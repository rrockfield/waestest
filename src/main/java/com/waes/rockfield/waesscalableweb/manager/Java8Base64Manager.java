package com.waes.rockfield.waesscalableweb.manager;

import com.waes.rockfield.waesscalableweb.exception.EmptyStringException;
import java.util.Base64;
import org.springframework.stereotype.Service;

@Service("java8Base64Manager")
public class Java8Base64Manager implements Base64Manager {

	@Override
	public byte[] decode(String data) throws EmptyStringException {
		if (data == null || data.isEmpty()) {
			throw new EmptyStringException();
		}
		return Base64.getDecoder().decode(data);
	}
}
