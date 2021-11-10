package com.solal.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.solal.entity.Mechanic;
import com.solal.repository.MechanicRepository;
import com.solal.rest.ex.InvalidLoginException;
import com.solal.service.MechanicServiceImpl;

/**
 * It is in this class that I create the login function whose login controller
 * will use
 * 
 * @author Solal Arroues
 *
 */
@Service
public class LoginSystem {

	// Fields
	private ApplicationContext context;
	private MechanicRepository mechanicRepository;

	// Constructor
	@Autowired
	public LoginSystem(ApplicationContext context, MechanicRepository mechanicRepository) {
		this.context = context;
		this.mechanicRepository = mechanicRepository;
	}

	/**
	 * This function uses the switch box system to dispatch connections for Admin
	 * Company and Customer
	 * 
	 * @param name
	 * @param password
	 * @return ClientSession
	 * @throws InvalidLoginException
	 */
	public ClientSession login(String name, String password) throws InvalidLoginException {
		return mechanicLogin(name, password);
	}

	private ClientSession mechanicLogin(String name, String password) throws InvalidLoginException {
		Mechanic mechanic = mechanicRepository.findByNameAndPassword(name, password);
		if (mechanic == null) {
			throw new InvalidLoginException("Name or password are invalid.");
		}
		MechanicServiceImpl service = context.getBean(MechanicServiceImpl.class);
		service.setMechanicId(mechanic.getId());

		ClientSession session = context.getBean(ClientSession.class);
		session.setService(service);
		session.accessed();

		return session;
	}

}
