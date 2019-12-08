package com.solal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solal.entity.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

	Manager findByNameAndPassword(String name, String password);
}
