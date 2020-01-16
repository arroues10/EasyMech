package com.solal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.solal.entity.Advisor;
import com.solal.entity.Mechanic;
import com.solal.entity.Storekeeper;
import com.solal.entity.WorkCard;
import com.solal.repository.AdvisorRepository;
import com.solal.repository.MechanicRepository;
import com.solal.repository.StoreKeeperRepository;
import com.solal.repository.WorkCardRepository;

@Service
public class ManagerServiceImpl implements ManagerService {

	private long managerId;

	private WorkCardRepository workCardRepository;
	private MechanicRepository mechanicRepository;
	private StoreKeeperRepository storeKeeperRepository;
	private AdvisorRepository advisorRepository;

	public ManagerServiceImpl(WorkCardRepository workCardRepository, MechanicRepository mechanicRepository,
			StoreKeeperRepository storeKeeperRepository, AdvisorRepository advisorRepository) {
		this.workCardRepository = workCardRepository;
		this.mechanicRepository = mechanicRepository;
		this.storeKeeperRepository = storeKeeperRepository;
		this.advisorRepository = advisorRepository;
	}

	public void setManagerId(long managerId) {
		this.managerId = managerId;
	}

	@Override
	public List<WorkCard> getAllWorkCards() {
		return workCardRepository.findAll();
	}

	@Override
	public List<Mechanic> getAllMechanics() {
		return mechanicRepository.findAll();
	}

	@Override
	public WorkCard getWorkCardById(long id) {
		return workCardRepository.findById(id).orElse(null);
	}

	@Override
	public Mechanic createMechanic(Mechanic mechanic) {
		mechanic.setId(0);
		return mechanicRepository.save(mechanic);
	}

	@Override
	public Storekeeper createStoreKeeper(Storekeeper storekeeper) {
		storekeeper.setId(0);
		return storeKeeperRepository.save(storekeeper);
	}

	@Override
	public Advisor createAdvisor(Advisor advisor) {
		advisor.setId(0);
		return advisorRepository.save(advisor);
	}

}
