package com.solal.service;

import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl implements ManagerService {
	private long mangerId;

	public void setManagerId(long mangerId) {
		this.mangerId = mangerId;
	}

}
