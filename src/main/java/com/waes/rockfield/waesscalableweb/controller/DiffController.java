package com.waes.rockfield.waesscalableweb.controller;

import com.waes.rockfield.waesscalableweb.manager.*;
import com.waes.rockfield.waesscalableweb.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/diff")
public class DiffController {

	@Autowired
	private PersistenceManager saver;

	@Autowired
	private Base64Manager decoder;

	@Autowired
	private DiffManager diff;

	@RequestMapping(value = "/{id}/left",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ReceivedResponse receiveLeft(@PathVariable String id, @RequestBody Base64Json info) {
		return DecodeAndSaveData(info, id, "left");
	}

	@RequestMapping(value = "/{id}/right",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ReceivedResponse receiveRight(@PathVariable String id, @RequestBody Base64Json info) {
		return DecodeAndSaveData(info, id, "right");
	}

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
