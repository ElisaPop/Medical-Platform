package com.repositories;

import com.entities.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MedicationRepository extends JpaRepository<Medication,UUID> {
    void deleteByName(String name);
    Medication findByName(String name);
}
