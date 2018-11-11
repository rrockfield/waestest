package com.waes.rockfield.waesscalableweb.manager;

import com.waes.rockfield.waesscalableweb.exception.*;
import java.io.IOException;
import java.nio.file.*;
import org.springframework.stereotype.Service;

@Service("filePersistenceManager")
public class FilePersistenceManager implements PersistenceManager {
	
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
