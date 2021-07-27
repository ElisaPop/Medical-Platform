package com.communication;

import com.services.MedicationPlanService;
import com.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

@Configuration
public class ServerConfiguration {
    @Autowired
    private final PatientService patientService;
    @Autowired
    private final MedicationPlanService medicationPlanService;

    @Autowired
    public ServerConfiguration(PatientService patientService, MedicationPlanService medicationPlanService) {
        this.patientService = patientService;
        this.medicationPlanService = medicationPlanService;
    }

    @Bean(name = "dispenser")
    DispenserService booking() {
        return new RetrievalService(patientService, medicationPlanService);
    }

    @Bean(name = "/dispenser")
    HttpInvokerServiceExporter dispenserService(@Qualifier("dispenser") DispenserService service) {
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(service);
        exporter.setServiceInterface(DispenserService.class);
        return exporter;
    }

}
