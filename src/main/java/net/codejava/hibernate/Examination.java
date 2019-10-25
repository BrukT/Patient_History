/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.codejava.hibernate;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.*;

@Entity
@Table(name = "examination")
public class Examination implements Serializable {
    @EmbeddedId
    private ExaminationId examinationId;
       
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("patientId")
    @JoinColumn(name = "patient")
    private Patient patient;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("doctorId")
    @JoinColumn(name = "doctor")
    private Doctor doctor;
    
    private String type;
    
    @Column(nullable = true)
    private String result;              //results might be unavailable
    
    public ExaminationId getId() {
        return examinationId;
    }

    public void setId(ExaminationId id) {
        this.examinationId = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
            return "Examination{" + "date=" + examinationId.getExamDate() + ", type=" + type + ", result=" + result + '}';
    }

}
