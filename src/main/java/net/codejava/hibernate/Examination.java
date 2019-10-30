/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.codejava.hibernate;

import javax.persistence.*;

@Entity
@Table(name = "examination")
public class Examination  {
    @Column(name = "ExamId")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int examinationId;
       
    @ManyToOne(fetch = FetchType.EAGER)
    //@MapsId("patientId")
    @JoinColumn(name = "patient")
    private Patient patient;
    
    @ManyToOne(fetch = FetchType.EAGER)
   // @MapsId("doctorId")
    @JoinColumn(name = "doctor")
    private Doctor doctor;
    
    private String examDate;
    
    private String type;
    
    @Column(nullable = true)
    private String result;              //results might be unavailable
    
    public int getId() {
        return examinationId;
    }

    public void setId(int id) {
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
    
    public String getDate() {
        return examDate;
    }

    public void setDate(String date) {
        this.examDate = date;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

	@Override
	public String toString() {
		return "Examination{" + examinationId +  " date=" + examDate + ", type=" + type + ", result=" + result + '}';
	}
   
	public Examination(){
		
	}

	public Examination(int examinationId, Patient patient, Doctor doctor, String examDate, String type, String result) {
		this.examinationId = examinationId;
		this.patient = patient;
		this.doctor = doctor;
		this.examDate = examDate;
		this.type = type;
		this.result = result;
	}
	
	
   }
