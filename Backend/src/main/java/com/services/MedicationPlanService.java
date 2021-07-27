package com.services;

import com.entities.MedicationPlan;
import com.entities.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.repositories.MedicationPlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MedicationPlanService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MedicationPlanService.class);
    private final MedicationPlanRepository medicationPlanRepository;

    @Autowired
    public MedicationPlanService(MedicationPlanRepository medicationPlanRepository) {
        this.medicationPlanRepository = medicationPlanRepository;
    }

    public List<MedicationPlan> findMedicationPlans() {
        List<MedicationPlan> medicationPlanList = medicationPlanRepository.findAll();
        return medicationPlanList.stream()
                .collect(Collectors.toList());
    }

    public MedicationPlan findMedicationPlanByPatient(Patient patient) {
        MedicationPlan prosumerOptional = medicationPlanRepository.findByPatient(patient);
        return prosumerOptional;
    }

    public UUID insert(MedicationPlan medicationPlan) {
        medicationPlan = medicationPlanRepository.save(medicationPlan);
        LOGGER.debug("User with id {} was inserted in db", medicationPlan.getId());
        return medicationPlan.getId();
    }
}
