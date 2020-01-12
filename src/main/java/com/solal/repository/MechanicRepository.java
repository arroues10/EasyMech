package com.solal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.solal.entity.Mechanic;

@Repository
public interface MechanicRepository extends JpaRepository<Mechanic, Long> {

	Mechanic findByNameAndPasswordAndGarageCode(String name, String password, String garageCode);
}
