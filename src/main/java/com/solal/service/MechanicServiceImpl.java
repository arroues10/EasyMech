package com.solal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.solal.entity.Mechanic;
import com.solal.entity.Part;
import com.solal.repository.MechanicRepository;

@Service
public class MechanicServiceImpl implements MechanicService {
	private long mechanicId;
	private MechanicRepository mechanicRepository;

	public void setMechanicId(long mechanicId) {
		this.mechanicId = mechanicId;
	}

	public Mechanic getMechanic() {
		return mechanicRepository.findById(mechanicId).orElse(null);
	}

	public void addParts(List<Part> parts) {
		Mechanic mechanic = getMechanic();

		mechanic.addPartsToWorkCard(parts);
		mechanicRepository.save(mechanic);
	}

}
