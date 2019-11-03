package net.codejava.hibernate;

import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.EntityNotFoundException;
import javax.persistence.RollbackException;

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
        patient.setPatientId(taxCode);
        patient.setPwdHash(Hash.getSHA256(pwd));

        try {
            entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();

            entityManager.persist(patient);

            entityManager.getTransaction().commit();
        } catch (RollbackException e) {
            System.out.println("rollback exception (duplicate)");
        } catch (Exception e) {
            System.out.println("Exception in createPatient");
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    public void updatePatientInfo(String taxCode, String city, String email, String pwd) {
        if (city == null && email == null && pwd == null){ //return if both blank (or prevent in gui class?)
            return;
        }
        try {
            entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();

            //find that patient
            Patient p = entityManager.getReference(Patient.class, taxCode);
            if (p != null) {
                //able to update also 1 single field if you leave the other blank
                if (city != null) 
                    p.setCity(city);				//update city                
                if (email != null) 
                    p.setEmail(email);				//update email                
                if (pwd != null) 
                    p.setPwdHash(Hash.getSHA256(pwd));		//update pwd hash				
                
                entityManager.merge(p);				//merge it				
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
	
    public Patient readPatient(String taxCode) {
        Patient p = null;
        try {
            entityManager = factory.createEntityManager();
            //find that patient
            p = entityManager.find(Patient.class, taxCode);
            if (p == null) {
                throw new EntityNotFoundException();
            }
        } catch (EntityNotFoundException e) {
            System.out.println("readPatient -  taxCode not found");
        } catch (Exception e) {
            System.out.println("Exception in readPatient");
        } finally {
            entityManager.close();
            return p;
        }
    }

    public boolean loginPatient(String taxCode, String pwd) {        
        String pwdHash = Hash.getSHA256(pwd);
        Patient p = readPatient(taxCode);
        if (p == null) {	//no patient found
            System.out.println("Wrong taxcode");
            return false;
        }
        String patientPwdHash = p.getPwdHash();
        System.out.println(patientPwdHash + "  vs  "+pwdHash);
        if (pwdHash.equals(patientPwdHash)) {
            return true;
        } else {
            System.out.println("Wrong password");
            return false;	//pwd not matching
        }
    }
	
    public List<Examination> readPatientExaminations(String taxCode) {
        Patient p = null;
        List<Examination> list = null;
        try {
            entityManager = factory.createEntityManager();

            //find that patient
            p = entityManager.find(Patient.class, taxCode);
            if (p == null) {
                throw new EntityNotFoundException();
            }
            else{
                list = p.getExaminations();
            }
        } catch (EntityNotFoundException e) {
            System.out.println("ReadPatientExaminations -  taxCode not found");
        } catch (Exception e) {
            System.out.println("Exception in readPatientExaminations");
        } finally {
            entityManager.close();
            return list;
        }
    }

    public void deletePatient(String taxCode) {
        //due to CASCADE property, when you delete a patient you also delete all the 
        //examination he had
        try {
            entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();

            //find that patient
            Patient p = entityManager.getReference(Patient.class, taxCode);
            if (p != null) {
                entityManager.remove(p);
            } else {
                throw new EntityNotFoundException();
            }
            entityManager.getTransaction().commit();
        } catch (EntityNotFoundException e) {
            System.out.println("deletePatient -  not found");            
            entityManager.getTransaction().rollback();
        } catch (Exception ex) {
            System.out.println("Exception in deletePatient");
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    public void deleteExamination(int examinationId) {
        //due to CASCADE property, when you delete a patient you also delete all the 
        //examination he had
        try {
            entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();

            
            Examination e = entityManager.find(Examination.class, examinationId);
            if (e!=null) {
                System.out.println("a");
                entityManager.remove(e);
            } else {
                throw new EntityNotFoundException();
            }
            entityManager.getTransaction().commit();
        } catch (EntityNotFoundException ex) {
            System.out.println("deleteExamination - entity not found");
            entityManager.getTransaction().rollback();
        } catch (Exception ex) {
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

            entityManager.persist(doctor);

            entityManager.getTransaction().commit();
        } catch (RollbackException e) {
            System.out.println("rollback exception (duplicate)");
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Exception in createDoctor");
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }
	
    public void updateDoctorInfo(int doctorId, String email, String pwd) {
        if (email == null && pwd == null) //return if blank (or prevent in gui class?)
        {
            return;
        }
        try {
            entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();

            //find that doctor			
            Doctor d = entityManager.getReference(Doctor.class, doctorId);
            if (d != null) {
                if (email != null) {
                    d.setEmail(email);				//update email				
                }
                if (pwd != null) {
                    d.setPwdHash(Hash.getSHA256(pwd));		//update pwd
                }
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
	
    public Doctor readDoctor(int doctorId) {
        Doctor d = null;
        try {
            entityManager = factory.createEntityManager();

            d = entityManager.find(Doctor.class, doctorId);
            if (d == null) {
                throw new EntityNotFoundException();
            }
        } catch (EntityNotFoundException e) {
            System.out.println("readDoctor -  doctorId not found");
        } catch (Exception e) {
            System.out.println("Exception in doctorId");
        } finally {
            entityManager.close();
            return d;
        }
    }
	
	public boolean loginDoctor(int doctorId, String pwd){
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

    public void createExamination(String patientId, int doctorId, String type, String result, String examDate) {

        Examination examination = new Examination();
        examination.setType(type);
        examination.setDate(examDate);
        examination.setResult(result);
        try {
            entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();

            //find the patient
            Patient patient = entityManager.find(Patient.class, patientId);
            if (patient == null) {
                throw new EntityNotFoundException();
            }
            examination.setPatient(patient);

            //find the doctor
            Doctor doctor = entityManager.find(Doctor.class, doctorId);
            if (doctor == null) {
                throw new EntityNotFoundException();
            }
            examination.setDoctor(doctor);

            entityManager.persist(examination);
            entityManager.getTransaction().commit();	//commit                        
        } catch (EntityNotFoundException e) {
            System.out.println("createExamination - Entity not found");
            entityManager.getTransaction().rollback();
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

    public void updateExamination(int examinationId, String result) {
        try {
            entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();
            Examination e = entityManager.getReference(Examination.class, examinationId);
            if (e != null) {
                e.setResult(result);
                entityManager.merge(e);
            } else {
                throw new EntityNotFoundException();
            }
            entityManager.getTransaction().commit();
        } catch(EntityNotFoundException ex){
            System.out.println("Entity not found in updateExamination (id: "+examinationId+")");
            entityManager.getTransaction().rollback();
        } catch (Exception ex) {
            System.out.println("Exception in updateExamination");
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }
	
    public List<Examination> readDoctorExaminations(int doctorId) {
        Doctor d = null;        
        List<Examination> list = null;
        try {
            entityManager = factory.createEntityManager();

            d = entityManager.find(Doctor.class, doctorId);
            if (d == null) {
                throw new EntityNotFoundException();
            }            
            else{
                list = d.getExaminations();
            }
        } catch (EntityNotFoundException e) {
            System.out.println("ReadDoctorExaminations -  not found");
        } catch (Exception e) {
            System.out.println("Exception in readDoctorExaminations");
        } finally {
            entityManager.close();
            return list;
        }
    }
	
    public void deleteDoctor(int doctorId) {
        //due to CASCADE property, when you delete a doctor you also delete all the 
        //examination he has performed
        try {
            entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();

            Doctor d = entityManager.getReference(Doctor.class, doctorId);
            if (d != null) {
                entityManager.remove(d);
            } else {
                throw new EntityNotFoundException();
            }
            entityManager.getTransaction().commit();
        } catch (EntityNotFoundException e) {
            System.out.println("deleteDoctor - not found");
            entityManager.getTransaction().rollback();
        } catch (Exception ex) {
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
