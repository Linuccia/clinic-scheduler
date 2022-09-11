package org.saturn.clinicscheduler.service.impl;

import lombok.RequiredArgsConstructor;
import org.saturn.clinicscheduler.exception.DepartmentNotFoundException;
import org.saturn.clinicscheduler.exception.DoctorNotFoundException;
import org.saturn.clinicscheduler.mapper.ScheduleMapper;
import org.saturn.clinicscheduler.model.dto.request.ScheduleUnpartitionedDto;
import org.saturn.clinicscheduler.model.dto.response.ScheduleResponseDto;
import org.saturn.clinicscheduler.model.entity.Department;
import org.saturn.clinicscheduler.model.entity.Doctor;
import org.saturn.clinicscheduler.model.entity.Schedule;
import org.saturn.clinicscheduler.repository.DepartmentRepository;
import org.saturn.clinicscheduler.repository.DoctorRepository;
import org.saturn.clinicscheduler.repository.ScheduleRepository;
import org.saturn.clinicscheduler.service.ScheduleService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final DoctorRepository doctorRepository;
    private final DepartmentRepository departmentRepository;
    private final ScheduleMapper mapper;

    @Override
    public List<ScheduleResponseDto> addSchedule(ScheduleUnpartitionedDto scheduleUnpartitionedDto) {
        Doctor doctor = doctorRepository.findById(scheduleUnpartitionedDto.getDoctorId())
                .orElseThrow(DoctorNotFoundException::new);
        Department department = departmentRepository.findById(scheduleUnpartitionedDto.getDepartmentId())
                .orElseThrow(DepartmentNotFoundException::new);
        List<Schedule> schedules = mapper.mapToSchedules(scheduleUnpartitionedDto, doctor, department);
        scheduleRepository.saveAllAndFlush(schedules);
        return mapper.mapToResponseDtoList(schedules);
    }

    @Override
    public List<ScheduleResponseDto> getAllDoctorSchedules(Long doctorId) {
        return null;
    }

}
