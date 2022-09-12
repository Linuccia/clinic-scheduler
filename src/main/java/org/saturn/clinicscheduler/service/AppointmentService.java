package org.saturn.clinicscheduler.service;

import org.saturn.clinicscheduler.model.dto.response.AppointmentResponseDto;

public interface AppointmentService {
    AppointmentResponseDto createAnAppointment(Long patientId, Long scheduleId);
}
