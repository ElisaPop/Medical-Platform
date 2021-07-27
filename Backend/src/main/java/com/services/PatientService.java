package com.services;

import com.entities.Caregiver;
import com.entities.Patient;
import com.entities.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import com.repositories.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PatientService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientService.class);
    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> findPatients() {
        List<Patient> PatientList = patientRepository.findAll();
        return PatientList.stream()
                .collect(Collectors.toList());
    }

    public Patient findPatientById(UUID id) {
        Optional<Patient> prosumerOptional = patientRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Patient with id {} was not found in db", id);
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + " with id: " + id);
        }
        return prosumerOptional.get();
    }

    public Patient findByUsername(String username){
        Patient patient = patientRepository.findByUsername(username);
        return patient;
    }

    public List<Patient> findByCaregiver(Caregiver caregiver){
        List<Patient> patient = patientRepository.findByCaregiver(caregiver);
        return patient;
    }

    public UUID insert(Patient patient) {
        patient = patientRepository.save(patient);
        LOGGER.debug("Patient with id {} was inserted in db", patient.getId());
        return patient.getId();
    }

    public UUID update(Patient patient){
        patient = patientRepository.save(patient);
        LOGGER.debug("Patient with id {} was updated in db", patient.getId());
        return patient.getId();
    }

    public void deleteById(UUID id){
        UUID oldId = id;
        patientRepository.deleteById(id);
        LOGGER.debug("Patient with id {} was deleted in db", oldId);
    }
}
