package org.saturn.clinicscheduler.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.saturn.clinicscheduler.mapper.DoctorMapper;
import org.saturn.clinicscheduler.mapper.SpecialityMapper;
import org.saturn.clinicscheduler.model.dto.request.DoctorCreateDto;
import org.saturn.clinicscheduler.model.dto.response.DoctorInfoDto;
import org.saturn.clinicscheduler.model.dto.response.SpecialityDto;
import org.saturn.clinicscheduler.model.entity.Doctor;
import org.saturn.clinicscheduler.model.entity.Speciality;
import org.saturn.clinicscheduler.model.entity.constant.Gender;
import org.saturn.clinicscheduler.repository.DoctorRepository;
import org.saturn.clinicscheduler.repository.SpecialityRepository;
import org.saturn.clinicscheduler.service.impl.DoctorServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.saturn.clinicscheduler.model.DtoGeneratorTest.*;
import static org.saturn.clinicscheduler.model.EntityGeneratorTest.doctor;
import static org.saturn.clinicscheduler.model.EntityGeneratorTest.speciality;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceImplTest {

    private Doctor doctor;
    private DoctorInfoDto doctorInfoDto;
    private DoctorCreateDto doctorCreateDto;
    private Speciality speciality;
    private SpecialityDto specialityDto;

    @Mock
    DoctorRepository doctorRepository;

    @Mock
    DoctorMapper doctorMapper;

    @Mock
    SpecialityRepository specialityRepository;

    @Mock
    SpecialityMapper specialityMapper;

    @InjectMocks
    DoctorServiceImpl doctorService;

    @BeforeEach
    void initialize() {
        doctor = doctor();
        doctorInfoDto = doctorInfoDto();
        doctorCreateDto = doctorCreateDto();
        speciality = speciality();
        specialityDto = specialityDto();
    }

    void assertEqualsRequestAndResponse(DoctorCreateDto request, DoctorInfoDto response) {
        assertEquals(request.getName(), response.getName());
        assertEquals(request.getBirthdate(), response.getBirthdate());
        assertEquals(Gender.valueOf(request.getGender()), response.getGender());
        assertEquals(specialityRepository.findById(request.getSpecialityId()).get().getName(), response.getSpeciality());
        assertEquals(request.getWorksFrom(), response.getWorksFrom());
        assertEquals(request.getPhoneNumber(), response.getPhoneNumber());
    }

    @Test
    void createDoctor() {

        Mockito.when(doctorRepository.save(doctor)).thenReturn(doctor);

        Mockito.when(doctorMapper.mapToDoctor(doctorCreateDto, speciality)).thenReturn(doctor);

        Mockito.when(doctorMapper.mapToInfoDto(doctor)).thenReturn(doctorInfoDto);

        Mockito.when(specialityRepository.findById(1L)).thenReturn(Optional.ofNullable(speciality));

        DoctorInfoDto result = doctorService.createDoctor(doctorCreateDto);

        assertEqualsRequestAndResponse(doctorCreateDto, result);

        Mockito.verify(doctorRepository, Mockito.times(1)).save(doctor);
    }

}