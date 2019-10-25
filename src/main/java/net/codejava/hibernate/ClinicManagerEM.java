package net.codejava.hibernate;

import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.lang.Exception;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.EntityNotFoundException;

public class ClinicManagerEM {

	private EntityManagerFactory factory;
	private EntityManager entityManager;

	//-----------------UTILITY METHODS
	
	public void setup() {
		factory = Persistence.createEntityManagerFactory("clinic");
	}

	public void exit() {
		factory.close();
	}

	//-----------------END OF UTILITY METHODS
	
	//-----------------PATIENT METHODS
	
	public void createPatient(String name, String surname, String sex, String city, String birthDate, String email, String taxCode) {

		Patient patient = new Patient();
		patient.setSurname(surname);
		patient.setName(name);
		patient.setBirthDate(birthDate);
		patient.setCity(city);
		patient.setEmail(email);
		patient.setSex(sex);
		patient.setTaxCode(taxCode);

		try {
			entityManager = factory.createEntityManager();
			entityManager.getTransaction().begin();

			Query query = entityManager.createQuery("SELECT p FROM Patient p WHERE p.taxCode = '" + taxCode + "'");
			List<Patient> p = query.getResultList();
			if (p.size() == 0) {
				entityManager.persist(patient);
			} else {
				throw new EntityExistsException();
			}

		} catch (EntityExistsException e) {
			System.out.println("createPatient - Duplicated taxCode (must be unique)");
		} catch (Exception e) {
			System.out.println("Exception in createPatient");
		} finally {			
			entityManager.getTransaction().commit();
			entityManager.close();
		}
	}

	public void updatePatientInfo(String taxCode, String city, String email) {
		if(city == null && email == null)			//return if both blank (or prevent in gui class?)
			return;
		try {
			entityManager = factory.createEntityManager();
			entityManager.getTransaction().begin();

			//find that patient
			Query query = entityManager.createQuery("SELECT p FROM Patient p WHERE p.taxCode = '" + taxCode + "'");
			List<Patient> patientList = query.getResultList();
			if (patientList.size() != 0) {
				Patient p = patientList.get(0);				
				//able to update also 1 single field if you leave the other blank
				if(city != null)
					p.setCity(city);						//update city
				if(email != null)
					p.setEmail(email);					//update email
				
				entityManager.merge(p);					//merge it				
			} else {
				throw new EntityNotFoundException();
			}
		} catch (EntityNotFoundException e) {
			System.out.println("updatePatientInfo - taxCode not found");
		} catch (Exception e) {
			System.out.println("Exception in updatePatientInfo");
		} finally {
			entityManager.getTransaction().commit();		//commit
			entityManager.close();
		}
	}
	
	public void readPatientExaminations(String taxCode){
		try{
			entityManager = factory.createEntityManager();
			entityManager.getTransaction().begin();

			Query query = entityManager.createQuery("SELECT p FROM Patient p WHERE p.taxCode = '" + taxCode + "'");
			List<Patient> patientList = query.getResultList();
			if(patientList.size() != 0){
				Patient p = patientList.get(0);
				System.out.println("Patient: "+p.getName()+" "+p.getSurname());
				p.getExaminations().forEach((x) -> {
					System.out.println(x);
				});				
			}
			else{
				throw new EntityNotFoundException();
			}

		} catch (EntityNotFoundException e) {
			System.out.println("ReadPatientExaminations -  taxCode not found");
		} catch (Exception e) {
			System.out.println("Exception in readPatientExaminations");
		} finally{
			entityManager.getTransaction().commit();
			entityManager.close();
		}
	}
	
