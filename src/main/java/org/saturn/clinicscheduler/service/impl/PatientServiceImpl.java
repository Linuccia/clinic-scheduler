package org.saturn.clinicscheduler.service.impl;

import lombok.RequiredArgsConstructor;
import org.saturn.clinicscheduler.exception.BusyPassportException;
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
        Patient patient = checkPatient(id);

        return patientMapper.toInfoDto(patient);
    }

    @Override
    @Transactional
    public PatientInfoDto createPatient(PatientCreateDto patientCreateDto) {
        throwIfPhoneBusy(patientCreateDto);
        throwIfPassportBusy(patientCreateDto);
        Patient patientCreatedEntity = patientMapper.fromCreateDto(patientCreateDto);
        Patient patientSavedEntity = patientRepository.save(patientCreatedEntity);

        return patientMapper.toInfoDto(patientSavedEntity);
    }

    @Override
    public List<PatientInfoDto> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(patientMapper::toInfoDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PatientInfoDto updatePatient(Long id, PatientCreateDto patientCreateDto) {
        Patient patient = checkPatient(id);
        String newPassport = patientCreateDto.getPassport();
        String newPhone = patientCreateDto.getPhoneNumber();
        if (!patient.getPassport().equals(newPassport)) {
            throwIfPassportBusy(patientCreateDto);
        }
        if (!patient.getPhoneNumber().equals(newPhone)) {
            throwIfPhoneBusy(patientCreateDto);
        }
        Patient patientCreatedEntity = patientMapper.fromCreateDto(patientCreateDto, id);
        Patient patientSavedEntity = patientRepository.save(patientCreatedEntity);

        return patientMapper.toInfoDto(patientSavedEntity);
    }

    @Override
    public PatientInfoDto deletePatient(Long id) {
        PatientInfoDto patient = getPatient(id);
        patientRepository.deleteById(id);

        return patient;
    }

    private void throwIfPhoneBusy(PatientCreateDto patientCreateDto) {
        String phone = patientCreateDto.getPhoneNumber();
        patientRepository.findByPhoneNumber(phone).ifPresent((s) -> {
            throw new BusyPhoneNumberException();
        });
    }

    private void throwIfPassportBusy(PatientCreateDto patientCreateDto) {
        String passport = patientCreateDto.getPassport();
        patientRepository.findByPassport(passport).ifPresent((s) -> {
            throw new BusyPassportException();
        });
    }

    private Patient checkPatient(Long id) {
        return patientRepository
                .findById(id)
                .orElseThrow(() -> {
                    throw new PatientNotFoundException();
                });
    }

}
