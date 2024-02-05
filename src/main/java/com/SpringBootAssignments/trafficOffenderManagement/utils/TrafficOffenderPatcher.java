package com.SpringBootAssignments.trafficOffenderManagement.utils;

import com.SpringBootAssignments.trafficOffenderManagement.dto.TrafficOffenderDTO;
import com.SpringBootAssignments.trafficOffenderManagement.models.TrafficOffender;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class TrafficOffenderPatcher {
    public void trafficOffenderPatcher(TrafficOffender existingOffender, TrafficOffenderDTO incompleteOffender) throws IllegalAccessException{

        //GET THE COMPILED VERSION OF THE CLASS
        Class<?> trafficOffenderDTOClass = TrafficOffenderDTO.class;
        Field[] trafficOffenderDTOFields = trafficOffenderDTOClass.getDeclaredFields();
        System.out.println(trafficOffenderDTOFields.length);
        for(Field field : trafficOffenderDTOFields){
            System.out.println(field.getName());
            //CANT ACCESS IF THE FIELD IS PRIVATE
            field.setAccessible(true);

            //CHECK IF THE VALUE OF THE FIELD IS NOT NULL, IF NOT UPDATE EXISTING INTERN
            Object value=field.get(incompleteOffender);
            if(value!=null){
                field.set(existingOffender,value);
            }
            //MAKE THE FIELD PRIVATE AGAIN
            field.setAccessible(false);
        }
    }
}

