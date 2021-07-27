package com.services;

import com.entities.Medication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import com.repositories.MedicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MedicationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MedicationService.class);
    private final MedicationRepository medicationRepository;

    @Autowired
    public MedicationService(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    public List<Medication> findMedications() {
        List<Medication> medicationList = medicationRepository.findAll();
        return medicationList.stream()
                .collect(Collectors.toList());
    }

    public Medication findMedicationById(UUID id) {
        Optional<Medication> prosumerOptional = medicationRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("User with id {} was not found in db", id);
            throw new ResourceNotFoundException(Medication.class.getSimpleName() + " with id: " + id);
        }
        return prosumerOptional.get();
    }

    public Medication findMedicationByName(String name) {
        Medication prosumerOptional = medicationRepository.findByName(name);
        return prosumerOptional;
    }

    public UUID insert(Medication medication) {
        medication = medicationRepository.save(medication);
        LOGGER.debug("User with id {} was inserted in db", medication.getId());
        return medication.getId();
    }

    public UUID update(Medication medication){
        medication = medicationRepository.save(medication);
        LOGGER.debug("User with id {} was updated in db", medication.getId());
        return medication.getId();
    }

    public void deleteByName(String name){
        medicationRepository.deleteByName(name);
        LOGGER.debug("Medication with name {1} was deleted in db", name);
    }
}
