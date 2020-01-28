package com.solal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.solal.entity.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {

	Manager findByNameAndPasswordAndGarageCode(String name, String password, String garageCode);
}
