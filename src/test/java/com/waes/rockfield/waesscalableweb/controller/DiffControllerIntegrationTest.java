package com.waes.rockfield.waesscalableweb.controller;

import com.waes.rockfield.waesscalableweb.Application;
import com.waes.rockfield.waesscalableweb.model.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class DiffControllerIntegrationTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testWhenLeftAndRightAreEqual() throws Exception {
		String baseUrl = getBaseURL("HappyPath");
		Base64Json encoded = new Base64Json("VGhlcmUgaXMgbm8gc3Bvb24=");
		ResponseEntity<ReceivedResponse> responseLeft = restTemplate.postForEntity(baseUrl + "/left", encoded, ReceivedResponse.class);
		Assert.assertEquals(ReceivedStatus.OK, responseLeft.getBody().getStatus());
		ResponseEntity<ReceivedResponse> responseRight = restTemplate.postForEntity(baseUrl + "/right", encoded, ReceivedResponse.class);
		Assert.assertEquals(ReceivedStatus.OK, responseRight.getBody().getStatus());
		ResponseEntity<DiffResponse> diffResponse = restTemplate.getForEntity(baseUrl, DiffResponse.class);
		Assert.assertEquals(DiffStatus.EQUAL, diffResponse.getBody().getStatus());
	}

	@Test
	public void testWhenLeftAndRightAreDifferentButSameSize() throws Exception {
		String baseUrl = getBaseURL("DifferentBytes");
		Base64Json encodedLeft = new Base64Json("OTE4MTY1OTAgNzgxMjg1MDcgNTIw");
		ResponseEntity<ReceivedResponse> responseLeft = restTemplate.postForEntity(baseUrl + "/left", encodedLeft, ReceivedResponse.class);
		Assert.assertEquals(ReceivedStatus.OK, responseLeft.getBody().getStatus());
		Base64Json encodedRight = new Base64Json("ODE5MTY1OTAgNzgxMjg1MDcgNzQx");
		ResponseEntity<ReceivedResponse> responseRight = restTemplate.postForEntity(baseUrl + "/right", encodedRight, ReceivedResponse.class);
		Assert.assertEquals(ReceivedStatus.OK, responseRight.getBody().getStatus());
		ResponseEntity<DiffResponse> diffResponse = restTemplate.getForEntity(baseUrl, DiffResponse.class);
		Assert.assertEquals(DiffStatus.DIFFERENT, diffResponse.getBody().getStatus());
	}

	@Test
	public void testWhenLeftAndRightAreDifferentSize() throws Exception {
		String baseUrl = getBaseURL("DifferentSizes");
		Base64Json encodedLeft = new Base64Json("WW91IGtub3csIEx1aXMgaXMgb25lIHRoZSBmaW5lc3QgZGV2ZWxvcGVycyBpbiB0aGUgd29ybGQu");
		ResponseEntity<ReceivedResponse> responseLeft = restTemplate.postForEntity(baseUrl + "/left", encodedLeft, ReceivedResponse.class);
		Assert.assertEquals(ReceivedStatus.OK, responseLeft.getBody().getStatus());
		Base64Json encodedRight = new Base64Json("VGhlcmUgYXJlIGZldyBjaGFuY2VzIGluIGxpZmUgd2hlbiB5b3UgYXJlIGx1Y2t5IGVub3VnaCB0byBmaW5kIHN1Y2ggYSBncmVhdCBkZXZlbG9wZXIgYXMgTHVpcy4=");
		ResponseEntity<ReceivedResponse> responseRight = restTemplate.postForEntity(baseUrl + "/right", encodedRight, ReceivedResponse.class);
		Assert.assertEquals(ReceivedStatus.OK, responseRight.getBody().getStatus());
		ResponseEntity<DiffResponse> diffResponse = restTemplate.getForEntity(baseUrl, DiffResponse.class);
		Assert.assertEquals(DiffStatus.DIFFERENT, diffResponse.getBody().getStatus());
	}

	@Test
	public void testWhenThereIsOnlyOneFile() throws Exception {
		String baseUrl = getBaseURL("NoLeft");
		Base64Json encoded = new Base64Json("WW91IGtub3csIEx1aXMgaXMgb25lIHRoZSBmaW5lc3QgZGV2ZWxvcGVycyBpbiB0aGUgd29ybGQu");
		ResponseEntity<ReceivedResponse> responseRight = restTemplate.postForEntity(baseUrl + "/right", encoded, ReceivedResponse.class);
		Assert.assertEquals(ReceivedStatus.OK, responseRight.getBody().getStatus());
		ResponseEntity<DiffResponse> diffResponse = restTemplate.getForEntity(baseUrl, DiffResponse.class);
		Assert.assertEquals(DiffStatus.ERROR, diffResponse.getBody().getStatus());
	}

	@Test
	public void testAnInvalidBase64Encoding() throws Exception {
		String baseUrl = getBaseURL("WrongEncodedString");
		Base64Json encoded = new Base64Json("ImPrettySureThisIsGoingToFail");
		ResponseEntity<ReceivedResponse> response = restTemplate.postForEntity(baseUrl + "/right", encoded, ReceivedResponse.class);
		Assert.assertEquals(ReceivedStatus.ERROR, response.getBody().getStatus());
	}

	@Test
	public void testWhenThereAreNoFiles() throws Exception {
		String baseUrl = getBaseURL("MissingFiles");
		ResponseEntity<DiffResponse> diffResponse = restTemplate.getForEntity(baseUrl, DiffResponse.class);
		Assert.assertEquals(DiffStatus.ERROR, diffResponse.getBody().getStatus());
	}

	private String getBaseURL(String id) {
		return "http://localhost:" + port + "/v1/diff/" + id;
	}
}
