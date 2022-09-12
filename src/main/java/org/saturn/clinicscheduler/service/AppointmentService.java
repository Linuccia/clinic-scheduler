package org.saturn.clinicscheduler.service;

import org.saturn.clinicscheduler.model.dto.request.AppointmentResponseDto;
import org.saturn.clinicscheduler.model.entity.Appointment;

public interface AppointmentService {
    AppointmentResponseDto createAnAppointment(Long patientId, Long scheduleId);
}
