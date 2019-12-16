package com.solal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solal.entity.Mechanic;
import com.solal.entity.Part;
import com.solal.repository.MechanicRepository;
import com.solal.repository.PartRepository;
import com.solal.repository.WorkCardRepository;

@Service
public class MechanicServiceImpl implements MechanicService {

	private long mechanicId;
	private MechanicRepository mechanicRepository;
	private WorkCardRepository workCardRepository;
	private PartRepository partRepository;

	public void setMechanicId(long mechanicId) {
		this.mechanicId = mechanicId;
	}

	@Autowired
	public MechanicServiceImpl(MechanicRepository mechanicRepository, WorkCardRepository workCardRepository,
			PartRepository partRepository) {
		this.mechanicRepository = mechanicRepository;
		this.workCardRepository = workCardRepository;
		this.partRepository = partRepository;
	}

	public Mechanic getMechanic() {
		return mechanicRepository.findById(mechanicId).orElse(null);
	}

	public List<Part> addParts(List<Part> parts) {
		for (Part part : parts) {
			part.setWorkCard(workCardRepository.findById(getMechanic().getWorkCard().getId()).orElse(null));
			part.setId(0);
			partRepository.save(part);
		}
		return parts;
	}

}
