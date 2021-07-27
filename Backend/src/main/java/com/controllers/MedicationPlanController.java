package com.controllers;

import com.entities.Medication;
import com.entities.MedicationPlan;
import com.entities.Patient;
import com.services.MedicationPlanService;
import com.services.MedicationService;
import com.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
public class MedicationPlanController {

    @Autowired
    private final MedicationPlanService medicationPlanService;

    @Autowired
    private final PatientService patientService;

    @Autowired
    public MedicationPlanController(MedicationPlanService medicationPlanService, PatientService patientService) {
        this.medicationPlanService = medicationPlanService;
        this.patientService = patientService;
    }


    @Transactional
    @PostMapping(path= "/medicationplan", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UUID> insertMedicationPlan(@Valid @RequestBody MedicationPlan medicationPlan) {
        UUID medicationPlanID = medicationPlanService.insert(medicationPlan);
        return new ResponseEntity<>(medicationPlanID, HttpStatus.CREATED);
    }

    @GetMapping(path= "/medicationplan/{id}")
    public ResponseEntity<MedicationPlan> getMedicationPlan(@PathVariable("id") UUID personId) {
        MedicationPlan medicationPlan = medicationPlanService.findMedicationPlanByPatient(getPatient(personId));
        return new ResponseEntity<>(medicationPlan, HttpStatus.OK);
    }


    @GetMapping(path= "/medicationplans/{id}")
    public ResponseEntity<List<MedicationPlan>> getMedicationPlans(@PathVariable("id") UUID personId) {
        List<MedicationPlan> medicationPlan = medicationPlanService.findMedicationPlans();
        List<MedicationPlan> newMedicationPlan = new ArrayList<MedicationPlan>();
        for(MedicationPlan m: medicationPlan){
            if(m.getPatient().getId().equals(personId)) newMedicationPlan.add(m);
        }
        return new ResponseEntity<>(medicationPlan, HttpStatus.OK);
    }

    public Patient getPatient(UUID personId) {
        return patientService.findPatientById(personId);
    }
}
