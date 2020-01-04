package com.solal.rest.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.solal.entity.Part;
import com.solal.entity.WorkCard;
import com.solal.rest.ClientSession;
import com.solal.rest.ex.InvalidTokenException;
import com.solal.service.StoreKeeperService;

@RestController
@RequestMapping("/api")
public class StoreKeeperController {

	private Map<String, ClientSession> tokensMap;

	@Autowired
	public StoreKeeperController(@Qualifier("tokens") Map<String, ClientSession> tokensMap) {
		this.tokensMap = tokensMap;
	}

	private ClientSession getSession(String token) {
		return tokensMap.get(token);
	}

	@GetMapping("/storeKeepers/allWorkCardParts/{token}")
	public ResponseEntity<List<Part>> getAllWorkCardParts(@PathVariable String token, @RequestParam long workCardId)
			throws InvalidTokenException {
		ClientSession session = getSession(token);
		if (session == null) {
			throw new InvalidTokenException("Invalid token");
		}
		StoreKeeperService service = (StoreKeeperService) session.getService();
		final List<Part> allWorkCardParts = service.getAllWorkCardParts(workCardId);

		if (allWorkCardParts.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(allWorkCardParts);
	}

	@GetMapping("/storeKeepers/allWorkCards/{token}")
	public ResponseEntity<List<WorkCard>> getAllWorkCards(@PathVariable String token) throws InvalidTokenException {
		ClientSession session = getSession(token);
		if (session == null) {
			throw new InvalidTokenException("Invalid token");
		}
		StoreKeeperService service = (StoreKeeperService) session.getService();
		final List<WorkCard> allWorkCards = service.getAllWorkCards();

		if (allWorkCards.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(allWorkCards);
	}

	@GetMapping("/storeKeepers/allParts/{token}")
	public ResponseEntity<List<Part>> getAllParts(@PathVariable String token) throws InvalidTokenException {
		ClientSession session = getSession(token);
		if (session == null) {
			throw new InvalidTokenException("Invalid token");
		}
		StoreKeeperService service = (StoreKeeperService) session.getService();
		final List<Part> allParts = service.getAllParts();

		if (allParts.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(allParts);
	}

	@GetMapping("/storeKeepers/parts/{token}")
	public ResponseEntity<Part> getPart(@PathVariable String token, @RequestParam long id)
			throws InvalidTokenException {
		ClientSession session = getSession(token);
		if (session == null) {
			throw new InvalidTokenException("Invalid token");
		}
		StoreKeeperService service = (StoreKeeperService) session.getService();
		return ResponseEntity.ok(service.getPart(id));
	}

	@PostMapping("/storeKeepers/setPrices/{token}")
	public ResponseEntity<Part> setPrices(@PathVariable String token, @RequestParam long partId,
			@RequestParam long originalPrice, @RequestParam long replacementPrice) throws InvalidTokenException {
		ClientSession session = getSession(token);
		if (session == null) {
			throw new InvalidTokenException("Invalid token");
		}
		StoreKeeperService service = (StoreKeeperService) session.getService();
		final Part part = service.setPrices(partId, originalPrice, replacementPrice);

		return ResponseEntity.ok(part);
	}

}
