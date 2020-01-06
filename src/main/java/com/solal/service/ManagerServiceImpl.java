package com.solal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.solal.entity.Mechanic;
import com.solal.entity.WorkCard;
import com.solal.repository.MechanicRepository;
import com.solal.repository.WorkCardRepository;

@Service
public class ManagerServiceImpl implements ManagerService {

	private long managerId;

	private WorkCardRepository workCardRepository;
	private MechanicRepository mechanicRepository;

	public ManagerServiceImpl(WorkCardRepository workCardRepository, MechanicRepository mechanicRepository) {
		this.workCardRepository = workCardRepository;
		this.mechanicRepository = mechanicRepository;
	}

	public void setManagerId(long managerId) {
		this.managerId = managerId;
	}

	@Override
	public List<WorkCard> getAllWorkCards() {
		return workCardRepository.findAll();
	}

	@Override
	public List<Mechanic> getAllMechanics() {
		return mechanicRepository.findAll();
	}

	@Override
	public WorkCard getWorkCardById(long id) {
		return workCardRepository.findById(id).orElse(null);
	}

}
