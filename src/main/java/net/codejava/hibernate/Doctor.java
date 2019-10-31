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
@Table(name = "doctor")
public class Doctor implements Serializable {

	@Column(name = "doctorId")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int doctorId;
	private String name;
	private String surname;
	private String email;
	private String pwdHash;

	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
	private final List<Examination> examinations = new ArrayList<>();

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
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
	
	public List<Examination> getExaminations() {
		return examinations;
	}

	@Override
	public String toString() {
		return "Doctor{" + "doctorId=" + doctorId + ", name=" + name + ", surname=" + surname + ", email=" + email + '}';
	}

	public Doctor(){
		
	}

	public Doctor(int doctorId, String name, String surname, String email, String pwdHash) {
		this.doctorId = doctorId;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.pwdHash = pwdHash;
	}
	
	
}