	public void deletePatient(String taxCode) {
		//due to CASCADE property, when you delete a patient you also delete all the 
		//examination he had
		try {
			entityManager = factory.createEntityManager();
			entityManager.getTransaction().begin();
			
			Query query = entityManager.createQuery("SELECT p FROM Patient p WHERE p.taxCode = '" + taxCode + "'");
			List<Patient> patientList = query.getResultList();
			if (patientList.size() == 1) {
				Patient p = patientList.get(0);
				entityManager.remove(p);				
			} else {
				throw new EntityNotFoundException();
			}
		} catch(EntityNotFoundException e){
			System.out.println("deletePatient -  not found");
		}catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Exception in deletePatient");

		} finally {
			entityManager.getTransaction().commit();
			entityManager.close();
		}

	}
	
	//-----------------END OF PATIENT METHODS
	
	//-----------------DOCTOR METHODS
	
	public void createDoctor(int doctorId, String name, String surname, String email) {
		Doctor doctor = new Doctor();
		doctor.setSurname(surname);
		doctor.setName(name);
		doctor.setEmail(email);
		try {
			entityManager = factory.createEntityManager();
			entityManager.getTransaction().begin();

			Doctor d = entityManager.find(Doctor.class, doctorId);
			if (d == null) {
				entityManager.persist(doctor);
			} else {
				throw new EntityExistsException();
			}
		} catch (EntityExistsException e) {
			System.out.println("createDoctor - Entity already exists");
		} catch (Exception e) {
			System.out.println("Exception in createDoctor");
		} finally {
			entityManager.getTransaction().commit();
			entityManager.close();
		}
	}
	
	public void updateDoctorInfo(int doctorId, String email) {
		if(email == null)			//return if blank (or prevent in gui class?)
			return;
		try {
			entityManager = factory.createEntityManager();
			entityManager.getTransaction().begin();

			//find that doctor			
			Doctor d = entityManager.find(Doctor.class, doctorId);		
			if (d != null) {				
				d.setEmail(email);						//update email				
				entityManager.merge(d);					//merge it				
			} else {
				throw new EntityNotFoundException();
			}
		} catch (EntityNotFoundException e) {
			System.out.println("updateDoctorInfo - doctorId not found");
		} catch (Exception e) {
			System.out.println("Exception in updateDoctorInfo");
		} finally {
			entityManager.getTransaction().commit();		//commit
			entityManager.close();
		}
	}

	public void createExamination(int patientId, int doctorId, String type, String result, String examDate) {
		ExaminationId examinationId = new ExaminationId();
		examinationId.setPatientId(patientId);
		examinationId.setDoctorId(doctorId);
		examinationId.setExamDate(examDate);

		Examination examination = new Examination();
		examination.setId(examinationId);       //important
		examination.setType(type);
		if (result != null)				//leaving result field blank in gui won't insert any result (null)
			examination.setResult(result);
		

		try {
			entityManager = factory.createEntityManager();
			entityManager.getTransaction().begin();

			//find the patient
			Patient patient = entityManager.find(Patient.class, patientId);
			if (patient == null) {
				throw new Exception();
			}
			examination.setPatient(patient);

			//find the doctor
			Doctor doctor = entityManager.find(Doctor.class, doctorId);
			if (doctor == null) {
				throw new Exception();
			}
			examination.setDoctor(doctor);

			Examination e = entityManager.find(Examination.class, examinationId);
			if (e == null) {
				entityManager.persist(examination);
			} else {
				throw new EntityExistsException();
			}
		} catch (EntityExistsException e) {
			System.out.println("createExamination - Entity already exists");
		} catch (Exception e) {
			System.out.println("Exception in createExamination");
		} finally {
			entityManager.getTransaction().commit();	//commit
			entityManager.close();
		}
	}

	public void updateExamination(ExaminationId id, String result) {
		System.out.println("--Updating a Examination");
		try {
			entityManager = factory.createEntityManager();
			entityManager.getTransaction().begin();
			Examination examination = entityManager.find(Examination.class, id);
			if(examination != null){
				examination.setResult(result);
				entityManager.merge(examination);
			}
			else{
				throw new EntityNotFoundException();
			}
		} catch (EntityNotFoundException e) {
			System.out.println("updateExamination - not found");

		} catch(Exception e){
			System.out.println("Exception in updateExamination");
		}finally {
			entityManager.getTransaction().commit();
			entityManager.close();//the entity manager must be always closed
		}
	}

	public void readDoctorExaminations(int doctorId){
		try{
			entityManager = factory.createEntityManager();
			entityManager.getTransaction().begin();

			Doctor d = entityManager.find(Doctor.class, doctorId);
			if(d != null){
				System.out.println("Doctor " + d.getName() + " " + d.getSurname());
				d.getExaminations().forEach((x) -> {
					System.out.println(x);
				});				
			}
			else{
				throw new EntityNotFoundException();
			}

		} catch (EntityNotFoundException e) {
			System.out.println("ReadDoctorExaminations -  not found");
		} catch (Exception e) {
			System.out.println("Exception in readDoctorExaminations");
		} finally{
			entityManager.getTransaction().commit();
			entityManager.close();
		}
	}
	
	public void deleteDoctor(int doctorId) {
		//due to CASCADE property, when you delete a doctor you also delete all the 
		//examination he has performed
		try {
			entityManager = factory.createEntityManager();
			entityManager.getTransaction().begin();
			
			Doctor d = entityManager.find(Doctor.class, doctorId);
			if(d != null){
				entityManager.remove(d);				
			}
			else {
				throw new EntityNotFoundException();
			}
		} catch(EntityNotFoundException e){
			System.out.println("deleteDoctor -  not found");
		}catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Exception in deleteDoctor");

		} finally {
			entityManager.getTransaction().commit();
			entityManager.close();
		}

	}
	
	//-----------------END OF DOCTOR METHODS

	
	
	//-----------------MAIN METHOD
	public static void main(String[] args) {
                
		// code to run the program
		ClinicManagerEM manager = new ClinicManagerEM();
		manager.setup();

                MainWindow m = new MainWindow(manager);
                m.setVisible(true);
                
		//create patients
		manager.createPatient("pietro", "ducange", "female", "pisa", "yesterday", "pietroducange@plasmon.it", "duc1");				
		manager.createPatient("enzo", "mingozzi", "male", "pisa", "1 jan 1970", "enzomingozzi@skynet.com", "ming1");
		//create doctor
		manager.createDoctor(1, "Jack", "The Reaper", "aaa@bb.cc");
		manager.createDoctor(2, "Lord", "Voldemort", "tom.riddle@student.hogwarts.uk");
		//create examinations
		manager.createExamination(1, 1, "headache", null, "5 nov");
		manager.createExamination(1, 1, "fever", "almost fine", "6 nov");
		
		manager.updateDoctorInfo(1, "mymail@hospital.it");
		manager.updatePatientInfo("duc1", "livorno", null);
		manager.readPatientExaminations("duc1");
		System.out.println("-----");
		
		ExaminationId id = new ExaminationId();		
		id.setDoctorId(1);
		id.setPatientId(1);
		id.setExamDate("5 nov");
		manager.updateExamination(id, "positive");
		manager.readDoctorExaminations(1);
		
		System.out.println("-----");
		//manager.deletePatient("duc1");
		//manager.deleteDoctor(1);
		manager.exit();
		System.out.println("Finished");

	}
}
