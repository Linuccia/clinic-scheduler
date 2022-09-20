package org.saturn.clinicscheduler.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.saturn.clinicscheduler.model.entity.Appointment;
import org.saturn.clinicscheduler.model.entity.Schedule;
import org.saturn.clinicscheduler.repository.AppointmentRepository;
import org.saturn.clinicscheduler.repository.ScheduleRepository;

import java.sql.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.saturn.clinicscheduler.model.EntityGeneratorTest.appointment;
import static org.saturn.clinicscheduler.model.EntityGeneratorTest.schedule;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceImplTest {

    @Mock
    private ScheduleRepository scheduleRepository;
    @Mock
    private AppointmentRepository appointmentRepository;
    @InjectMocks
    private AppointmentServiceImpl appointmentService;

    private Appointment appointment;
    private Schedule schedule;

    @BeforeEach
    void init() {
        appointment = appointment();
        schedule = schedule();
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