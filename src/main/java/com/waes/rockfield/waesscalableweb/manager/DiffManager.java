package com.waes.rockfield.waesscalableweb.manager;

import com.waes.rockfield.waesscalableweb.exception.MissingFileException;
import com.waes.rockfield.waesscalableweb.model.DiffResponse;
import java.io.IOException;

/**
 * Interface with the Difference Manager method signatures. It may be
 * implemented using different types of comparators.
 * * 
 * @author lroca
 */
public interface DiffManager {

	DiffResponse compare(String id) throws MissingFileException, IOException;
}
