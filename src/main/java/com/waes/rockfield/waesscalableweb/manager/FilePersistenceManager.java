package com.waes.rockfield.waesscalableweb.manager;

import com.waes.rockfield.waesscalableweb.exception.*;
import java.io.IOException;
import java.nio.file.*;
import org.springframework.stereotype.Service;

/**
 * PersistanceManager implementation using plain OS files.
 *
 * @author lroca
 */
@Service("filePersistenceManager")
public class FilePersistenceManager implements PersistenceManager {

	/**
	 * Persist a given data to a plain OS file
	 *
	 * @param dir OS path to a folder for the file to be persisted
	 * @param fileName Name to be used for the file when persisted
	 * @param data Content to be added into the file when persisted
	 * @throws NullDataException when the byte array is null or empty
	 * @throws InvalidFileNameException when the name is not allowed by the OS
	 * to be used to persist the file
	 * @throws IOException when an unexpected IO error happens while persisting
	 */
	@Override
	public void save(String dir, String fileName, byte[] data)
			throws NullDataException, InvalidFileNameException, IOException {
		if (data == null || data.length <= 0) {
			throw new NullDataException();
		}
		try {
			validateDir(dir);
			Path path = Paths.get(dir, fileName);
			Files.write(path, data);
		} catch (InvalidPathException e) {
			throw new InvalidFileNameException(fileName);
		} catch (IOException e) {
			throw e;
		}
	}

	private void validateDir(String dir) {
		Path path = Paths.get(dir);
		if (!path.toFile().exists()) {
			path.toFile().mkdir();
		}
	}
}
