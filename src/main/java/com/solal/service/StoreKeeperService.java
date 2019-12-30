package com.solal.service;

import java.util.List;

import com.solal.entity.Part;
import com.solal.entity.WorkCard;

public interface StoreKeeperService extends Service {

	List<Part> getAllWorkCardParts(long workCardId);

	List<Part> getAllParts();

	List<WorkCard> getAllWorkCards();

}
