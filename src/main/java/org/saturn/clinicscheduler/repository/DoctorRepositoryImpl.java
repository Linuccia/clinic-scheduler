package org.saturn.clinicscheduler.repository;

import org.hibernate.SessionFactory;
import org.saturn.clinicscheduler.model.entity.Speciality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DoctorRepositoryImpl implements DoctorRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public DoctorRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public List<Speciality> getAllSpecialities() {
        return sessionFactory.getCurrentSession().createQuery("select s from Speciality s", Speciality.class).list();
    }
}
