/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.codejava.hibernate;

import java.io.*;
import java.util.*;
import javax.persistence.*;

@Embeddable
public class ExaminationId implements Serializable {
    @Column(name = "patientId")
    private int patientId;
 
    @Column(name = "doctorId")
    private int doctorId;
    
    @Column(name = "examDate")
    private String examDate;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        ExaminationId that = (ExaminationId) o;
        return Objects.equals(patientId, that.patientId) &&
               Objects.equals(doctorId, that.doctorId) && Objects.equals(examDate, that.examDate);
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }
    
    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientId, doctorId, examDate);
    }  
}