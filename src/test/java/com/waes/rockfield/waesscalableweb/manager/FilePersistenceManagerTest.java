package com.waes.rockfield.waesscalableweb.manager;

import com.waes.rockfield.waesscalableweb.exception.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import org.junit.Test;
import org.junit.Assert;

public class FilePersistenceManagerTest {

	private static final String DIR = "DeleteMe";
	private static final String DATA = "There is no spoon";
	private static final String FILE_NAME = "HappyPath";

	@Test
	public void testHappyPath()
			throws NullDataException, InvalidFileNameException, IOException {
		new FilePersistenceManager().save(DIR, FILE_NAME, DATA.getBytes());
		File file = Paths.get(DIR, FILE_NAME).toFile();
		Assert.assertTrue(file.exists());
		file.delete();
		Assert.assertFalse(file.exists());
	}

	@Test(expected = NullDataException.class)
	public void testNullContent()
			throws NullDataException, InvalidFileNameException, IOException {
		byte[] data = null;
		new FilePersistenceManager().save(DIR, FILE_NAME, data);
	}

	@Test(expected = NullDataException.class)
	public void testEmptyByteArray()
			throws NullDataException, InvalidFileNameException, IOException {
		byte[] data = new byte[] {};
		new FilePersistenceManager().save(DIR, FILE_NAME, data);
	}

	@Test(expected = InvalidFileNameException.class)
	public void testInvalidFileName()
			throws NullDataException, InvalidFileNameException, IOException {
		String fileName = "InvalidFileName:[{{/*$%&()=?¡¨*]}";
		new FilePersistenceManager().save(DIR, fileName, DATA.getBytes());
	}
}
