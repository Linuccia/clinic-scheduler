package org.saturn.clinicscheduler.service.impl;

import lombok.RequiredArgsConstructor;
import org.saturn.clinicscheduler.exception.BusyPhoneNumberException;
import org.saturn.clinicscheduler.exception.DoctorNotFoundException;
import org.saturn.clinicscheduler.exception.SpecialityNotFoundException;
import org.saturn.clinicscheduler.mapper.DoctorMapper;
import org.saturn.clinicscheduler.model.dto.request.DoctorCreateDto;
import org.saturn.clinicscheduler.model.dto.response.DoctorInfoDto;
import org.saturn.clinicscheduler.model.entity.Doctor;
import org.saturn.clinicscheduler.model.entity.Speciality;
import org.saturn.clinicscheduler.repository.DoctorRepository;
import org.saturn.clinicscheduler.repository.SpecialityRepository;
import org.saturn.clinicscheduler.service.DoctorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final SpecialityRepository specialityRepository;
    private final DoctorMapper doctorMapper;

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

    @Override
    @Transactional
    public DoctorInfoDto createDoctor(DoctorCreateDto doctorCreateDto) {
        Optional<Speciality> speciality = Optional.ofNullable(specialityRepository.findById(doctorCreateDto.getSpecialityId())
                .orElseThrow(() -> { throw new SpecialityNotFoundException(); }));
        String phone = doctorCreateDto.getPhoneNumber();
        doctorRepository.getDoctorByPhoneNumber(phone).ifPresent(d -> {
            throw new BusyPhoneNumberException();
        });

        Doctor doctor = doctorMapper.mapToDoctor(doctorCreateDto, speciality.get());
        doctorRepository.save(doctor);
        return doctorMapper.mapToInfoDto(doctor);
    }
}
