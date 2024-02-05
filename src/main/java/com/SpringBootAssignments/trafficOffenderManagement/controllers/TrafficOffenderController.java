package com.SpringBootAssignments.trafficOffenderManagement.controllers;

import com.SpringBootAssignments.trafficOffenderManagement.dto.TrafficOffenderDTO;
import com.SpringBootAssignments.trafficOffenderManagement.models.TrafficOffender;
import com.SpringBootAssignments.trafficOffenderManagement.services.impl.TrafficOffenderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class TrafficOffenderController {
    private final TrafficOffenderService trafficOffenderService;

    public TrafficOffenderController(TrafficOffenderService trafficOffenderService){
        this.trafficOffenderService = trafficOffenderService;
    }

    @GetMapping("/trafficOffenders/{id}")
    public ResponseEntity<TrafficOffender> getTrafficOffender(@PathVariable("id") Long id){

            Optional<TrafficOffender> trafficOffender = trafficOffenderService.getTrafficOffender(id);
            if(trafficOffender.isPresent()){
                return new ResponseEntity<>(trafficOffender.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/trafficOffenders")
    public ResponseEntity<List<TrafficOffender>> getAllTrafficOffenders(@RequestParam(required = false) String name){
        try{
            List<TrafficOffender> trafficOffenders = new ArrayList<TrafficOffender>();
            if(name == null){
                trafficOffenderService.getAllTrafficOffenders().forEach(trafficOffenders::add);
            }
            else{
                trafficOffenderService.getAllTrafficOffenders(name).forEach(trafficOffenders::add);
            }

            if(trafficOffenders.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(trafficOffenders, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/trafficOffenders")
    public ResponseEntity<TrafficOffender> createTrafficOffender(@RequestBody TrafficOffenderDTO trafficOffenderDTO){
        try {
            TrafficOffender trafficOffender = trafficOffenderService.createTrafficOffender(trafficOffenderDTO);
            return new ResponseEntity<>(trafficOffender, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/trafficOffenders/{id}")
    public ResponseEntity<TrafficOffender> updateTrafficOffender(@PathVariable("id") Long id, @RequestBody TrafficOffenderDTO trafficOffenderDTO){
        TrafficOffender response = trafficOffenderService.updateTrafficOffender(id, trafficOffenderDTO);
        if(response != null){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/trafficOffenders/{id}")
    public ResponseEntity<String> deleteTrafficOffender(@PathVariable("id") Long id){
        try{
            return new ResponseEntity<>(trafficOffenderService.deleteTrafficOffender(id), HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/trafficOffenders")
    public ResponseEntity<String> deleteAllTrafficOffenders(){
        try{
            return new ResponseEntity<String>(trafficOffenderService.deleteAllTrafficOffenders(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
