package com.waes.rockfield.waesscalableweb.manager;

import com.waes.rockfield.waesscalableweb.exception.MissingFileException;
import com.waes.rockfield.waesscalableweb.model.DiffResponse;
import com.waes.rockfield.waesscalableweb.model.DiffStatus;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import org.springframework.stereotype.Service;

@Service("diffManager")
public class DiffManagerImpl implements DiffManager {
	
	static final String DIFFERENT_SIZE_MESSAGE = "Left and right files have diffent sizes.";

	public DiffResponse compare(String id) throws MissingFileException, IOException {
		validateFileExistence(id);
		if (theyHaveDifferentSizes(id)) {
			return new DiffResponse(DiffStatus.DIFFERENT, DIFFERENT_SIZE_MESSAGE);
		}
		LinkedList<Integer> diffList = findDifferentBytes(id);
		if (diffList.isEmpty()) {
			return new DiffResponse(DiffStatus.EQUAL, "Congratulations!");
		}
		return new DiffResponse(DiffStatus.DIFFERENT, formatDifferentBytesMessage(diffList));
	}

	private LinkedList<Integer> findDifferentBytes(String id) throws IOException {
		byte[] left = Files.readAllBytes(Paths.get(id, "left"));
		byte[] right = Files.readAllBytes(Paths.get(id, "right"));
		LinkedList<Integer> diffList = new LinkedList();
		for (int i = 0; i < left.length; i++) {
			if (left[i] != right[i]) {
				diffList.add(i);
			}
		}
		return diffList;
	}

	private void validateFileExistence(String id) throws MissingFileException {
		if (idDoesntExist(id)) {
			throw new MissingFileException("There are no files for id " + id);
		}
		if (thereIsNoFile("left", id)) {
			throw new MissingFileException("Missing left file for id " + id);
		}
		if (thereIsNoFile("right", id)) {
			throw new MissingFileException("Missing right file for id " + id);
		}
	}

	private boolean idDoesntExist(String id) {
		File dir = Paths.get(id).toFile();
		return !dir.exists() || !dir.isDirectory();
	}

	private boolean thereIsNoFile(String fileName, String id) {
		return !Paths.get(id, fileName).toFile().exists();
	}

	private boolean theyHaveDifferentSizes(String id) {
		long leftSize = Paths.get(id, "left").toFile().length();
		long rightSize = Paths.get(id, "right").toFile().length();
		return !(leftSize == rightSize);
	}

	private String formatDifferentBytesMessage(LinkedList<Integer> diffList) {
		String message = "Left and Right files are different. "
				+ "This is the index list that identifies the "
				+ "positions in the byte array where they are different: ";
		boolean first = true;
		for (Integer index : diffList) {
			if (!first) {
				message += ", ";
			} else {
				first = false;
			}
			message += index;
		}
		message += ".";
		return message;
	}
}
