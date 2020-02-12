package com.solal.rest.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.solal.entity.Part;
import com.solal.entity.WorkCard;
import com.solal.rest.ClientSession;
import com.solal.rest.ex.InvalidTokenException;
import com.solal.rest.ex.TheWorkIsFinishedException;
import com.solal.rest.ex.WorkCardNotExistsException;
import com.solal.service.MechanicService;

@RestController
@RequestMapping("/api")
public class MechanicController {

	private Map<String, ClientSession> tokensMap;

	@Autowired
	public MechanicController(@Qualifier("tokens") Map<String, ClientSession> tokensMap) {
		this.tokensMap = tokensMap;
	}

	private ClientSession getSession(String token) {
		return tokensMap.get(token);
	}

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

	@PostMapping("/mechanics/addToEndWorkCards/{token}")
	public ResponseEntity<WorkCard> addToEndWorkCards(@PathVariable String token)
			throws InvalidTokenException, WorkCardNotExistsException {
		ClientSession session = getSession(token);
		if (session == null) {
			throw new InvalidTokenException("Invalid token");
		}
		MechanicService service = (MechanicService) session.getService();
		return ResponseEntity.ok(service.addToEndWorkCards());
	}

	@GetMapping("/mechanics/getEndWorkCards/{token}")
	public ResponseEntity<List<WorkCard>> getEndWorkCards(@PathVariable String token)
			throws InvalidTokenException, WorkCardNotExistsException {
		ClientSession session = getSession(token);
		if (session == null) {
			throw new InvalidTokenException("Invalid token");
		}
		MechanicService service = (MechanicService) session.getService();
		return ResponseEntity.ok(service.getEndWorkCards());
		
	}

}
