package com.solal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.solal.entity.WorkCard;

@Repository
public interface WorkCardRepository extends JpaRepository<WorkCard, Long> {

}
