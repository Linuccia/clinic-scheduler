package org.saturn.clinicscheduler.service;

import org.saturn.clinicscheduler.model.dto.request.DoctorCreateDto;
import org.saturn.clinicscheduler.model.dto.response.DoctorInfoDto;
import org.saturn.clinicscheduler.model.entity.Doctor;
import org.saturn.clinicscheduler.model.entity.Speciality;

import java.util.List;

public interface DoctorService {

    List<Speciality> getAllSpecialities();

    List<Doctor> getDoctorsBySpecialityId(Long id);

    List<Doctor> getAllDoctors();

    DoctorInfoDto createDoctor(DoctorCreateDto doctorDto);
}
