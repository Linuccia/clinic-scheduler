package org.saturn.clinicscheduler.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.saturn.clinicscheduler.model.entity.Gender;


@Data
@AllArgsConstructor
public class DoctorInfoDto {

    private String name;
    private String birthdate;
    private Gender gender;
    private String speciality;
    private String worksFrom;
    private String phoneNumber;

}
