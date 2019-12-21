package com.solal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.solal.entity.WorkCard;

@Repository
public interface WorkCardRepository extends JpaRepository<WorkCard, Long> {

	Optional<WorkCard> findByPlateNumber(String plateNumber);

}
