package org.saturn.clinicscheduler.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "speciality")
@Data
public class Speciality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
}
