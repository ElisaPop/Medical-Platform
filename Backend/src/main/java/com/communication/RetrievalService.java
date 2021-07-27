package com.communication;

import com.entities.Medication;
import com.entities.MedicationPlan;
import com.services.MedicationPlanService;
import com.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class RetrievalService implements DispenserService{
    @Autowired
    private final PatientService patientService;
    @Autowired
    private final MedicationPlanService medicationPlanService;

    @Autowired
    public RetrievalService(PatientService patientService, MedicationPlanService medicationPlanService){
        this.patientService = patientService;
        this.medicationPlanService = medicationPlanService;
    }

    @Override
    public List<Dispenser> retrieveMedications(UUID patientID){
        int cnt = 0;
        //MedicationPlan retrievedMedicationPlan = medicationPlanRepository.findByPatient(patientRepository.findByUsername(patientUsername));
        List<Dispenser> dispensers = new ArrayList<Dispenser>();

        List<MedicationPlan> retrievedMedicationPlan = medicationPlanService.findMedicationPlans();
        List<MedicationPlan> newMedicationPlan = new ArrayList<MedicationPlan>();
        for(MedicationPlan m: retrievedMedicationPlan){
            if(m.getPatient().getId().equals(patientID)){
                Set<Medication> medications = m.getMedications();
                List<String> newMedications = new ArrayList<>();
                for(Medication mm : medications){
                    newMedications.add(mm.getName());
                }

                if(newMedications.isEmpty()){
                    newMedications.add("testmed1");
                    newMedications.add("testmed2");
                }

                dispensers.add(new Dispenser(++cnt,m.getPatient().getName(),newMedications, m.getIntervalStart(), m.getIntervalEnd(), m.getPeriodStart(), m.getPeriodEnd()));
            }
        }
        return dispensers;
    }

    @Override
    public void sendMessage(String message) {
        System.out.println("Retrieved notification from dispenser: " + message);
    }

}
