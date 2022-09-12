package org.saturn.clinicscheduler.service;

import org.saturn.clinicscheduler.model.dto.request.AppointmentRequestDto;
import org.saturn.clinicscheduler.model.entity.Appointment;

public interface AppointmentService {
    Appointment createAnAppointment(AppointmentRequestDto request);
}
