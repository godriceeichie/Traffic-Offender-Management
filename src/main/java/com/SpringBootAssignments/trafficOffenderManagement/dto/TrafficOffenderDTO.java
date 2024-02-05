package com.SpringBootAssignments.trafficOffenderManagement.dto;

import com.SpringBootAssignments.trafficOffenderManagement.models.TrafficOffender;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;


@Data
public class TrafficOffenderDTO {
    private String firstname;
    private String lastname;
    private String email;
    private int age;
    private TrafficOffender.Gender gender;
    private String address;
    private String offence;
}
