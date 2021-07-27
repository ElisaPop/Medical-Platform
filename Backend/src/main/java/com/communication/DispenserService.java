package com.communication;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface DispenserService {
    List<Dispenser> retrieveMedications(UUID patientId);

    void sendMessage(String message);
}
