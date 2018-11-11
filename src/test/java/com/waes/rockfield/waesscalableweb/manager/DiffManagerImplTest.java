package com.waes.rockfield.waesscalableweb.manager;

import com.waes.rockfield.waesscalableweb.exception.MissingFileException;
import com.waes.rockfield.waesscalableweb.model.DiffResponse;
import com.waes.rockfield.waesscalableweb.model.DiffStatus;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import org.junit.Assert;
import org.junit.Test;

public class DiffManagerImplTest {
	
	@Test
	public void TestEqualFiles() throws MissingFileException, IOException {
		DiffResponse response = new DiffManagerImpl().compare("src/test/resources/equal");
		Assert.assertEquals(DiffStatus.EQUAL, response.getStatus());
	}
	
	@Test(expected = InvalidPathException.class)
	public void TestInvalidId() throws MissingFileException, IOException {
		new DiffManagerImpl().compare("InvalidId:[{{/*$%&()=?¡¨*]}");
	}
	
	@Test(expected = MissingFileException.class)
	public void TestMissingId() throws MissingFileException, IOException {
		new DiffManagerImpl().compare("validButMissingId");
	}
	
	@Test(expected = MissingFileException.class)
	public void TestMissingLeftFile() throws MissingFileException, IOException {
		new DiffManagerImpl().compare("src/test/resources/noleft");
	}
	
	@Test(expected = MissingFileException.class)
	public void TestMissingRightFile() throws MissingFileException, IOException {
		new DiffManagerImpl().compare("src/test/resources/noright");
	}
	
	@Test
	public void TestDifferentSizeFiles() throws MissingFileException, IOException {
		DiffResponse response = new DiffManagerImpl().compare("src/test/resources/differentSizes");
		Assert.assertEquals(DiffStatus.DIFFERENT, response.getStatus());
		Assert.assertEquals(DiffManagerImpl.DIFFERENT_SIZE_MESSAGE, response.getMessage());
	}
	
	@Test
	public void TestDifferentByteFiles() throws MissingFileException, IOException {
		DiffResponse response = new DiffManagerImpl().compare("src/test/resources/differentBytes");
		Assert.assertEquals(DiffStatus.DIFFERENT, response.getStatus());
		Assert.assertTrue(response.getMessage().endsWith(" 0, 2, 18, 19, 20."));
	}
}
