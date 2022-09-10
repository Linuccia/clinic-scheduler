package org.saturn.clinicscheduler.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "doctor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "birthdate")
    private Date birthdate;

    @Column(name = "gender")
    private String gender;

    @ManyToOne
    @JoinColumn(name = "speciality_id")
    private Speciality speciality;

    @Column(name = "works_from_year")
    private Date worksFromYear;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "password")
    private String password;

}
