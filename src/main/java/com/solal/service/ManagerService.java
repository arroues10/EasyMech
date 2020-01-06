package com.solal.service;

import java.util.List;

import com.solal.entity.Mechanic;
import com.solal.entity.WorkCard;

public interface ManagerService extends Service {

	List<WorkCard> getAllWorkCards();

	List<Mechanic> getAllMechanics();

	WorkCard getWorkCardById(long id);

}
