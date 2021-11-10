package com.solal.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solal.rest.ClientSession;

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

}
