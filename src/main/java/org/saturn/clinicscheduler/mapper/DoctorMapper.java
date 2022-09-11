package org.saturn.clinicscheduler.mapper;

import org.mapstruct.Mapper;
import org.saturn.clinicscheduler.model.dto.response.DoctorInfoDto;
import org.saturn.clinicscheduler.model.entity.Doctor;

import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    default DoctorInfoDto mapToInfoDto(Doctor doctor) {
        return DoctorInfoDto.builder()
                .name(doctor.getName())
                .birthdate(doctor.getBirthdate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                .gender(doctor.getGender())
                .speciality(doctor.getSpeciality().getName())
                .worksFrom(doctor.getWorksFromYear().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                .phoneNumber(doctor.getPhoneNumber())
                .build();
    }

}
