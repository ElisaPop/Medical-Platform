package com.communication;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Dispenser implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private List<String> medications;
    private String intervalStart;
    private String intervalEnd;
    private LocalDate periodStart;
    private LocalDate periodEnd;

    public Dispenser(){
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Dispenser(int id, String name, List<String> medications, String intervalStart, String intervalEnd, LocalDate periodStart, LocalDate periodEnd) {
        super();
        this.id = id;
        this.name = name;
        this.medications = medications;
        this.intervalStart = intervalStart;
        this.intervalEnd = intervalEnd;
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getMedications() {
        return medications;
    }

    public void setMedications(List<String> medications) {
        this.medications = medications;
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
}
