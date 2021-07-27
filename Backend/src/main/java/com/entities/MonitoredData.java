package com.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Entity
public class MonitoredData {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @Column(name = "patient_id", nullable = false)
    private UUID patientId;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Column(name = "activity_label", nullable = false)
    private String activityLabel;

    @Column(name = "problem")
    private String problem;

    public MonitoredData(){}

    public MonitoredData(UUID patientId, LocalDateTime startTime, LocalDateTime endTime, String activityLabel, String problem) {
        this.patientId = patientId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityLabel = activityLabel;
        this.problem = problem;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getPatientId() {
        return patientId;
    }

    public void setPatientId(UUID patientId) {
        this.patientId = patientId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getActivityLabel() {
        return activityLabel;
    }

    public void setActivityLabel(String activityLabel) {
        this.activityLabel = activityLabel;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public LocalTime getTime() {
        long minutes = ChronoUnit.MINUTES.between(startTime, endTime) %60;
        long hours = ChronoUnit.HOURS.between(startTime, endTime);
        long seconds = ChronoUnit.SECONDS.between(startTime, endTime) %60;

        LocalTime localTime;
        localTime = LocalTime.of((int) hours, (int) minutes, (int) seconds);
        return localTime;
    }

    public LocalDate getDate(LocalDateTime a) {
        LocalDate localDate;
        localDate = LocalDate.of(a.getYear (), a.getMonth(), a.getDayOfMonth());
        return localDate;
    }


}
