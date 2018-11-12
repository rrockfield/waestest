package com.waes.rockfield.waesscalableweb.controller;

import com.waes.rockfield.waesscalableweb.manager.*;
import com.waes.rockfield.waesscalableweb.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Main controller for handling diff REST services
 *
 * @author lroca
 */
@RestController
@RequestMapping("/v1/diff")
public class DiffController {

	@Autowired
	private PersistenceManager saver;

	@Autowired
	private Base64Manager decoder;

	@Autowired
	private DiffManager diff;

	/**
	 * Receives a JSON file with a Base64 encoded data and an identification to
	 * make the comparison. The decoded file is stored on the left side of the
	 * comparator.
	 *
	 * @param id Identification for the comparison, it should match the other
	 * side
	 * @param info JSON object containing the Base64 encoded data
	 * @return a ReceivedResponse JSON object with the OK or ERROR status and a
	 * message
	 */
	@RequestMapping(value = "/{id}/left",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ReceivedResponse receiveLeft(@PathVariable String id, @RequestBody Base64Json info) {
		return DecodeAndSaveData(info, id, "left");
	}

	/**
	 * Receives a JSON file with a Base64 encoded data and an identification to
	 * make the comparison. The decoded file is stored on the right side of the
	 * comparator.
	 *
	 * @param id Identification for the comparison, it should match the other
	 * side
	 * @param info JSON object containing the Base64 encoded data
	 * @return a ReceivedResponse JSON object with the OK or ERROR status and a
	 * message
	 */
	@RequestMapping(value = "/{id}/right",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ReceivedResponse receiveRight(@PathVariable String id, @RequestBody Base64Json info) {
		return DecodeAndSaveData(info, id, "right");
	}

	/**
	 * Executes the comparison between both files on each side matching the same
	 * id
	 *
	 * @param id Identification to locate left and right files.
	 * @return A DiffResponse JSON object with the EQUAL, DIFFERENT or ERROR.
	 * status and a message.
	 */
	@RequestMapping(value = "/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public DiffResponse executeDiff(@PathVariable String id) {
		try {
			return diff.compare(id);
		} catch (Exception e) {
			return new DiffResponse(DiffStatus.ERROR, e.getMessage());
		}
	}

	private ReceivedResponse DecodeAndSaveData(Base64Json info, String id, String side) {
		try {
			byte[] decoded = decoder.decode(info.getEncoded());
			saver.save(id, side, decoded);
			return new ReceivedResponse(ReceivedStatus.OK);
		} catch (Exception e) {
			return new ReceivedResponse(ReceivedStatus.ERROR, e.getMessage());
		}
	}
}
