package org.saturn.clinicscheduler.service;

import java.util.List;
import org.saturn.clinicscheduler.model.dto.response.AppointmentResponseDto;

public interface AppointmentService {
    AppointmentResponseDto createAnAppointment(Long patientId, Long scheduleId);

    List<AppointmentResponseDto> getAllAppointmentsByPatient(Long patientId);

    List<AppointmentResponseDto> getAllAppointmentsByDoctor(Long doctorId);
}
