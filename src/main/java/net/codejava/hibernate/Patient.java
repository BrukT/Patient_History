/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.codejava.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "patient")
public class Patient implements Serializable{

	@Column(name = "patientId")
	@Id	
	private String patientId;
	private String name;
	private String surname;
	private String sex;
	private String birthDate;
	private String city;
	private String email;
	private String pwdHash;

	@OneToMany(mappedBy = "patient", orphanRemoval = true, fetch = FetchType.LAZY)
	private final List<Examination> examinations = new ArrayList<>();

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwdHash() {
		return pwdHash;
	}

	public void setPwdHash(String pwdHash) {
		this.pwdHash = pwdHash;
	}

	@Override
	public String toString() {
		return "Patient{" + "patientId=" + patientId + ", name=" + name + ", surname=" + surname + ", sex=" + sex + ", birthDate=" + birthDate + ", city=" + city + ", email=" + email + '}';
	}

	public List<Examination> getExaminations() {
		return examinations;
	}

	public Patient(){
		
	}
	
	public Patient(String taxCode, String name, String surname, String sex, String birthDate, String city, String email, String pwdHash) {		
		this.patientId = taxCode;
		this.name = name;
		this.surname = surname;
		this.sex = sex;
		this.birthDate = birthDate;
		this.city = city;
		this.email = email;
		this.pwdHash = pwdHash;
	}

}
