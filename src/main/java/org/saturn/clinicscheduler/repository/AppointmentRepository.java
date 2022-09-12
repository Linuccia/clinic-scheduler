package org.saturn.clinicscheduler.repository;

import java.util.List;
import java.util.Optional;
import org.saturn.clinicscheduler.model.entity.Appointment;
import org.saturn.clinicscheduler.model.entity.Doctor;
import org.saturn.clinicscheduler.model.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findAllByPatient(Optional<Patient> patient);

    List<Appointment> findAllByDoctor(Optional<Doctor> doctor);
}
