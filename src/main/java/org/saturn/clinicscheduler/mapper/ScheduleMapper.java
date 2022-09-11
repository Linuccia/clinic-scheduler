package org.saturn.clinicscheduler.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.saturn.clinicscheduler.model.dto.request.ScheduleUnpartitionedDto;
import org.saturn.clinicscheduler.model.dto.response.ScheduleResponseDto;
import org.saturn.clinicscheduler.model.entity.Department;
import org.saturn.clinicscheduler.model.entity.Doctor;
import org.saturn.clinicscheduler.model.entity.Schedule;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", uses = ScheduleMapper.class)
public interface ScheduleMapper {

    @Mapping(target = "doctorName", expression = "java(schedule.getDoctor().getName())")
    @Mapping(target = "departmentAddress", expression = "java(schedule.getDepartment().getCity() + \", \" "
            + "+ schedule.getDepartment().getAddress())")
    ScheduleResponseDto mapToResponseDto(Schedule schedule);

    List<ScheduleResponseDto> mapToResponseDtoList(List<Schedule> schedules);

    default List<Schedule> mapToSchedules(ScheduleUnpartitionedDto scheduleUnpartitionedDto,
                                          Doctor doctor,
                                          Department department) {
        ArrayList<Schedule> schedules = new ArrayList<>();
        LocalTime startTime = scheduleUnpartitionedDto.getStartTime().toLocalTime();
        LocalTime endTime = scheduleUnpartitionedDto.getEndTime().toLocalTime();
        while (startTime.isBefore(endTime)) {
            Schedule schedule = Schedule.builder()
                    .doctor(doctor)
                    .department(department)
                    .date(scheduleUnpartitionedDto.getDate())
                    .startTime(Time.valueOf(startTime))
                    .endTime(Time.valueOf(startTime.plusHours(1)))
                    .cabinet(scheduleUnpartitionedDto.getCabinet())
                    .isAvailable(true)
                    .build();
            schedules.add(schedule);
            startTime = startTime.plusHours(1);
        }
        return schedules;
    }

}
