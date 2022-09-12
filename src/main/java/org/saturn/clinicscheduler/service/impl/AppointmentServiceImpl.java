package org.saturn.clinicscheduler.service.impl;

import lombok.RequiredArgsConstructor;
import org.saturn.clinicscheduler.exception.PatientNotFoundException;
import org.saturn.clinicscheduler.exception.ScheduleSlotNotFoundException;
import org.saturn.clinicscheduler.model.dto.request.AppointmentRequestDto;
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

    @Override
    public Appointment createAnAppointment(AppointmentRequestDto request){
        Optional<Patient> patient = patientRepository.findById(request.getPatientId());
        if(patient.isEmpty())throw new PatientNotFoundException();
        Optional<Schedule> timeSlot = scheduleRepository.findById(request.getScheduleId());
        if(timeSlot.isEmpty())throw new ScheduleSlotNotFoundException();
        Schedule schedule = timeSlot.get();
        if(Boolean.FALSE.equals(schedule.getIsAvailable()))throw new ScheduleSlotNotFoundException();
        schedule.setIsAvailable(false);
        scheduleRepository.save(schedule);
        Appointment appointment = new Appointment(patient.get(), schedule.getDoctor(), schedule.getDate(), schedule.getStartTime());
        return appointmentRepository.save(appointment);
    }
}
