package org.saturn.clinicscheduler.repository;

import lombok.RequiredArgsConstructor;
import org.saturn.clinicscheduler.model.entity.Doctor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DoctorRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    private DoctorRepository doctorRepository;

    List<Doctor> getDoctorsBySpeciality(Long id){

        String hql = "select d from Doctor d join Speciality s on d.id = s.id where s.id = :id";
        TypedQuery<Doctor> query = entityManager.createQuery(hql, Doctor.class);
        query.setParameter("id", id);
        return query.getResultList();
    }
}
