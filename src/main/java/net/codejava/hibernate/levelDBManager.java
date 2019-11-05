/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.codejava.hibernate;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.DBIterator;
import static org.iq80.leveldb.impl.Iq80DBFactory.factory;
import org.iq80.leveldb.Options;
import static org.iq80.leveldb.impl.Iq80DBFactory.asString;
import static org.iq80.leveldb.impl.Iq80DBFactory.bytes;

public class levelDBManager {

	private DB levelDBStore;
	private int doctor;
	private int examination;
	private int patient;
	
	//-----------------UTILITY METHODS
		
	public void init(String storeName){	//i.e. "mystore"
		try {
			Options options = new Options();
			levelDBStore = factory.open(new File(storeName), options);
						
			DBIterator iterator = levelDBStore.iterator();
			iterator.seek(bytes("examination:")); // starts from the specified key			
			examination = 0;
			while (iterator.hasNext()){
				byte[] key = iterator.peekNext().getKey();
				String[] keySplit = asString(key).split(":"); // split the key
				if (!keySplit[0].equals("examination")) { 
					break;
				}
				else{
					examination = Integer.parseInt(keySplit[1]);
				}
				iterator.next();				
			}
			
			iterator.seek(bytes("doctor:"));
			doctor = 0;
			while (iterator.hasNext()){
				byte[] key = iterator.peekNext().getKey();
				String[] keySplit = asString(key).split(":"); // split the key
				if (!keySplit[0].equals("doctor")) {
					break;
				}
				else{
					doctor = Integer.parseInt(keySplit[1]);
				}
				iterator.next();				
			}
			iterator.seek(bytes("patient:"));
			patient = 0;
			while (iterator.hasNext()){
				byte[] key = iterator.peekNext().getKey();
				String[] keySplit = asString(key).split(":"); // split the key
				if (!keySplit[0].equals("patientiId")) { 
					break;
				}
				else{
					patient = Integer.parseInt(keySplit[1]);
				}
				iterator.next();				
			}
			
		} catch (IOException ex) {
			Logger.getLogger(levelDBManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	//put get delete shortcut to avoid to bytes() and asString() everytime we call
	public void put(String key, String value){
		levelDBStore.put(bytes(key), bytes(value));	
	}
	
	public String get(String key){
		return asString(levelDBStore.get(bytes(key)));	//return the value as string
	}
	
	public void delete(String key){
		levelDBStore.delete(bytes(key));
	}
	
	public void dumpLevelDB(){
		System.out.println("------------- DUMP -----------");
		DBIterator iterator = levelDBStore.iterator();
		iterator.seekToFirst();
		while (iterator.hasNext()){
			byte[] key = iterator.peekNext().getKey();
			byte[] value = iterator.peekNext().getValue();
			System.out.println(asString(key) + "\t" + asString(value));
			iterator.next();
		}
		System.out.println("----------- END DUMP ---------");
	}
	
	public void close(){
		try {
			levelDBStore.close();			
		} catch (IOException ex) {
			Logger.getLogger(levelDBManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public int getDoctorId() {
		return doctor;
	}

	public int getExaminationId() {
		return examination;
	}
	
	public int getPatientId(){
		return patient;
	}
	
	public int incrementAndGetDoctorId(){
		doctor++;
		return  doctor;
	}
	
	public int incrementAndGetExaminationId(){
		examination++;
		return examination;
	}
	
	public int incrementAndGetPatientId(){
		patient++;
		return patient;
	}
	
	//-----------------END OF UTILITY METHODS
	
	//-----------------PATIENT METHODS
	
	public void putPatient(String name, String surname, String email, String taxCode) {
		String key = "patient:" + taxCode + ":";
		String s = get(key+"name");
		if (s != null) 
			return;
		put(key + "name", name);
		put(key + "surname", surname);
		put(key + "email", email);
	}
	
	public void updatePatientInfo(String taxCode, String email) {
		String key = "patient:" + taxCode + ":";
		if(email != null)
			put(key+"email", email);
	}
	
	public Patient readPatient(String taxCode){
		String myKey = "patient:" + taxCode + ":";
		DBIterator iterator = levelDBStore.iterator();
		iterator.seek(bytes(myKey)); // starts from the specified key
		ArrayList<String> param = new ArrayList<>();
		int i = 0;
		while (iterator.hasNext()){
			byte[] key = iterator.peekNext().getKey();
			// key arrangement : patient:$patient_id:$attribute_name = $value
			String[] keySplit = asString(key).split(":"); // split the key
			if (!keySplit[0].equals("patient") || !keySplit[1].equals(taxCode)) { // breaking condition : prefix is not "patient:$patient"
				break;
			}
			byte[] value = iterator.peekNext().getValue();
			param.add(asString(value));
			iterator.next();
			i++;
		}
		//taxCode, name, surname, email     -> no more
                Patient p = new Patient(taxCode, param.get(1), param.get(2), null, null, null, param.get(0), null);
		return p;
	}
	
	public String loginPatient(String taxCode, String pwd){
		String proposedPwdHash = Hash.getSHA256(pwd);
		String key = "patient:" + taxCode + ":password";	
		String pwdHash = get(key);	//null if no user	
		if(pwdHash != null && pwdHash.equals(proposedPwdHash))
			return taxCode;
		else
			return null;
	}
	
	public List<Examination> readPatientExamination(String taxCode){
		String myKey = "examination:";
		DBIterator iterator = levelDBStore.iterator();
		iterator.seek(bytes(myKey)); // starts from the specified key
		ArrayList<String> param = new ArrayList<>();
		final List<Examination> examinations = new ArrayList<>();
		int i = 0;
		while (iterator.hasNext()){
			byte[] key = iterator.peekNext().getKey();
			// key arrangement : examination:$examination_id:$patient_id:$doctor_id:$attribute_name = $value			
			String[] keySplit = asString(key).split(":"); // split the key
			if(!keySplit[0].equals("examination"))
				break;
			if(keySplit[2].equals(taxCode)){
				byte[] value = iterator.peekNext().getValue();
				//System.out.println("-> "+asString(key) + "\t|\t"+asString(value));
				param.add(asString(value));
				i++;
				if(i==3){
					Patient p = readPatient(keySplit[2]);
					Doctor d = readDoctor(Integer.parseInt(keySplit[3]));
					Examination e = new Examination(Integer.parseInt(keySplit[1]), p, d, param.get(0), param.get(2), param.get(1));
					examinations.add(e);
                                        param.clear();
					i=0;
				}
			}
			else{
				i = 0;
			}
			iterator.next();
			
		}
		
		return examinations;
	}
	
	public void deletePatient(String taxCode) {
		String myKey = "patient:" + taxCode + ":";
		DBIterator iterator = levelDBStore.iterator();
		iterator.seek(bytes(myKey)); // starts from the specified key
		while (iterator.hasNext()){
			byte[] key = iterator.peekNext().getKey();
			// key arrangement : doctor:$doctor_id:$attribute_name = $value
			String[] keySplit = asString(key).split(":"); // split the key
			if (!keySplit[0].equals("patient") || !keySplit[1].equals(taxCode)) { // breaking condition : prefix is not "patient:$patient"
				break;
			}
			delete(asString(key));
			iterator.next();
		}
	}
	
	public void deleteExaminations(String examination) {
		String myKey = "examination:" + examination + ":";
		DBIterator iterator = levelDBStore.iterator();
		iterator.seek(bytes(myKey)); // starts from the specified key
		while (iterator.hasNext()){
			byte[] key = iterator.peekNext().getKey();			
			String[] keySplit = asString(key).split(":"); // split the key
			if (!keySplit[0].equals("examination") || !keySplit[1].equals(examination)) { // breaking condition : prefix is not "examination:$examination"
				break;
			}
			delete(asString(key));			
			iterator.next();
		}
	}
	
	//-----------------END OF PATIENT METHODS
	
	//-----------------DOCTOR METHODS
	
	public void putDoctor(String name, String surname, String email){
		String key = "doctor:" + incrementAndGetDoctorId() + ":";
                
		DBIterator iterator = levelDBStore.iterator();
		iterator.seek(bytes("doctor:")); // starts from the specified key
		ArrayList<String> param = new ArrayList<>();
		int i = 0;
		while (iterator.hasNext()){
			byte[] myKey = iterator.peekNext().getKey();
			// key arrangement : doctor:$doctor_id:$attribute_name = $value
			
			String[] keySplit = asString(myKey).split(":"); // split the key
			if (!keySplit[0].equals("doctor")) { // breaking condition : prefix is not "doctor"
				break;
			}
			
                        
                        String value = asString(iterator.peekNext().getValue());
                        if(keySplit[2].equals("email")){
                            i=0;
                            if(value.equals(email))
                                i++;
                        }                                                
                        else if(keySplit[2].equals("name")){
                            if(value.equals(name))
                                i++;
                        }                        
                        else if(keySplit[2].equals("surname")){
                            if(value.equals(surname))
                                i++;
                        }                        
                        if(i==3){
                            return;
                        }                                            
			iterator.next();
                }
                		
		put(key+"name", name);
		put(key+"surname", surname);
		put(key+"email", email);	
	}
	
	public void updateDoctorInfo(int doctor, String email) {
		String key = "doctor:" + doctor + ":";		
		if(email != null)
			put(key+"email", email);
	}
	
	public Doctor readDoctor(int doctor){
		String myKey = "doctor:" + doctor + ":";
		DBIterator iterator = levelDBStore.iterator();
		iterator.seek(bytes(myKey)); // starts from the specified key
		ArrayList<String> param = new ArrayList<>();
		int i = 0;
		while (iterator.hasNext()){
			byte[] key = iterator.peekNext().getKey();
			// key arrangement : doctor:$doctor_id:$attribute_name = $value
			
			String[] keySplit = asString(key).split(":"); // split the key
			if (!keySplit[0].equals("doctor") || !keySplit[1].equals(Integer.toString(doctor))) { // breaking condition : prefix is not "doctor:$doctor"
				break;
			}
			
			byte[] value = iterator.peekNext().getValue();
			param.add(asString(value));
			//System.out.println(asString(key) + " | "+asString(value));
			iterator.next();
			i++;
		}
		
		Doctor d = new Doctor(doctor, param.get(1), param.get(2), param.get(0), null);
		return d;
	}
	
	public String loginDoctor(int doctor, String pwd){
		String proposedPwdHash = Hash.getSHA256(pwd);
		String key = "doctor:" + doctor + ":password";	
		String pwdHash = get(key);	//null if no user	
		if(pwdHash != null && pwdHash.equals(proposedPwdHash))
			return Integer.toString(doctor);
		else
			return null;
                
	}
        
	public void putExamination(String taxCode, int doctor, String type, String result, String examDate){
		String key = "examination:" + incrementAndGetExaminationId() + ":" + taxCode + ":" + doctor + ":";
		
		put(key+"examDate", examDate);
		put(key+"type", type);
		put(key+"result", result);
	}	
	
	public void updateExamination(int examination, String result){
		String myKey = "examination:" + examination + ":";
		DBIterator iterator = levelDBStore.iterator();
		iterator.seek(bytes(myKey)); // starts from the specified key		
		while (iterator.hasNext()){
			byte[] key = iterator.peekNext().getKey();
			// key arrangement : doctor:$doctor_id:$attribute_name = $value
			
			String[] keySplit = asString(key).split(":"); // split the key
			if (!keySplit[0].equals("examination") || !keySplit[1].equals(Integer.toString(examination))) { 
				break;
			}
			else if(keySplit[4].equals("result")){
				put(asString(key), result);
			}
			
			iterator.next();
		}
	}
	
	public List<Examination> readDoctorExamination(int doctor){
		String myKey = "examination:";
		DBIterator iterator = levelDBStore.iterator();
		iterator.seek(bytes(myKey)); // starts from the specified key
		ArrayList<String> param = new ArrayList<>();
		final List<Examination> examinations = new ArrayList<>();
		int i = 0;
		while (iterator.hasNext()){
			byte[] key = iterator.peekNext().getKey();
			// key arrangement : examination:$examination_id:$patient_id:$doctor_id:$attribute_name = $value			
			String[] keySplit = asString(key).split(":"); // split the key
			if(!keySplit[0].equals("examination"))
				break;
			if(keySplit[3].equals(Integer.toString(doctor))){
				byte[] value = iterator.peekNext().getValue();
				//System.out.println("-> "+asString(key) + "\t|\t"+asString(value));
				param.add(asString(value));
				i++;
				if(i==3){
					Patient p = readPatient(keySplit[2]);
					Doctor d = readDoctor(Integer.parseInt(keySplit[3]));
					Examination e = new Examination(Integer.parseInt(keySplit[1]), p, d, param.get(0), param.get(2), param.get(1));
					examinations.add(e);
                                        param.clear();
					//System.out.println("//");
					i=0;
				}
			}
			else{
				i = 0;
			}
			
			// whatever you want to do
			iterator.next();
			
		}
		
		return examinations;
	}
	
	public void deleteDoctor(int doctor) {
		String myKey = "doctor:" + doctor + ":";
		DBIterator iterator = levelDBStore.iterator();
		iterator.seek(bytes(myKey)); // starts from the specified key
		while (iterator.hasNext()){
			byte[] key = iterator.peekNext().getKey();
			// key arrangement : doctor:$doctor_id:$attribute_name = $value
			String[] keySplit = asString(key).split(":"); // split the key
			if (!keySplit[0].equals("doctor") || !keySplit[1].equals(Integer.toString(doctor))) { 
				break;
			}
			delete(asString(key));
			// whatever you want to do
			iterator.next();
		}
	}
	
	//-----------------END OF DOCTOR METHODS
		
        /*
	//-----------------MAIN METHOD
	
	public static void main(String[] args) {
		System.out.println("-----");
		
		levelDBManager l = new levelDBManager();
		l.init("mystore");
		System.out.println("Current= doc:"+l.getDoctorId()+"\tex:"+l.getExaminationId()+"\tpat:"+l.getPatientId());
		
		l.putPatient("nedo", "pisani", "aa.bb@das.c", "ned1");
		l.dumpLevelDB();
		Patient p = l.readPatient("ned1");
		System.out.println("-----");
		System.out.println(p);
		l.updatePatientInfo("ned1", "changed@gmail.com");
		p = l.readPatient("ned1");
		System.out.println("-----");
		System.out.println(p);
		
		System.out.println("--------------");
		l.putDoctor("serial", "killer", "reaper@live.it");
		Doctor d = l.readDoctor(1);		
		System.out.println(d);
		System.out.println("-----");
		l.putExamination("ned1", 1, "blood", "not available", "yesterday");
		l.putExamination("ned1", 1, "head", "positive", "20 oct");
		System.out.println("\n\n\n-----");
		System.out.println("-----");
		List<Examination> list = l.readPatientExamination("ned1");
		System.out.println("-----");
		System.out.println("-----");
		list.forEach((x) -> {
			System.out.println(x);
			System.out.println(x.getDoctor());
			System.out.println(x.getPatient());
		});
		
		
		l.dumpLevelDB();
		System.out.println("-----");
		System.out.println("-----\n\n\n");
		
		l.updateExamination(1, "good!!!");
		
		System.out.println("-----");
		System.out.println("-----");		
		l.dumpLevelDB();
				
		System.out.println("-----");		
		l.readDoctorExamination(1);
				
		l.dumpLevelDB();
		l.close();
		System.out.println("Finished");
	}
        */
}
