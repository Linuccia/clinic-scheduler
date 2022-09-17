package org.saturn.clinicscheduler.service.impl;

import lombok.RequiredArgsConstructor;
import org.saturn.clinicscheduler.exception.DoctorNotFoundException;
import org.saturn.clinicscheduler.exception.PatientNotFoundException;
import org.saturn.clinicscheduler.exception.ScheduleSlotNotFoundException;
import org.saturn.clinicscheduler.mapper.AppointmentMapper;
import org.saturn.clinicscheduler.mapper.PatientMapper;
import org.saturn.clinicscheduler.model.dto.response.AppointmentResponseDto;
import org.saturn.clinicscheduler.model.entity.Appointment;
import org.saturn.clinicscheduler.model.entity.Doctor;
import org.saturn.clinicscheduler.model.entity.Patient;
import org.saturn.clinicscheduler.model.entity.Schedule;
import org.saturn.clinicscheduler.repository.AppointmentRepository;
import org.saturn.clinicscheduler.repository.DoctorRepository;
import org.saturn.clinicscheduler.repository.PatientRepository;
import org.saturn.clinicscheduler.repository.ScheduleRepository;
import org.saturn.clinicscheduler.service.AppointmentService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final ScheduleRepository scheduleRepository;
    private final AppointmentRepository appointmentRepository;
    private final PatientMapper patientMapper;
    private final AppointmentMapper appointmentMapper;

    @Override
    public AppointmentResponseDto createAppointment(Long patientId, Long scheduleId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(PatientNotFoundException::new);
        Schedule timeSlot = scheduleRepository.findById(scheduleId).orElseThrow(ScheduleSlotNotFoundException::new);
        if (Boolean.FALSE.equals(timeSlot.getIsAvailable())) {
            throw new ScheduleSlotNotFoundException();
        }
        timeSlot.setIsAvailable(false);
        scheduleRepository.save(timeSlot);
        Appointment appointment = Appointment.builder()
                .patient(patient)
                .doctor(timeSlot.getDoctor())
                .date(timeSlot.getDate())
                .startTime(timeSlot.getStartTime())
                .build();
        appointment = appointmentRepository.save(appointment);

        return appointmentMapper.toResponseDto(appointment, timeSlot, patientMapper.toInfoDto(patient));
    }

    @Override
    public List<AppointmentResponseDto> getAllAppointmentsByPatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(PatientNotFoundException::new);

        return appointmentMapper.toAppointmentResponseDTOList(appointmentRepository.findAllByPatient(patient));
    }

    @Override
    public List<AppointmentResponseDto> getAllAppointmentsByDoctor(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(DoctorNotFoundException::new);

        return appointmentMapper.toAppointmentResponseDTOList(appointmentRepository.findAllByDoctor(doctor));
    }

}
