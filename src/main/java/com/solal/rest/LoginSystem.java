package com.solal.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.solal.entity.Manager;
import com.solal.entity.Mechanic;
import com.solal.entity.Storekeeper;
import com.solal.repository.ManagerRepository;
import com.solal.repository.MechanicRepository;
import com.solal.repository.StoreKeeperRepository;
import com.solal.rest.ex.InvalidLoginException;
import com.solal.service.ManagerServiceImpl;
import com.solal.service.MechanicServiceImpl;
import com.solal.service.StoreKeeperServiceImpl;

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
	private ManagerRepository managerRepository;
	private MechanicRepository mechanicRepository;
	private StoreKeeperRepository storeKeeperRepository;

	// Constructor
	@Autowired
	public LoginSystem(ApplicationContext context, ManagerRepository managerRepository,
			MechanicRepository mechanicRepository, StoreKeeperRepository storeKeeperRepository) {
		this.context = context;
		this.managerRepository = managerRepository;
		this.mechanicRepository = mechanicRepository;
		this.storeKeeperRepository = storeKeeperRepository;
	}

	/**
	 * This function uses the switch box system to dispatch connections for Admin
	 * Company and Customer
	 * 
	 * @param email
	 * @param password
	 * @param loginType
	 * @return ClientSession
	 * @throws InvalidLoginException
	 */
	public ClientSession login(String email, String password, String loginType) throws InvalidLoginException {
		switch (loginType) {
			case "MANAGER":
				return managerLogin(email, password);
			case "STOREKEEPER":
				return storekeeperLogin(email, password);
			case "MECHANIC":
				return mechanicLogin(email, password);
			default:
				throw new InvalidLoginException("Invalid loginType");
		}
	}

	private ClientSession storekeeperLogin(String name, String password) throws InvalidLoginException {
		Storekeeper storekeeper = storeKeeperRepository.findByNameAndPassword(name, password);
		if (storekeeper == null) {
			throw new InvalidLoginException("Name or password are invalid.");
		}
		StoreKeeperServiceImpl service = context.getBean(StoreKeeperServiceImpl.class);
		service.setStoreKeeperId(storekeeper.getId());

		ClientSession session = context.getBean(ClientSession.class);
		session.setService(service);
		session.accessed();

		return session;
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
	
	private ClientSession managerLogin(String name, String password) throws InvalidLoginException {
		Manager manager = managerRepository.findByNameAndPassword(name, password);
		if (manager == null) {
			throw new InvalidLoginException("Name or password are invalid.");
		}
		ManagerServiceImpl service = context.getBean(ManagerServiceImpl.class);
		service.setManagerId(manager.getId());
		
		ClientSession session = context.getBean(ClientSession.class);
		session.setService(service);
		session.accessed();
		
		return session;
	}

	
}
