package com.solal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solal.entity.Part;
import com.solal.entity.WorkCard;
import com.solal.repository.PartRepository;
import com.solal.repository.WorkCardRepository;

@Service
public class StoreKeeperServiceImpl implements StoreKeeperService {

	private long storekeeperId;

	private PartRepository partRepository;
	private WorkCardRepository workCardRepository;

	@Autowired
	public StoreKeeperServiceImpl(PartRepository partRepository, WorkCardRepository workCardRepository) {
		this.partRepository = partRepository;
		this.workCardRepository = workCardRepository;
	}

	public void setStoreKeeperId(long storekeeperId) {
		this.storekeeperId = storekeeperId;
	}

	@Override
	public List<Part> getAllWorkCardParts(long workCardId) {
		return partRepository.findAllByWorkCardId(workCardId);
	}

	@Override
	public List<Part> getAllParts() {
		return partRepository.findAll();
	}

	@Override
	public List<WorkCard> getAllWorkCards() {
		return workCardRepository.findAll();
	}

}
