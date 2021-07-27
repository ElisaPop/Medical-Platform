package com.services;

import com.entities.Caregiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import com.repositories.CaregiverRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CaregiverService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CaregiverService.class);
    private final CaregiverRepository caregiverRepository;

    @Autowired
    public CaregiverService(CaregiverRepository caregiverRepository) {
        this.caregiverRepository = caregiverRepository;
    }

    public List<Caregiver> findCaregivers() {
        List<Caregiver> caregiverList = caregiverRepository.findAll();
        return caregiverList.stream()
                .collect(Collectors.toList());
    }

    public Caregiver findCaregiverByUsername(String username) {
        Caregiver prosumerOptional = caregiverRepository.findByUsername(username);
        return prosumerOptional;
    }

    public Caregiver findCaregiverById(UUID id) {
        Optional<Caregiver> prosumerOptional = caregiverRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("User with id {} was not found in db", id);
            throw new ResourceNotFoundException(Caregiver.class.getSimpleName() + " with id: " + id);
        }
        return prosumerOptional.get();
    }

    public UUID insert(Caregiver caregiver) {
        caregiver = caregiverRepository.save(caregiver);
        LOGGER.debug("User with id {} was inserted in db", caregiver.getId());
        return caregiver.getId();
    }

    public UUID update(Caregiver caregiver){
        caregiver = caregiverRepository.save(caregiver);
        LOGGER.debug("User with id {} was updated in db", caregiver.getId());
        return caregiver.getId();
    }

    public void deleteByUsername(String username){
        caregiverRepository.deleteByUsername(username);
        LOGGER.debug("Caregiver with username {} was deleted in db", username);
    }

}
