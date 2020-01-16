package com.solal.service;

import java.util.List;

import com.solal.entity.Part;
import com.solal.entity.WorkCard;

public interface AdvisorService extends Service {

	List<WorkCard> getAllWorkCards();

	List<Part> getAllWorkCardParts(long workCardId);

	WorkCard addWorkCard(WorkCard workCard);

}
