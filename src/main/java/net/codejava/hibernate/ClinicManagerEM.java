package net.codejava.hibernate;

import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
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
	
	public void createPatient(String name, String surname, String sex, String city, String birthDate, String email, String taxCode, String pwd) {

		Patient patient = new Patient();
		patient.setSurname(surname);
		patient.setName(name);
		patient.setBirthDate(birthDate);
		patient.setCity(city);
		patient.setEmail(email);
		patient.setSex(sex);
		patient.setTaxCode(taxCode);
		patient.setPwdHash(Hash.getSHA256(pwd));

		try {
			entityManager = factory.createEntityManager();
			entityManager.getTransaction().begin();

			Query query = entityManager.createQuery("SELECT p FROM Patient p WHERE p.taxCode = '" + taxCode + "'");
			List<Patient> p = query.getResultList();
			if (p.isEmpty()) {
				entityManager.persist(patient);
			} else {
				throw new EntityExistsException();
			}
			entityManager.getTransaction().commit();
		} catch (EntityExistsException e) {
			System.out.println("createPatient - Duplicated taxCode (must be unique)");
                        entityManager.getTransaction().rollback();                        
		} catch (Exception e) {
			System.out.println("Exception in createPatient");
                        entityManager.getTransaction().rollback();
		} finally {			
			entityManager.close();
		}
	}

	public void updatePatientInfo(String taxCode, String city, String email, String pwd) {
		if(city == null && email == null && pwd == null)			//return if both blank (or prevent in gui class?)
			return;
		try {
			entityManager = factory.createEntityManager();
			entityManager.getTransaction().begin();

			//find that patient
			Query query = entityManager.createQuery("SELECT p FROM Patient p WHERE p.taxCode = '" + taxCode + "'");
			List<Patient> patientList = query.getResultList();
			if (!patientList.isEmpty()) {
				Patient p = patientList.get(0);				
				//able to update also 1 single field if you leave the other blank
				if(city != null)
					p.setCity(city);						//update city
				if(email != null)
					p.setEmail(email);					//update email
				if(pwd != null)
					p.setPwdHash(Hash.getSHA256(pwd));		//				
				
				entityManager.merge(p);					//merge it				
			} else {
				throw new EntityNotFoundException();
			}                        
			entityManager.getTransaction().commit();		//commit
		} catch (EntityNotFoundException e) {
			System.out.println("updatePatientInfo - taxCode not found");
                        entityManager.getTransaction().rollback();
		} catch (Exception e) {
			System.out.println("Exception in updatePatientInfo");
                        entityManager.getTransaction().rollback();
		} finally {
			entityManager.close();
		}
	}
	
	public Patient readPatient(String taxCode){                
		Patient p = null;
		try{
			entityManager = factory.createEntityManager();
			entityManager.getTransaction().begin();

			Query query = entityManager.createQuery("SELECT p FROM Patient p WHERE p.taxCode = '" + taxCode + "'");
			List<Patient> patientList = query.getResultList();
			if(!patientList.isEmpty()){
				p = patientList.get(0);
			}
			else{
				throw new EntityNotFoundException();
			}

		} catch (EntityNotFoundException e) {
			System.out.println("readPatient -  taxCode not found");
		} catch (Exception e) {
			System.out.println("Exception in readPatient");
		} finally{
			entityManager.getTransaction().commit();
			entityManager.close();
			return p;
		}
	}
	
	public boolean loginPatient(String taxCode, String pwd){
                if(taxCode == null || pwd == null)
                    return false;
                
		String pwdHash = Hash.getSHA256(pwd);
		Patient p = readPatient(taxCode);
		if(p == null) {	//no patient found
                    System.out.println("Wrong taxcode");
                    return false;
                }
		String patientPwdHash = p.getPwdHash();
		if(pwdHash.equals(patientPwdHash)){
                    return true;
		}
		else{
                    System.out.println("Wrong password");
                    return false;	//pwd not matching
		}
	}
	
	public List<Examination> readPatientExaminations(String taxCode){
                Patient p = null;
		try{
			entityManager = factory.createEntityManager();
			entityManager.getTransaction().begin();

			Query query = entityManager.createQuery("SELECT p FROM Patient p WHERE p.taxCode = '" + taxCode + "'");
			List<Patient> patientList = query.getResultList();
			if(!patientList.isEmpty()){
				p = patientList.get(0);
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
                        if(p!=null)
                            return p.getExaminations();
                        else
                            return null;
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
			entityManager.getTransaction().commit();
		} catch(EntityNotFoundException e){
			System.out.println("deletePatient -  not found");
                        entityManager.getTransaction().rollback();
		}catch (Exception ex) {
			System.out.println("Exception in deletePatient");
                        entityManager.getTransaction().rollback();
		} finally {
			entityManager.close();
		}

	}

	public void deleteExamination(String examinationId) {
		//due to CASCADE property, when you delete a patient you also delete all the 
		//examination he had
		try {
			entityManager = factory.createEntityManager();
			entityManager.getTransaction().begin();
			
			Query query = entityManager.createQuery("SELECT e FROM Examination e WHERE e.examinationId = '" + examinationId + "'");
			List<Examination> examList = query.getResultList();
			if (examList.size() == 1) {
				Examination e = examList.get(0);
				entityManager.remove(e);		
			} else {
				throw new EntityNotFoundException();
			}
			entityManager.getTransaction().commit();
		} catch(EntityNotFoundException e){
			System.out.println("deleteExam -  not found");
                        entityManager.getTransaction().rollback();
		}catch (Exception ex) {
			System.out.println("Exception in deleteExam");
			entityManager.getTransaction().rollback();
		} finally {
			entityManager.close();
		}

	}
	
	//-----------------END OF PATIENT METHODS
	
	//-----------------DOCTOR METHODS
	
	public void createDoctor(int doctorId, String name, String surname, String email, String pwd) {
		Doctor doctor = new Doctor();
		doctor.setSurname(surname);
		doctor.setName(name);
		doctor.setEmail(email);
		doctor.setPwdHash(Hash.getSHA256(pwd));
		try {
			entityManager = factory.createEntityManager();
			entityManager.getTransaction().begin();

			Doctor d = entityManager.find(Doctor.class, doctorId);
			if (d == null) {
				entityManager.persist(doctor);
			} else {
				throw new EntityExistsException();
			}
			entityManager.getTransaction().commit();
		} catch (EntityExistsException e) {
			System.out.println("createDoctor - Entity already exists");
                        entityManager.getTransaction().rollback();
		} catch (Exception e) {
			System.out.println("Exception in createDoctor");
                        entityManager.getTransaction().rollback();
		} finally {
			entityManager.close();
		}
	}
	
	public void updateDoctorInfo(int doctorId, String email, String pwd) {
		if(email == null && pwd == null)			//return if blank (or prevent in gui class?)
			return;
		try {
			entityManager = factory.createEntityManager();
			entityManager.getTransaction().begin();

			//find that doctor			
			Doctor d = entityManager.find(Doctor.class, doctorId);		
			if (d != null) {			
				if(email!=null)
					d.setEmail(email);					//update email				
				if(pwd!=null)
					d.setPwdHash(Hash.getSHA256(pwd));		//update pwd
				entityManager.merge(d);					//merge it				
			} else {
				throw new EntityNotFoundException();
			}
			entityManager.getTransaction().commit();		//commit
		} catch (EntityNotFoundException e) {
			System.out.println("updateDoctorInfo - doctorId not found");
                        entityManager.getTransaction().rollback();
		} catch (Exception e) {
			System.out.println("Exception in updateDoctorInfo");
                        entityManager.getTransaction().rollback();
		} finally {
			entityManager.close();
		}
	}
	
	public Doctor readDoctor(int doctorId){                
		Doctor d = null;
		try{
			entityManager = factory.createEntityManager();
			entityManager.getTransaction().begin();

			d = entityManager.find(Doctor.class, doctorId);
			if(d == null){
				throw new EntityNotFoundException();
			}
		} catch (EntityNotFoundException e) {
			System.out.println("readDoctor -  doctorId not found");
		} catch (Exception e) {
			System.out.println("Exception in doctorId");
		} finally{
			entityManager.getTransaction().commit();
			entityManager.close();
			return d;
		}
	}
	
	public boolean loginDoctor(int doctorId, String pwd){
                if(doctorId == -1 || pwd == null)
                    return false;
                
		String pwdHash = Hash.getSHA256(pwd);
		Doctor d = readDoctor(doctorId);
		if(d == null) {	//no doctor found
                    System.out.println("Wrong ID");
                    return false;
                }
		String doctorPwdHash = d.getPwdHash();
		if(pwdHash.equals(doctorPwdHash)){	
                    return true;
		}
		else{
                    System.out.println("Wrong password");
                    return false;	//pwd not matching
		}
	}

	public void createExamination(int patientId, int doctorId, String type, String result, String examDate) {


		Examination examination = new Examination();
		examination.setType(type);
                examination.setDate(examDate);
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
			entityManager.persist(examination);
			entityManager.getTransaction().commit();	//commit                        
		} catch (EntityExistsException e) {
			System.out.println("createExamination - Entity already exists");
                        entityManager.getTransaction().rollback();
		} catch (Exception e) {
			System.out.println("Exception in createExamination");
                        entityManager.getTransaction().rollback();
		} finally {
			entityManager.close();
		}
	}

	public void updateExamination(int id, String result) {
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
			entityManager.getTransaction().commit();
		} catch (EntityNotFoundException e) {
			System.out.println("updateExamination - not found");
                        entityManager.getTransaction().rollback();
		} catch(Exception e){
			System.out.println("Exception in updateExamination");
                        entityManager.getTransaction().rollback();
		}finally {
			entityManager.close();//the entity manager must be always closed
		}
	}
	
	public List<Examination> readDoctorExaminations(int doctorId){
                Doctor d = null;
		try{
			entityManager = factory.createEntityManager();
			entityManager.getTransaction().begin();

			d = entityManager.find(Doctor.class, doctorId);
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
                    if(d!=null) 
                        return d.getExaminations();
                    else 
                        return null;
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
			entityManager.getTransaction().commit();
		} catch(EntityNotFoundException e){
			System.out.println("deleteDoctor -  not found");
                        entityManager.getTransaction().rollback();
		}catch (Exception ex) {
			System.out.println("Exception in deleteDoctor");
                        entityManager.getTransaction().rollback();

		} finally {
			entityManager.close();
		}

	}
	
	//-----------------END OF DOCTOR METHODS

	
	/*
	//-----------------MAIN METHOD
	public static void main(String[] args) {
                
		
		// code to run the program
		ClinicManagerEM manager = new ClinicManagerEM();
		manager.setup();

		MainWindow m = new MainWindow(manager);
		m.setVisible(true);
                
		//create patients
		//create doctor
		manager.createDoctor(1, "Jack", "The Reaper", "aaa@bb.cc", "doc1");
		manager.createDoctor(2, "Lord", "Voldemort", "tom.riddle@student.hogwarts.uk", "doc2");
		
		System.out.println("-----");
		//manager.deletePatient("duc1");
		//manager.deleteDoctor(1);
		
		System.out.println("Finished");

	}
        */
}
