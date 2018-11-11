package com.waes.rockfield.waesscalableweb.manager;

import com.waes.rockfield.waesscalableweb.exception.EmptyStringException;
import java.io.UnsupportedEncodingException;
import org.junit.Assert;
import org.junit.Test;

public class Java8Base64ManagerTest {
	
	private static final String SOURCE = "There is no spoon";
	private static final String ENCODED = "VGhlcmUgaXMgbm8gc3Bvb24=";
	
	@Test
	public void testValidData() throws UnsupportedEncodingException, EmptyStringException {
		byte[] data = new Java8Base64Manager().decode(ENCODED);
		String decoded = new String(data, "UTF8");
		Assert.assertEquals(SOURCE, decoded);
	}
	
	@Test(expected = EmptyStringException.class)
	public void testNullString() throws UnsupportedEncodingException, EmptyStringException {
		new Java8Base64Manager().decode(null);
	}
	
	@Test(expected = EmptyStringException.class)
	public void testEmptyString() throws UnsupportedEncodingException, EmptyStringException {
		new Java8Base64Manager().decode("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidData() throws UnsupportedEncodingException, EmptyStringException {
		new Java8Base64Manager().decode("AGG%&Y#GQ#G$2/¨*");
	}
}
