package com.repositories;

import com.entities.MedicationPlan;
import com.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MedicationPlanRepository extends JpaRepository<MedicationPlan,UUID> {
    void deleteById(UUID id);
    MedicationPlan findByPatient(Patient patient);
}
