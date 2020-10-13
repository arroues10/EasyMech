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
import com.solal.service.AdvisorService;

@RestController
@RequestMapping("/api")
public class AdvisorController {

	// Field
	private Map<String, ClientSession> tokensMap;

	/**
	 * Constructor
	 * 
	 * @param tokensMap
	 */
	@Autowired
	public AdvisorController(@Qualifier("tokens") Map<String, ClientSession> tokensMap) {
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
	 * This function returns us the list of all the work cards of the system
	 * 
	 * @param token
	 * @return ResponseEntity<List<WorkCard>>
	 * @throws InvalidTokenException
	 */
	@GetMapping("/advisors/allWorkCards/{token}")
	public ResponseEntity<List<WorkCard>> getAllWorkCards(@PathVariable String token) throws InvalidTokenException {
		ClientSession session = getSession(token);
		if (session == null) {
			throw new InvalidTokenException("Invalid token");
		}
		AdvisorService service = (AdvisorService) session.getService();
		final List<WorkCard> allWorkCards = service.getAllWorkCards();

		if (allWorkCards.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(allWorkCards);
	}

	/**
	 * This function returns us the list of all the parts to be replaced belonging
	 * to the work card given as a parameter
	 * 
	 * @param token
	 * @param workCardId
	 * @return ResponseEntity<List<Part>>
	 * @throws InvalidTokenException
	 */
	@GetMapping("/advisors/allWorkCardParts/{token}")
	public ResponseEntity<List<Part>> getAllWorkCardParts(@PathVariable String token, @RequestParam long workCardId)
			throws InvalidTokenException {
		ClientSession session = getSession(token);
		if (session == null) {
			throw new InvalidTokenException("Invalid token");
		}
		AdvisorService service = (AdvisorService) session.getService();
		final List<Part> allWorkCardParts = service.getAllWorkCardParts(workCardId);

		if (allWorkCardParts.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(allWorkCardParts);
	}

	/**
	 * This function is a "post" type function which allows the advisor to upload a
	 * new work card to the system
	 * 
	 * @param token
	 * @param workCard
	 * @return ResponseEntity<WorkCard>
	 * @throws InvalidTokenException
	 */
	@PostMapping("/advisors/addWorkCard/{token}")
	public ResponseEntity<WorkCard> addWorkCard(@PathVariable String token, @RequestBody WorkCard workCard)
			throws InvalidTokenException {
		ClientSession session = getSession(token);
		if (session == null) {
			throw new InvalidTokenException("Invalid token");
		}
		AdvisorService service = (AdvisorService) session.getService();

		return ResponseEntity.ok(service.addWorkCard(workCard));
	}

}
