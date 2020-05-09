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
import com.solal.rest.ex.TheWorkIsFinishedException;
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
	public WorkCard setWorkCard(String plateNumber) throws WorkCardNotExistsException, TheWorkIsFinishedException {
		if (!workCardIsExists(plateNumber)) {
			throw new WorkCardNotExistsException("This work card is not exists");
		}
		Mechanic mechanic = getMechanic();
		WorkCard workCard = workCardRepository.findByPlateNumber(plateNumber).orElse(null);

		if (theWorkIsFinished(workCard)) {
			throw new TheWorkIsFinishedException("You cannot work on a finished job");
		}

		mechanic.setWorkCard(workCard);
		mechanicRepository.save(mechanic);

		setStartWork(mechanic);

		return workCard;
	}

	@Override
	public String setEndWork() {
		Mechanic mechanic = getMechanic();
		String plateNumber = mechanic.getWorkCard().getPlateNumber();

		mechanic.getWorkCard().setEndWork(System.currentTimeMillis());
		setWorkCardOfMechanicToNull(mechanic);

		return "Work finished on " + plateNumber;
	}

	@Override
	public WorkCard addToEndWorkCards() {
		final WorkCard workCard = getMechanic().getWorkCard();
		workCard.setEndWorkMechanic
		(getMechanic());
		return workCardRepository.save(workCard);
	}

	@Override
	public List<WorkCard> getEndWorkCards() {
		return getMechanic().getEndWorkCards();
	}

	private void setStartWork(Mechanic mechanic) {
		WorkCard workCard = mechanic.getWorkCard();
		workCard.setStartWork(System.currentTimeMillis());
		workCardRepository.save(workCard);
	}

	private boolean workCardIsExists(String plateNumber) {
		final WorkCard workCard = workCardRepository.findByPlateNumber(plateNumber).orElse(null);
		return workCard != null;
	}

	private void setWorkCardOfMechanicToNull(Mechanic mechanic) {
		mechanic.setWorkCard(null);
		mechanicRepository.save(mechanic);
	}

	private boolean theWorkIsFinished(WorkCard workCard) {
		return workCard.getEndWork() != 0;
	}

}