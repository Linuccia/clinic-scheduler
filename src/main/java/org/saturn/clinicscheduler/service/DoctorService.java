package org.saturn.clinicscheduler.service;

import org.saturn.clinicscheduler.model.entity.Speciality;

import java.util.List;

public interface DoctorService {
    List<Speciality> getAllSpecialities();
}
