package com.solal.rest.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solal.rest.ClientSession;

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

}
