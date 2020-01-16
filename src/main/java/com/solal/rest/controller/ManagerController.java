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

import com.solal.entity.Advisor;
import com.solal.entity.Mechanic;
import com.solal.entity.Storekeeper;
import com.solal.entity.WorkCard;
import com.solal.rest.ClientSession;
import com.solal.rest.ex.InvalidTokenException;
import com.solal.service.ManagerService;

@RestController
@RequestMapping("/api")
public class ManagerController {

	// Field
	private Map<String, ClientSession> tokensMap;

	/**
	 * Constructor
	 * 
	 * @param tokensMap
	 */
	@Autowired
	public ManagerController(@Qualifier("tokens") Map<String, ClientSession> tokensMap) {
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

	@GetMapping("/managers/allMechanics/{token}")
	public ResponseEntity<List<Mechanic>> getAllMechanics(@PathVariable String token) throws InvalidTokenException {
		ClientSession session = getSession(token);
		if (session == null) {
			throw new InvalidTokenException("Invalid token");
		}
		ManagerService service = (ManagerService) session.getService();
		final List<Mechanic> allMechanics = service.getAllMechanics();

		if (allMechanics.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(allMechanics);
	}

	@GetMapping("/managers/getWorkCardById/{token}")
	public ResponseEntity<WorkCard> getWorkCardById(@PathVariable String token, @RequestParam long id)
			throws InvalidTokenException {
		ClientSession session = getSession(token);
		if (session == null) {
			throw new InvalidTokenException("Invalid token");
		}
		ManagerService service = (ManagerService) session.getService();
		final WorkCard workCard = service.getWorkCardById(id);

		if (workCard == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(workCard);
	}

	@PostMapping("/managers/createMechanic/{token}")
	public ResponseEntity<Mechanic> createMechanic(@PathVariable String token, @RequestBody Mechanic mechanic)
			throws InvalidTokenException {
		ClientSession session = getSession(token);
		if (session == null) {
			throw new InvalidTokenException("Invalid token");
		}
		ManagerService service = (ManagerService) session.getService();
		return ResponseEntity.ok(service.createMechanic(mechanic));
	}

	@PostMapping("/managers/createStoreKeeper/{token}")
	public ResponseEntity<Storekeeper> createStoreKeeper(@PathVariable String token,
			@RequestBody Storekeeper storekeeper) throws InvalidTokenException {
		ClientSession session = getSession(token);
		if (session == null) {
			throw new InvalidTokenException("Invalid token");
		}
		ManagerService service = (ManagerService) session.getService();
		return ResponseEntity.ok(service.createStoreKeeper(storekeeper));
	}

	@PostMapping("/managers/createAdvisor/{token}")
	public ResponseEntity<Advisor> createAdvisor(@PathVariable String token, @RequestBody Advisor advisor)
			throws InvalidTokenException {
		ClientSession session = getSession(token);
		if (session == null) {
			throw new InvalidTokenException("Invalid token");
		}
		ManagerService service = (ManagerService) session.getService();
		return ResponseEntity.ok(service.createAdvisor(advisor));
	}

}
