package com.solal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.solal.entity.Part;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {

}
