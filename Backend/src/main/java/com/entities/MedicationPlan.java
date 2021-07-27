package com.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
public class MedicationPlan {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @ManyToOne
    @JoinColumn(name="doctor_id")
    private Doctor doctor;

    @ManyToMany(mappedBy = "medicationPlans", fetch = FetchType.EAGER)
    private Set<Medication> medications;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false)
    private Patient patient;

    @Column(name = "interval_start", nullable = false)
    private String intervalStart;

    @Column(name = "interval_end", nullable = false)
    private String intervalEnd;

    @Column(name = "period_start", nullable = false)
    private LocalDate periodStart;

    @Column(name = "period_end", nullable = false)
    private LocalDate periodEnd;

    public MedicationPlan(){}

    public MedicationPlan(UUID id, Doctor doctor, Set<Medication> medications, Patient patient, String intervalStart, String intervalEnd, LocalDate periodStart, LocalDate periodEnd) {
        this.id = id;
        this.doctor = doctor;
        this.medications = medications;
        this.patient = patient;
        this.intervalStart = intervalStart;
        this.intervalEnd = intervalEnd;
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
    }

    public MedicationPlan(Doctor doctor, Set<Medication> medications, Patient patient, String intervalStart, String intervalEnd, LocalDate periodStart, LocalDate periodEnd) {
        this.doctor = doctor;
        this.medications = medications;
        this.patient = patient;
        this.intervalStart = intervalStart;
        this.intervalEnd = intervalEnd;
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getIntervalStart() {
        return intervalStart;
    }

    public void setIntervalStart(String intervalStart) {
        this.intervalStart = intervalStart;
    }

    public String getIntervalEnd() {
        return intervalEnd;
    }

    public void setIntervalEnd(String intervalEnd) {
        this.intervalEnd = intervalEnd;
    }

    public LocalDate getPeriodStart() {
        return periodStart;
    }

    public void setPeriodStart(LocalDate periodStart) {
        this.periodStart = periodStart;
    }

    public LocalDate getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(LocalDate periodEnd) {
        this.periodEnd = periodEnd;
    }

    public Set<Medication> getMedications() {
        return medications;
    }

    public void setMedications(Set<Medication> medications) {
        this.medications = medications;
    }
}
