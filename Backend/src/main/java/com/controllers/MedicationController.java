package com.controllers;

import com.entities.Medication;
import com.services.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/medication")
public class MedicationController {

    @Autowired
    private final MedicationService medicationService;

    @Autowired
    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @GetMapping()
    public ResponseEntity<List<Medication>> getMedications() {
        List<Medication> peopleList = medicationService.findMedications();
        return new ResponseEntity<>(peopleList, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<UUID> insertMedication(@Valid @RequestBody Medication medication) {
        UUID medicationID = medicationService.insert(medication);
        return new ResponseEntity<>(medicationID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Medication> getMedication(@PathVariable("id") UUID medicationId) {
        Medication medication = medicationService.findMedicationById(medicationId);
        return new ResponseEntity<>(medication, HttpStatus.OK);
    }

    @GetMapping(value = "/cg/{name}")
    public ResponseEntity<Medication> getMedicationByName(@PathVariable("name") String name) {
        Medication medication = medicationService.findMedicationByName(name);
        return new ResponseEntity<>(medication, HttpStatus.OK);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<UUID> updateMedication(@Valid @RequestBody Medication medication) {
        UUID medicationID = medicationService.update(medication);
        return new ResponseEntity<>(medicationID, HttpStatus.CREATED);
    }

    @DeleteMapping("/{name}")
    @PreAuthorize("hasRole('DOCTOR')")
    public HttpStatus deleteMedication(@PathVariable("name") String name) {
        medicationService.deleteByName(name);
        return HttpStatus.OK;
    }
}
