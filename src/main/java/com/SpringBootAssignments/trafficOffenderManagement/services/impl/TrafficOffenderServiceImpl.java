package com.SpringBootAssignments.trafficOffenderManagement.services.impl;

import com.SpringBootAssignments.trafficOffenderManagement.dto.TrafficOffenderDTO;
import com.SpringBootAssignments.trafficOffenderManagement.models.TrafficOffender;
import com.SpringBootAssignments.trafficOffenderManagement.repositories.TrafficOffenderRepository;
import com.SpringBootAssignments.trafficOffenderManagement.services.TrafficOffenderService;
import com.SpringBootAssignments.trafficOffenderManagement.utils.TrafficOffenderPatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TrafficOffenderServiceImpl implements TrafficOffenderService {
    @Autowired
    private TrafficOffenderPatcher patcher;

    private final TrafficOffenderRepository trafficOffenderRepository;

    public TrafficOffenderServiceImpl(TrafficOffenderRepository trafficOffenderRepository){
        this.trafficOffenderRepository = trafficOffenderRepository;
    }

    @Override
    public TrafficOffender createTrafficOffender(TrafficOffenderDTO trafficOffenderDTO) {
        TrafficOffender trafficOffender = new TrafficOffender();
        trafficOffender.setFirstname(trafficOffenderDTO.getFirstname());
        trafficOffender.setLastname(trafficOffenderDTO.getLastname());
        trafficOffender.setEmail(trafficOffenderDTO.getEmail());
        trafficOffender.setAge(trafficOffenderDTO.getAge());
        trafficOffender.setGender(trafficOffenderDTO.getGender());
        trafficOffender.setAddress(trafficOffenderDTO.getAddress());
        trafficOffender.setOffence(trafficOffenderDTO.getOffence());

        return trafficOffenderRepository.save(trafficOffender);

    }

    @Override
    public Optional<TrafficOffender> getTrafficOffender(Long id) {
        return trafficOffenderRepository.findById(id);
    }

    @Override
    public List<TrafficOffender> getAllTrafficOffenders(String name) {
        List<TrafficOffender> trafficOffenders = new ArrayList<>();
        trafficOffenderRepository.findByFirstnameContainingIgnoreCase(name)
                .forEach(trafficOffenders::add);
        return trafficOffenders;
    }

    public List<TrafficOffender> getAllTrafficOffenders() {
        List<TrafficOffender> trafficOffenders = new ArrayList<>();
        trafficOffenderRepository.findAll().forEach(trafficOffenders::add);
        return trafficOffenders;
    }

    @Override
    public TrafficOffender updateTrafficOffender(Long id, TrafficOffenderDTO trafficOffenderDTO) {
        Optional<TrafficOffender> optionalTrafficOffender = trafficOffenderRepository.findById(id);
        if(optionalTrafficOffender.isPresent()){
            TrafficOffender updatedTrafficOffender = optionalTrafficOffender.get();
            if(trafficOffenderDTO.getFirstname() != null){
                updatedTrafficOffender.setFirstname(trafficOffenderDTO.getFirstname());
            }
            if(trafficOffenderDTO.getLastname() != null){
                updatedTrafficOffender.setLastname(trafficOffenderDTO.getLastname());
            }
            if(trafficOffenderDTO.getEmail() != null){
                updatedTrafficOffender.setEmail(trafficOffenderDTO.getEmail());
            }
            if(trafficOffenderDTO.getAge() == 0){
                updatedTrafficOffender.setAge((trafficOffenderDTO.getAge()));
            }
            if(trafficOffenderDTO.getGender() != null){
                updatedTrafficOffender.setGender(trafficOffenderDTO.getGender());
            }
            if(trafficOffenderDTO.getOffence() != null){
                updatedTrafficOffender.setOffence(trafficOffenderDTO.getOffence());
            }
            if(trafficOffenderDTO.getAddress() != null){
                updatedTrafficOffender.setOffence(trafficOffenderDTO.getAddress());
            }
            return trafficOffenderRepository.save(updatedTrafficOffender);
        }
        return null;
    }



    @Override
    public String deleteTrafficOffender(Long id){
        trafficOffenderRepository.deleteById(id);
        return "Deleted successfully";
    }

    @Override
    public String deleteAllTrafficOffenders() {
        trafficOffenderRepository.deleteAll();
        return "Deleted all traffic offenders";
    }
}
