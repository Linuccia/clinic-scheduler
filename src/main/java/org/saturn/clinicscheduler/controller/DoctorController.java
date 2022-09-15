package org.saturn.clinicscheduler.controller;

import lombok.RequiredArgsConstructor;
import org.saturn.clinicscheduler.model.dto.request.DoctorCreateDto;
import org.saturn.clinicscheduler.model.dto.response.DoctorInfoDto;
import org.saturn.clinicscheduler.model.entity.Speciality;
import org.saturn.clinicscheduler.service.DoctorService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping("/specialities")
    public ResponseEntity<List<Speciality>> getSpecialities(){
        return ResponseEntity.ok(doctorService.getAllSpecialities());
    }

    @GetMapping("/doctors")
    public ResponseEntity<List<DoctorInfoDto>> getAllDoctors(){
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @GetMapping("specialities/{id}/doctors")
    public ResponseEntity<List<DoctorInfoDto>> getDoctorsBySpeciality(@PathVariable Long id){
        return ResponseEntity.ok(doctorService.getDoctorsBySpecialityId(id));
    }

    @PostMapping("/doctors")
    public ResponseEntity<DoctorInfoDto> createDoctor(@RequestBody DoctorCreateDto doctorCreateDto){
        return ResponseEntity.ok(doctorService.createDoctor(doctorCreateDto));
    }

    @DeleteMapping("/doctors/{id}")
    public ResponseEntity<DoctorInfoDto> deleteDoctor(@PathVariable Long id){
        return ResponseEntity.ok((doctorService.deleteDoctor(id)));
    }
}
