package org.saturn.clinicscheduler.mapper;

import org.saturn.clinicscheduler.model.dto.response.DoctorInfoDto;
import org.saturn.clinicscheduler.model.entity.Doctor;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class DoctorMapper {

    public DoctorInfoDto toDto(Doctor doctor){
        return new DoctorInfoDto(doctor.getName(), doctor.getBirthdate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                doctor.getGender(), doctor.getSpeciality().getName(),
                doctor.getWorksFromYear().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), doctor.getPhoneNumber());
    }
}
