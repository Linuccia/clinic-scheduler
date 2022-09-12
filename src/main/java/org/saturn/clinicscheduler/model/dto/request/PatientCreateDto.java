package org.saturn.clinicscheduler.model.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PatientCreateDto {

    String name;
    LocalDate birthdate;
    String gender;
    String chiPolicy;
    String passport;
    String password;
    String phoneNumber;

}
