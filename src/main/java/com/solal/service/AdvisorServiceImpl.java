package com.solal.service;

import org.springframework.stereotype.Service;

import com.solal.repository.MechanicRepository;
import com.solal.repository.WorkCardRepository;

@Service
public class AdvisorServiceImpl implements AdvisorService {

	private long advisorId;

	private WorkCardRepository workCardRepository;
	private MechanicRepository mechanicRepository;

	public AdvisorServiceImpl(WorkCardRepository workCardRepository, MechanicRepository mechanicRepository) {
		this.workCardRepository = workCardRepository;
		this.mechanicRepository = mechanicRepository;
	}

	public void setAdvisorId(long advisorId) {
		this.advisorId = advisorId;
	}
}
