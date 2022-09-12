package org.saturn.clinicscheduler.service.impl;

import lombok.RequiredArgsConstructor;
import org.saturn.clinicscheduler.exception.PatientNotFoundException;
import org.saturn.clinicscheduler.exception.ScheduleSlotNotFoundException;
import org.saturn.clinicscheduler.mapper.PatientMapper;
import org.saturn.clinicscheduler.model.dto.request.AppointmentResponseDto;
import org.saturn.clinicscheduler.model.entity.Appointment;
import org.saturn.clinicscheduler.model.entity.Patient;
import org.saturn.clinicscheduler.model.entity.Schedule;
import org.saturn.clinicscheduler.repository.AppointmentRepository;
import org.saturn.clinicscheduler.repository.PatientRepository;
import org.saturn.clinicscheduler.repository.ScheduleRepository;
import org.saturn.clinicscheduler.service.AppointmentService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Primary
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final PatientRepository patientRepository;
    private final ScheduleRepository scheduleRepository;
    private final AppointmentRepository appointmentRepository;
    private final PatientMapper patientMapper;

    @Override
    public AppointmentResponseDto createAnAppointment(Long patientId, Long scheduleId){
        Optional<Patient> patient = patientRepository.findById(patientId);
        if(patient.isEmpty())throw new PatientNotFoundException();
        Optional<Schedule> timeSlot = scheduleRepository.findById(scheduleId);
        if(timeSlot.isEmpty())throw new ScheduleSlotNotFoundException();
        Schedule schedule = timeSlot.get();
        if(Boolean.FALSE.equals(schedule.getIsAvailable()))throw new ScheduleSlotNotFoundException();
        schedule.setIsAvailable(false);
        scheduleRepository.save(schedule);
        Appointment appointment = new Appointment(patient.get(), schedule.getDoctor(), schedule.getDate(), schedule.getStartTime());
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return new AppointmentResponseDto(savedAppointment.getDoctor().getName(),
                                            schedule.getDepartment().getAddress(),
                                            schedule.getCabinet(),
                                            patientMapper.toInfoDto(savedAppointment.getPatient()),
                                            savedAppointment.getDate(),
                                            savedAppointment.getStartTime());
    }
}
