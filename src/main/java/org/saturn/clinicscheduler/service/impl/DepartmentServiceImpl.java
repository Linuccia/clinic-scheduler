package org.saturn.clinicscheduler.service.impl;

import lombok.RequiredArgsConstructor;
import org.saturn.clinicscheduler.mapper.DepartmentMapper;
import org.saturn.clinicscheduler.model.dto.response.DepartmentResponseDto;
import org.saturn.clinicscheduler.repository.DepartmentRepository;
import org.saturn.clinicscheduler.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    public List<DepartmentResponseDto> getAll() {
        return departmentMapper.mapToResponseDtoList(departmentRepository.findAll());
    }
}
