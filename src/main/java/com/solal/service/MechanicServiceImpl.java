package com.solal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solal.entity.Mechanic;
import com.solal.repository.MechanicRepository;

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

	/**
	 * Constructor
	 * 
	 * @param mechanicRepository
	 * @param workCardRepository
	 * @param partRepository
	 */
	@Autowired
	public MechanicServiceImpl(MechanicRepository mechanicRepository) {
		this.mechanicRepository = mechanicRepository;
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
}