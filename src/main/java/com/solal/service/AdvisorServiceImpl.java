package com.solal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.solal.entity.Part;
import com.solal.entity.WorkCard;
import com.solal.repository.MechanicRepository;
import com.solal.repository.PartRepository;
import com.solal.repository.WorkCardRepository;

@Service
public class AdvisorServiceImpl implements AdvisorService {

	private long advisorId;

	private WorkCardRepository workCardRepository;
	private MechanicRepository mechanicRepository;
	private PartRepository partRepository;

	public AdvisorServiceImpl(WorkCardRepository workCardRepository, MechanicRepository mechanicRepository,
			PartRepository partRepository) {
		this.workCardRepository = workCardRepository;
		this.mechanicRepository = mechanicRepository;
		this.partRepository = partRepository;
	}

	public void setAdvisorId(long advisorId) {
		this.advisorId = advisorId;
	}

	@Override
	public List<WorkCard> getAllWorkCards() {
		return workCardRepository.findAll();
	}

	@Override
	public List<Part> getAllWorkCardParts(long workCardId) {
		return partRepository.findAllByWorkCardId(workCardId);
	}

	@Override
	public WorkCard addWorkCard(WorkCard workCard) {
		workCard.setId(0);
		return workCardRepository.save(workCard);
	}
}
