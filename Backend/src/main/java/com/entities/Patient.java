package com.entities;

import com.entities.enums.Gender;
import com.entities.enums.Role;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Patient extends Person {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="caregiver_id")
    private Caregiver caregiver;

    private String medicalRecord;

    @OneToOne(mappedBy = "patient")
    private MedicationPlan medicationPlan;

    public Patient(){

    }

    public Patient(Caregiver caregiver, String medicalRecord, MedicationPlan medicationPlan) {
        this.caregiver = caregiver;
        this.medicalRecord = medicalRecord;
        this.medicationPlan = medicationPlan;
    }

    public Patient(UUID id, String username, String password, String name, LocalDate birthdate, Gender gender, String address, Role role, Caregiver caregiver, String medicalRecord, MedicationPlan medicationPlan) {
        super(id, username, password, name, birthdate, gender, address, Role.PATIENT);
        this.caregiver = caregiver;
        this.medicalRecord = medicalRecord;
        this.medicationPlan = medicationPlan;
    }

    public Patient(String name, String username, String password, LocalDate birthdate, Gender gender, String address, Role role, Caregiver caregiver, String medicalRecord, MedicationPlan medicationPlan) {
        super(name, username, password, birthdate, gender, address, Role.PATIENT);
        this.caregiver = caregiver;
        this.medicalRecord = medicalRecord;
        this.medicationPlan = medicationPlan;
    }

    public Caregiver getCaregiver() {
        return caregiver;
    }

    public void setCaregiver(Caregiver caregiver) {
        this.caregiver = caregiver;
    }

    public String getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(String medicalRecord) {
        this.medicalRecord = medicalRecord;
    }
}
