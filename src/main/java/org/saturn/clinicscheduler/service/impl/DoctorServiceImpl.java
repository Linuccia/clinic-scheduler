package org.saturn.clinicscheduler.service.impl;

import org.saturn.clinicscheduler.model.entity.Doctor;
import org.saturn.clinicscheduler.model.entity.Speciality;
import org.saturn.clinicscheduler.repository.DoctorRepository;
import org.saturn.clinicscheduler.repository.SpecialityRepository;
import org.saturn.clinicscheduler.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final SpecialityRepository specialityRepository;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, SpecialityRepository specialityRepository) {
        this.doctorRepository = doctorRepository;
        this.specialityRepository = specialityRepository;
    }

    @Override
    public List<Speciality> getAllSpecialities() {
        return specialityRepository.findAll();
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
}
