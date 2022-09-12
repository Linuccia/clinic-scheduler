package org.saturn.clinicscheduler.model.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRequestDto {

    private Long patientId;
    private Long doctorId;
    private LocalDate date;
    private LocalTime time;

}
