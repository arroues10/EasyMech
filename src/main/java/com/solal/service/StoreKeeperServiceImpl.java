package com.solal.service;

import org.springframework.stereotype.Service;

@Service
public class StoreKeeperServiceImpl implements StoreKeeperService {
	private long storekeeperId;
	
	public void setStoreKeeperId(long storekeeperId) {
		this.storekeeperId = storekeeperId;
	}

}
