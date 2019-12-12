package com.solal.service;

import java.util.List;

import com.solal.entity.Part;

public interface StoreKeeperService extends Service {

	List<Part> getAllWorkCardParts(long workCardId);

}
