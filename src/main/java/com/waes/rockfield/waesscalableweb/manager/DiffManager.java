package com.waes.rockfield.waesscalableweb.manager;

import com.waes.rockfield.waesscalableweb.exception.MissingFileException;
import com.waes.rockfield.waesscalableweb.model.DiffResponse;
import java.io.IOException;

public interface DiffManager {
	
	DiffResponse compare(String id) throws MissingFileException, IOException;
}
