package com.solal.service;

import org.springframework.stereotype.Service;

@Service
public class MechanicServiceImpl implements MechanicService {
	private long mechanicId;

	public void setMechanicId(long mechanicId) {
		this.mechanicId = mechanicId;
	}

}
