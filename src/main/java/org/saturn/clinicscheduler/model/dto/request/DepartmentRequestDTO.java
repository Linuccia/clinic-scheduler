package org.saturn.clinicscheduler.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentRequestDTO {
    private String city;
    private String district;
    private String address;
    private String phoneNumber;
}
