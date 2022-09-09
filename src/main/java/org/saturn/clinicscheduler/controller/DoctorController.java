package org.saturn.clinicscheduler.controller;

import org.saturn.clinicscheduler.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/specialities")
    public ResponseEntity<?> getSpecialities(){
        return new ResponseEntity<>(doctorService.getAllSpecialities(), HttpStatus.OK);
    }
}
