package com.solal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solal.entity.Mechanic;
import com.solal.entity.Part;
import com.solal.entity.WorkCard;
import com.solal.repository.MechanicRepository;
import com.solal.repository.PartRepository;
import com.solal.repository.WorkCardRepository;
import com.solal.rest.ex.WorkCardNotExistsException;

@Service
public class MechanicServiceImpl implements MechanicService {

	private long mechanicId;
	private MechanicRepository mechanicRepository;
	private WorkCardRepository workCardRepository;
	private PartRepository partRepository;

	public void setMechanicId(long mechanicId) {
		this.mechanicId = mechanicId;
	}

	public Mechanic getMechanic() {
		return mechanicRepository.findById(mechanicId).orElse(null);
	}

	@Autowired
	public MechanicServiceImpl(MechanicRepository mechanicRepository, WorkCardRepository workCardRepository,
			PartRepository partRepository) {
		this.mechanicRepository = mechanicRepository;
		this.workCardRepository = workCardRepository;
		this.partRepository = partRepository;
	}

	public List<Part> addParts(List<Part> parts) {
		for (Part part : parts) {
			part.setWorkCard(getMechanic().getWorkCard());
			part.setId(0);
			partRepository.save(part);
		}
		return parts;
	}

	@Override
	public WorkCard setWorkCard(String plateNumber) throws WorkCardNotExistsException {
		if (!workCardIsExists(plateNumber)) {
			throw new WorkCardNotExistsException("This work card is not exists");
		}
		Mechanic mechanic = getMechanic();
		WorkCard workCard = workCardRepository.findByPlateNumber(plateNumber).orElse(null);

		mechanic.setWorkCard(workCard);
		mechanicRepository.save(mechanic);

		return workCard;
	}

	private boolean workCardIsExists(String plateNumber) {
		final WorkCard workCard = workCardRepository.findByPlateNumber(plateNumber).orElse(null);
		return workCard != null;
	}
}
