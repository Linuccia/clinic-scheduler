package org.saturn.clinicscheduler.service;

import org.saturn.clinicscheduler.model.dto.request.DoctorCreateDto;
import org.saturn.clinicscheduler.model.dto.response.DoctorInfoDto;
import org.saturn.clinicscheduler.model.entity.Doctor;
import org.saturn.clinicscheduler.model.entity.Speciality;

import java.util.List;

public interface DoctorService {

    List<Speciality> getAllSpecialities();
<<<<<<< HEAD

    List<Doctor> getDoctorsBySpecialityId(Long id);

=======
    List<Doctor> getDoctorsBySpecialityId(Long id);
>>>>>>> 597acb0 (doctors by speciality)
    List<Doctor> getAllDoctors();
<<<<<<< HEAD

=======
    DoctorInfoDto createDoctor(DoctorCreateDto doctorDto);
>>>>>>> ffc8624 (add method create doctor)
}
