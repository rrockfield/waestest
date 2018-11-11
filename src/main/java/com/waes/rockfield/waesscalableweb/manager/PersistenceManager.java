package com.waes.rockfield.waesscalableweb.manager;

import com.waes.rockfield.waesscalableweb.exception.*;
import java.io.IOException;

public interface PersistenceManager {

	public void save(String dir, String fileName, byte[] data)
			throws NullDataException, InvalidFileNameException, IOException;
}
