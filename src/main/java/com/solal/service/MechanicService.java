package com.solal.service;

import java.util.List;

import com.solal.entity.Part;
import com.solal.entity.WorkCard;
import com.solal.rest.ex.TheWorkIsFinishedException;
import com.solal.rest.ex.WorkCardNotExistsException;

public interface MechanicService extends Service {

	List<Part> addParts(List<Part> parts);

	WorkCard setWorkCard(String plateNumber) throws WorkCardNotExistsException, TheWorkIsFinishedException;

	String setEndWork();

	List<WorkCard> getEndWorkCards();

}
