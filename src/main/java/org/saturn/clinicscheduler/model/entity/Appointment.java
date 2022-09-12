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
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "appointment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Column(name = "date")
    private Date date;

    @Column(name = "start_time")
    private Time startTime;

    public Appointment(Patient patient, Doctor doctor, Date date, Time startTime) {
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.startTime = startTime;
    }
}
