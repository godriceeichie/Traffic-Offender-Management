package com.SpringBootAssignments.trafficOffenderManagement.services;

import com.SpringBootAssignments.trafficOffenderManagement.dto.TrafficOffenderDTO;
import com.SpringBootAssignments.trafficOffenderManagement.models.TrafficOffender;

import java.util.List;
import java.util.Optional;

public interface TrafficOffenderInterface {
    public TrafficOffender createTrafficOffender(TrafficOffenderDTO trafficOffenderDTO);
    public Optional<TrafficOffender> getTrafficOffender(Long id);
    public List<TrafficOffender> getAllTrafficOffenders(String name);
    public TrafficOffender updateTrafficOffender(Long id, TrafficOffenderDTO trafficOffenderDTO);
    public String deleteTrafficOffender(Long id);
    public String deleteAllTrafficOffenders();
}
