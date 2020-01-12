package com.solal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.solal.entity.Advisor;

@Repository
public interface AdvisorRepository extends JpaRepository<Advisor, Long> {

	Advisor findByNameAndPasswordAndGarageCode(String name, String password, String garageCode);
}
