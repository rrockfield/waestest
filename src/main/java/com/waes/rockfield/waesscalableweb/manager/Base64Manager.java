package com.waes.rockfield.waesscalableweb.manager;

import com.waes.rockfield.waesscalableweb.exception.EmptyStringException;

public interface Base64Manager {
	
	byte[] decode(String data) throws EmptyStringException;
}
