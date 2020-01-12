package com.solal.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.solal.entity.Advisor;
import com.solal.entity.Manager;
import com.solal.entity.Mechanic;
import com.solal.entity.Storekeeper;
import com.solal.repository.AdvisorRepository;
import com.solal.repository.ManagerRepository;
import com.solal.repository.MechanicRepository;
import com.solal.repository.StoreKeeperRepository;
import com.solal.rest.ex.InvalidLoginException;
import com.solal.service.AdvisorServiceImpl;
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
	private AdvisorRepository advisorRepository;

	// Constructor
	@Autowired
	public LoginSystem(ApplicationContext context, ManagerRepository managerRepository,
			MechanicRepository mechanicRepository, StoreKeeperRepository storeKeeperRepository,
			AdvisorRepository advisorRepository) {
		this.context = context;
		this.managerRepository = managerRepository;
		this.mechanicRepository = mechanicRepository;
		this.storeKeeperRepository = storeKeeperRepository;
		this.advisorRepository = advisorRepository;
	}

	/**
	 * This function uses the switch box system to dispatch connections for Admin
	 * Company and Customer
	 * 
	 * @param name
	 * @param password
	 * @param loginType
	 * @param garageCode
	 * @return ClientSession
	 * @throws InvalidLoginException
	 */
	public ClientSession login(String name, String password, String loginType, String garageCode)
			throws InvalidLoginException {
		switch (loginType) {
			case "MANAGER":
				return managerLogin(name, password, garageCode);
			case "STOREKEEPER":
				return storekeeperLogin(name, password, garageCode);
			case "MECHANIC":
				return mechanicLogin(name, password, garageCode);
			case "ADVISOR":
				return advisorLogin(name, password, garageCode);
			default:
				throw new InvalidLoginException("Invalid loginType");
		}
	}

	private ClientSession storekeeperLogin(String name, String password, String garageCode)
			throws InvalidLoginException {
		Storekeeper storekeeper = storeKeeperRepository.findByNameAndPasswordAndGarageCode(name, password, garageCode);
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

	private ClientSession mechanicLogin(String name, String password, String garageCode) throws InvalidLoginException {
		Mechanic mechanic = mechanicRepository.findByNameAndPasswordAndGarageCode(name, password, garageCode);
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

	private ClientSession managerLogin(String name, String password, String garageCode) throws InvalidLoginException {
		Manager manager = managerRepository.findByNameAndPasswordAndGarageCode(name, password, garageCode);
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

	private ClientSession advisorLogin(String name, String password, String garageCode) throws InvalidLoginException {
		Advisor advisor = advisorRepository.findByNameAndPasswordAndGarageCode(name, password, garageCode);
		if (advisor == null) {
			throw new InvalidLoginException("Name or password are invalid.");
		}
		AdvisorServiceImpl service = context.getBean(AdvisorServiceImpl.class);
		service.setAdvisorId(advisor.getId());

		ClientSession session = context.getBean(ClientSession.class);
		session.setService(service);
		session.accessed();

		return session;
	}

}
