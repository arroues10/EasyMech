package com.solal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.solal.entity.Storekeeper;

@Repository
public interface StoreKeeperRepository extends JpaRepository<Storekeeper, Long> {

}
