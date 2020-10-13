package com.solal.rest.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.solal.entity.Part;
import com.solal.rest.ClientSession;
import com.solal.rest.ex.InvalidTokenException;
import com.solal.rest.ex.TheWorkIsFinishedException;
import com.solal.rest.ex.WorkCardNotExistsException;
import com.solal.service.MechanicService;

@RestController
@RequestMapping("/api")
public class MechanicController {

	// Field
	private Map<String, ClientSession> tokensMap;

	/**
	 * Constructor
	 * 
	 * @param tokensMap
	 */
	@Autowired
	public MechanicController(@Qualifier("tokens") Map<String, ClientSession> tokensMap) {
		this.tokensMap = tokensMap;
	}

	/**
	 * This function returns us the session which corresponds to the token received
	 * 
	 * @param token
	 * @return ClientSession
	 */
	private ClientSession getSession(String token) {
		return tokensMap.get(token);
	}

	/**
	 * 
	 * This function is a "post" type function which allows the mechanic to upload
	 * the parts that must be replaced for his work card
	 * 
	 * @param token
	 * @param List<Part> parts
	 * @return ResponseEntity<List<Part>>
	 * @throws InvalidTokenException
	 */
	@PostMapping("/mechanics/parts/{token}")
	public ResponseEntity<List<Part>> addParts(@PathVariable String token, @RequestBody List<Part> parts)
			throws InvalidTokenException {
		ClientSession session = getSession(token);
		if (session == null) {
			throw new InvalidTokenException("Invalid token");
		}
		MechanicService service = (MechanicService) session.getService();
		service.addParts(parts);
		return ResponseEntity.ok(parts);
	}

	/**
	 * This function is a "post" type function which allows the mechanic to start
	 * working on the work card given as a parameter
	 * 
	 * @param token
	 * @param plateNumber - string
	 * @return ResponseEntity<String> - the plate number
	 * @throws InvalidTokenException
	 * @throws WorkCardNotExistsException - the plate number in parameter is not
	 *                                    entered in the database
	 * @throws TheWorkIsFinishedException - if the job on the work card has already
	 *                                    been completed before
	 */
	@PostMapping("/mechanics/setWorkCard/{token}")
	public ResponseEntity<String> setWorkCard(@PathVariable String token, @RequestParam String plateNumber)
			throws InvalidTokenException, WorkCardNotExistsException, TheWorkIsFinishedException {
		ClientSession session = getSession(token);
		if (session == null) {
			throw new InvalidTokenException("Invalid token");
		}
		MechanicService service = (MechanicService) session.getService();
		service.setWorkCard(plateNumber);
		return ResponseEntity.ok(plateNumber);
	}

	/**
	 * This function when called fixes the moment when the job has been finished on
	 * the work card
	 * 
	 * @param token
	 * @return ResponseEntity<String> - "Work finished on " + plateNumber
	 * @throws InvalidTokenException
	 * @throws WorkCardNotExistsException - the plate number in parameter is not
	 *                                    entered in the database
	 */
	@PostMapping("/mechanics/setEndWork/{token}")
	public ResponseEntity<String> setEndWork(@PathVariable String token)
			throws InvalidTokenException, WorkCardNotExistsException {
		ClientSession session = getSession(token);
		if (session == null) {
			throw new InvalidTokenException("Invalid token");
		}
		MechanicService service = (MechanicService) session.getService();
		return ResponseEntity.ok(service.setEndWork());
	}

}
