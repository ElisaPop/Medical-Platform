package com.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
public class Medication{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "side_effects", nullable = false)
    private String sideEffects;

    @Column(name = "available_dosage", nullable = false)
    private int availableDosage;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "medication_plan_medication",
            joinColumns = @JoinColumn(name = "medication_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "medication_plan_id", referencedColumnName = "id"))
    private Set<MedicationPlan> medicationPlans;

    public Medication(){}

    public Medication(UUID id, String name, String sideEffects, int availableDosage) {
        this.id = id;
        this.name = name;
        this.sideEffects = sideEffects;
        this.availableDosage = availableDosage;
    }

    public Medication(UUID id, String name, int availableDosage) {
        this.id = id;
        this.name = name;
        this.availableDosage = availableDosage;
    }

    public Medication(String name, int availableDosage) {
        this.name = name;
        this.availableDosage = availableDosage;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public int getAvailableDosage() {
        return availableDosage;
    }

    public void setAvailableDosage(int availableDosage) {
        this.availableDosage = availableDosage;
    }


}
