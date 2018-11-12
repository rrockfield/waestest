package com.waes.rockfield.waesscalableweb.manager;

import com.waes.rockfield.waesscalableweb.exception.EmptyStringException;

/**
 * Interface withe the Base64 Manager method signature. It may be implemented
 * using different types of decoders.
 *
 * @author lroca
 */
public interface Base64Manager {

	byte[] decode(String data) throws EmptyStringException;
}
