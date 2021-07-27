package com.repositories;

import com.entities.Caregiver;
import com.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient,UUID> {
    Patient findByUsername(String username);
    void deleteById(UUID id);
    List<Patient> findByCaregiver(Caregiver caregiver);
}
