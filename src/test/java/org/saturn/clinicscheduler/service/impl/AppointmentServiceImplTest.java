package org.saturn.clinicscheduler.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.saturn.clinicscheduler.mapper.AppointmentMapper;
import org.saturn.clinicscheduler.mapper.PatientMapper;
import org.saturn.clinicscheduler.model.EntityGeneratorTest;
import org.saturn.clinicscheduler.model.dto.response.AppointmentResponseDto;
import org.saturn.clinicscheduler.model.dto.response.PatientInfoDto;
import org.saturn.clinicscheduler.model.entity.Appointment;
import org.saturn.clinicscheduler.model.entity.Patient;
import org.saturn.clinicscheduler.model.entity.Schedule;
import org.saturn.clinicscheduler.repository.AppointmentRepository;
import org.saturn.clinicscheduler.repository.PatientRepository;
import org.saturn.clinicscheduler.repository.ScheduleRepository;

import java.sql.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.saturn.clinicscheduler.model.EntityGeneratorTest.*;
import static org.saturn.clinicscheduler.model.EntityGeneratorTest.appointment;
import static org.saturn.clinicscheduler.model.EntityGeneratorTest.patient;
import static org.saturn.clinicscheduler.model.EntityGeneratorTest.patientInfoDto;
import static org.saturn.clinicscheduler.model.EntityGeneratorTest.schedule;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceImplTest {

    @Mock
    private ScheduleRepository scheduleRepository;
    @Mock
    private AppointmentRepository appointmentRepository;
    @Mock
    private PatientRepository patientRepository;
    @Mock
    private PatientMapper patientMapper;
    @Mock
    private AppointmentMapper appointmentMapper;
    @InjectMocks
    private AppointmentServiceImpl appointmentService;

    private Appointment appointment;
    private Schedule schedule;
    private Patient patient;
    private PatientInfoDto patientInfoDto;
    private AppointmentResponseDto appointmentResponseDto;

    @BeforeEach
    void init() {
        appointment = appointment();
        schedule = schedule();
        patient = patient();
        patientInfoDto = patientInfoDto();
        appointmentResponseDto = appointmentResponseDto();
    }

    @Test
    void createAppointment() {
        Mockito.when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        Mockito.when(scheduleRepository.findById(1L)).thenReturn(Optional.of(schedule));
        Mockito.when(patientMapper.toInfoDto(patient)).thenReturn(patientInfoDto);
        Mockito.when(appointmentRepository.save(appointment)).thenReturn(appointment);
        Appointment appointmentWithoutId = appointment;
        appointmentWithoutId.setId(null);
        Mockito.when(appointmentMapper.toResponseDto(appointment, schedule, patientMapper.toInfoDto(patient)))
                .thenReturn(appointmentResponseDto);
        appointmentService.createAppointment(1l, 1L);
        Mockito.verify(scheduleRepository, Mockito.times(1)).save(schedule);
        Mockito.verify(appointmentRepository, Mockito.times(1)).save(appointmentWithoutId);
    }

    @Test
    void cancelAppointment() {
        Mockito.when(appointmentRepository.findById(appointment.getId()))
                .thenReturn(Optional.of(appointment));
        Mockito.when(scheduleRepository
                .findByDoctorAndDateAndStartTime(appointment.getDoctor(), new Date(appointment.getDate().getTime()),
                        appointment.getStartTime())).thenReturn(Optional.of(schedule));
        appointmentService.cancelAppointment(appointment.getId());
        assertTrue(schedule.getIsAvailable());
    }

}