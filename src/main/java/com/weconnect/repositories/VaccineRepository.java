package com.weconnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weconnect.models.Vaccine;


public interface  VaccineRepository extends JpaRepository<Vaccine, Long> {

}
