package org.saturn.clinicscheduler.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleUnpartitionedDto {

    private Long doctorId;
    private Long departmentId;
    private Date date;
    private Time startTime;
    private Time endTime;
    private Integer cabinet;

}
