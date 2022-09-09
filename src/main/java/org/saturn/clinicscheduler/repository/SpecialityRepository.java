package org.saturn.clinicscheduler.repository;

import org.saturn.clinicscheduler.model.entity.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialityRepository extends JpaRepository<Speciality, Integer> {

}