package org.saturn.clinicscheduler.service.impl;

import lombok.RequiredArgsConstructor;
import org.saturn.clinicscheduler.exception.BusyPhoneNumberException;
import org.saturn.clinicscheduler.exception.PatientNotFoundException;
import org.saturn.clinicscheduler.mapper.PatientMapper;
import org.saturn.clinicscheduler.model.dto.request.PatientCreateDto;
import org.saturn.clinicscheduler.model.dto.response.PatientInfoDto;
import org.saturn.clinicscheduler.model.entity.Patient;
import org.saturn.clinicscheduler.repository.PatientRepository;
import org.saturn.clinicscheduler.service.PatientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientMapper patientMapper;

    private final PatientRepository patientRepository;

    @Override
    public PatientInfoDto getPatient(Long id) {
        Patient patient = patientRepository
                .findById(id)
                .orElseThrow(() -> {
                    throw new PatientNotFoundException();
                });
        return patientMapper.toInfoDto(patient);
    }

    @Override
    @Transactional
    public PatientInfoDto createPatient(PatientCreateDto patientCreateDto) {
        String phoneNumber = patientCreateDto.getPhoneNumber();
        patientRepository.findByPhoneNumber(phoneNumber).ifPresent(s -> {
            throw new BusyPhoneNumberException();
        });
        Patient patient = patientMapper.fromCreateDto(patientCreateDto);
        Patient patientEntity = patientRepository.save(patient);
        return patientMapper.toInfoDto(patientEntity);
    }

    @Override
    public List<PatientInfoDto> getAllPatient() {
        List<Patient> allPatientEntity = patientRepository.findAll();
        return allPatientEntity.stream()
                .map(patientMapper::toInfoDto)
                .collect(Collectors.toList());
    }
}
