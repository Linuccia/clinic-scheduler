package org.saturn.clinicscheduler.service;

import org.saturn.clinicscheduler.model.dto.response.DepartmentResponseDto;

import java.util.List;

public interface DepartmentService {
    List<DepartmentResponseDto> getAll();
}
