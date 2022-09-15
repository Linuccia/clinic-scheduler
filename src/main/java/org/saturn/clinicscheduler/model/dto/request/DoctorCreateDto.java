package org.saturn.clinicscheduler.model.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DoctorCreateDto {

    private String name;
    private String birthdate;
    private String gender;
    private Long specialityId;
    private String worksFrom;
    private String phoneNumber;
    private String password;
}
