package com.services;

import com.entities.Doctor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import com.repositories.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DoctorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorService.class);
    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> findDoctors() {
        List<Doctor> doctorList = doctorRepository.findAll();
        return doctorList.stream()
                .collect(Collectors.toList());
    }

    public Doctor findDoctorById(UUID id) {
        Optional<Doctor> prosumerOptional = doctorRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("User with id {} was not found in db", id);
            throw new ResourceNotFoundException(Doctor.class.getSimpleName() + " with id: " + id);
        }
        return prosumerOptional.get();
    }

    public UUID insert(Doctor doctor) {
        doctor = doctorRepository.save(doctor);
        LOGGER.debug("User with id {} was inserted in db", doctor.getId());
        return doctor.getId();
    }
}
