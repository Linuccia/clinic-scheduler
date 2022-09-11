package org.saturn.clinicscheduler.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DepartmentResponseDto {
    private String city;
    private String district;
    private String address;
    private String phoneNumber;
}
