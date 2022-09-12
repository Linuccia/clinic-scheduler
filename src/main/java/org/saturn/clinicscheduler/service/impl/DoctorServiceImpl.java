package org.saturn.clinicscheduler.service.impl;

import lombok.RequiredArgsConstructor;
import org.saturn.clinicscheduler.exception.DoctorNotFoundException;
import org.saturn.clinicscheduler.exception.SpecialityNotFoundException;
import org.saturn.clinicscheduler.model.entity.Doctor;
import org.saturn.clinicscheduler.model.entity.Speciality;
import org.saturn.clinicscheduler.repository.DoctorRepository;
import org.saturn.clinicscheduler.repository.SpecialityRepository;
import org.saturn.clinicscheduler.service.DoctorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final SpecialityRepository specialityRepository;

    @Override
    public List<Speciality> getAllSpecialities() {
        return specialityRepository.findAll();
    }

    @Override
    public List<Doctor> getDoctorsBySpecialityId(Long id) {
<<<<<<< HEAD
<<<<<<< HEAD
        specialityRepository.findById(id).orElseThrow(SpecialityNotFoundException::new);
        List<Doctor> doctors = doctorRepository.getDoctorsBySpecialityId(id);
        if (!doctors.isEmpty()) {
            return doctors;
        } else {
            throw new DoctorNotFoundException();
        }
=======
        return doctorRepository.getDoctorsBySpecialityId(id);
>>>>>>> 597acb0 (doctors by speciality)
=======

        specialityRepository.findById(id).orElseThrow(() -> { throw new SpecialityNotFoundException(); });
        List<Doctor> doctors = doctorRepository.getDoctorsBySpecialityId(id);
        if (doctors.size() > 0) {
            return doctors;
        } else throw new DoctorNotFoundException();
>>>>>>> 110653d (add exception for speciality)
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
}
