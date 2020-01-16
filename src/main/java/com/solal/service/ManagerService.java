package com.solal.service;

import java.util.List;

import com.solal.entity.Advisor;
import com.solal.entity.Mechanic;
import com.solal.entity.Storekeeper;
import com.solal.entity.WorkCard;

public interface ManagerService extends Service {

	List<WorkCard> getAllWorkCards();

	List<Mechanic> getAllMechanics();

	WorkCard getWorkCardById(long id);

	Mechanic createMechanic(Mechanic mechanic);

	Storekeeper createStoreKeeper(Storekeeper storekeeper);

	Advisor createAdvisor(Advisor advisor);

}
