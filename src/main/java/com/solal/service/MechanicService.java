package com.solal.service;

import java.util.List;

import com.solal.entity.Part;

public interface MechanicService extends Service {

	List<Part> addParts(List<Part> parts);

}
