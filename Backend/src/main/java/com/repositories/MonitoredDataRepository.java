package com.repositories;

import com.entities.MonitoredData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MonitoredDataRepository extends JpaRepository<MonitoredData, UUID> {
}
