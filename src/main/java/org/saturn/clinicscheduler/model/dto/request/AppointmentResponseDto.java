package org.saturn.clinicscheduler.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.saturn.clinicscheduler.model.dto.response.PatientInfoDto;

import java.sql.Time;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponseDto {
    private String doctorName;
    private String departmentAddress;
    private Integer cabinet;
    private PatientInfoDto patient;
    private Date date;
    private Time startTime;
}
