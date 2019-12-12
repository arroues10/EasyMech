package com.solal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.solal.entity.Part;
import com.solal.repository.PartRepository;

@Service
public class StoreKeeperServiceImpl implements StoreKeeperService {
	private long storekeeperId;

	private PartRepository partRepository;

	public void setStoreKeeperId(long storekeeperId) {
		this.storekeeperId = storekeeperId;
	}

	@Override
	public List<Part> getAllWorkCardParts(long workCardId) {
		return partRepository.findAllByWorkCardId(workCardId);
	}

}
