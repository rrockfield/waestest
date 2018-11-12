package com.waes.rockfield.waesscalableweb.manager;

import com.waes.rockfield.waesscalableweb.exception.*;
import java.io.IOException;

/**
 * Interface with the Persistence Manager method signatures. It may be
 * implemented using different types of persistence for the files (i.e. plain OS
 * files, relational databases, NoSQL databases, cloud services, etc.).
 *
 * @author lroca
 */
public interface PersistenceManager {

	public void save(String dir, String fileName, byte[] data)
			throws NullDataException, InvalidFileNameException, IOException;
}
