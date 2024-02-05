package com.SpringBootAssignments.trafficOffenderManagement.repositories;

import com.SpringBootAssignments.trafficOffenderManagement.models.TrafficOffender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrafficOffenderRepository extends JpaRepository<TrafficOffender, Long> {
    List<TrafficOffender> findByFirstnameContainingOrLastnameContainingAllIgnoreCase(String firstname, String lastname);
}
