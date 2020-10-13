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

/**
 * This class is the class in which are to implement all the functions which are
 * declared in the "MechanicService" interface
 * 
 * @author Solal Arroues
 *
 */
@Service
public class MechanicServiceImpl implements MechanicService {

	// Fields
	private long mechanicId;
	private MechanicRepository mechanicRepository;
	private WorkCardRepository workCardRepository;
	private PartRepository partRepository;

	/**
	 * Constructor
	 * 
	 * @param mechanicRepository
	 * @param workCardRepository
	 * @param partRepository
	 */
	@Autowired
	public MechanicServiceImpl(MechanicRepository mechanicRepository, WorkCardRepository workCardRepository,
			PartRepository partRepository) {
		this.mechanicRepository = mechanicRepository;
		this.workCardRepository = workCardRepository;
		this.partRepository = partRepository;
	}

	/**
	 * This function is used in the LoginSystem class in the mechanicLogin function
	 * in order to define the id of the mechanic who has connected and to grant him
	 * access to the functions he has the right to use
	 * 
	 * @param mechanicId
	 */
	public void setMechanicId(long mechanicId) {
		this.mechanicId = mechanicId;
	}

	/**
	 * This function returns the mechanic to us by id
	 * 
	 * @return Mechanic
	 */
	public Mechanic getMechanic() {
		return mechanicRepository.findById(mechanicId).orElse(null);
	}

	/**
	 * This function saves the list of pieces received as a parameter in the table
	 * of pieces and to do that it uses a loop which will process each piece one by
	 * one: first it uses the setWorkCard () function to put the work card on which
	 * the mechanic is working at the moment, and then it sets its id to 0 to be
	 * sure that it is not an existing part
	 * 
	 * @param List<Part>
	 * @return List<Part>
	 */
	@Override
	public List<Part> addParts(List<Part> parts) {
		for (Part part : parts) {
			part.setWorkCard(getMechanic().getWorkCard());
			part.setId(0);
			partRepository.save(part);
		}
		return parts;
	}

	/**
	 * This function is used to change the mechanic's work card. first it checks
	 * that the new card exists in the database, then it checks that the job has not
	 * already been finished on this card then hopefully it changes the mechanic
	 * card and then it uses the private function setStartWork () to set the start
	 * time for work
	 * 
	 * @param String plateNumber
	 * @throws WorkCardNotExistsException - "This work card is not exists"
	 * @throws TheWorkIsFinishedException - "You cannot work on a finished job"
	 * @return WorkCard
	 */
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

	/**
	 * this function defines when the job is finished and then sets the mechanic's
	 * work card to null that he is not working on anything
	 * 
	 * @return String - "Work finished on " + plateNumber
	 */
	@Override
	public String setEndWork() {
		Mechanic mechanic = getMechanic();
		String plateNumber = mechanic.getWorkCard().getPlateNumber();

		mechanic.getWorkCard().setEndWork(System.currentTimeMillis());
		setWorkCardOfMechanicToNull(mechanic);

		return "Work finished on " + plateNumber;
	}

	/**
	 * 
	 * @param mechanic
	 */
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