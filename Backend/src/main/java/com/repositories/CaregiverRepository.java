package com.repositories;

import com.entities.Caregiver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CaregiverRepository extends JpaRepository<Caregiver,UUID>{
    void deleteById(UUID id);
    void deleteByUsername(String username);
    Caregiver findByUsername(String username);
}
