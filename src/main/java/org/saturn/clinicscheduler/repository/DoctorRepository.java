package org.saturn.clinicscheduler.repository;

import org.saturn.clinicscheduler.model.entity.Speciality;

import java.util.List;

public interface DoctorRepository {
    List<Speciality> getAllSpecialities();
}
