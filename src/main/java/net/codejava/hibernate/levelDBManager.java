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
	private int doctorId;
	private int examinationId;
	private int patientId;
	
		
	public void init(String storeName){	//i.e. "levelDBStore"
		try {
			Options options = new Options();
			levelDBStore = factory.open(new File(storeName), options);
						
			DBIterator iterator = levelDBStore.iterator();
			iterator.seek(bytes("examinationId:")); // starts from the specified key			
			examinationId = 0;
			while (iterator.hasNext()){
				byte[] key = iterator.peekNext().getKey();
				// key arrangement : doctorId:$doctor_id:$attribute_name = $value
				String[] keySplit = asString(key).split(":"); // split the key
				if (!keySplit[0].equals("examinationId")) { // breaking condition : prefix is not "employee"
					break;
				}
				else{
					examinationId = Integer.parseInt(keySplit[1]);
				}
				iterator.next();				
			}
			
			iterator.seek(bytes("doctorId:"));
			doctorId = 0;
			while (iterator.hasNext()){
				byte[] key = iterator.peekNext().getKey();
				// key arrangement : doctorId:$doctor_id:$attribute_name = $value
				String[] keySplit = asString(key).split(":"); // split the key
				if (!keySplit[0].equals("doctorId")) { // breaking condition : prefix is not "employee"
					break;
				}
				else{
					doctorId = Integer.parseInt(keySplit[1]);
				}
				iterator.next();				
			}
			iterator.seek(bytes("patientId:"));
			patientId = 0;
			while (iterator.hasNext()){
				byte[] key = iterator.peekNext().getKey();
				// key arrangement : doctorId:$doctor_id:$attribute_name = $value
				String[] keySplit = asString(key).split(":"); // split the key
				if (!keySplit[0].equals("patientiId")) { // breaking condition : prefix is not "employee"
					break;
				}
				else{
					patientId = Integer.parseInt(keySplit[1]);
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
			// whatever you want to do
			iterator.next();
		}
	}
	
	
	
	public void putPatient(String name, String surname, String sex, String city, String birthDate, String email, String taxCode){		
		String key = "patientId:" + taxCode + ":";
		
		put(key+"name", name);
		put(key+"surname", surname);
		put(key+"sex", sex);
		put(key+"city", city);
		put(key+"birthDate", birthDate);
		put(key+"email", email);
	}
	
	public Patient readPatient(String taxCode){
		String myKey = "patientId:" + taxCode + ":";
		DBIterator iterator = levelDBStore.iterator();
		iterator.seek(bytes(myKey)); // starts from the specified key
		ArrayList<String> param = new ArrayList<>();
		int i = 0;
		while (iterator.hasNext()){
			byte[] key = iterator.peekNext().getKey();
			// key arrangement : doctorId:$doctor_id:$attribute_name = $value
			String[] keySplit = asString(key).split(":"); // split the key
			if (!keySplit[0].equals("patientId") || !keySplit[1].equals(taxCode)) { // breaking condition : prefix is not "employee"
				break;
			}
			byte[] value = iterator.peekNext().getValue();
			param.add(asString(value));
			//System.out.println(asString(key) + " | "+asString(value));
			// whatever you want to do
			iterator.next();
			i++;
		}
		
		Patient p = new Patient(taxCode, param.get(3), param.get(5), param.get(4), param.get(0), param.get(1), param.get(2));
		return p;
	}
	
	public void putDoctor(String name, String surname, String email){
		String key = "doctorId:" + incrementAndGetDoctorId() + ":";
		
		put(key+"name", name);
		put(key+"surname", surname);
		put(key+"email", email);				
	}
	
	public Doctor readDoctor(int doctorId){
		String myKey = "doctorId:" + doctorId + ":";
		DBIterator iterator = levelDBStore.iterator();
		iterator.seek(bytes(myKey)); // starts from the specified key
		ArrayList<String> param = new ArrayList<>();
		int i = 0;
		while (iterator.hasNext()){
			byte[] key = iterator.peekNext().getKey();
			// key arrangement : doctorId:$doctor_id:$attribute_name = $value
			
			String[] keySplit = asString(key).split(":"); // split the key
			if (!keySplit[0].equals("doctorId") || !keySplit[1].equals(Integer.toString(doctorId))) { // breaking condition : prefix is not "employee"
				break;
			}
			
			byte[] value = iterator.peekNext().getValue();
			param.add(asString(value));
			//System.out.println(asString(key) + " | "+asString(value));
			// whatever you want to do
			iterator.next();
			i++;
		}
		
		Doctor d = new Doctor(doctorId, param.get(1), param.get(2), param.get(0));
		return d;
	}
	
	public void putExamination(String taxCode, int doctorId, String type, String result, String examDate){
		String key = "examinationId:" + incrementAndGetExaminationId() + ":" + taxCode + ":" + doctorId + ":";
		
		put(key+"examDate", examDate);
		put(key+"type", type);
		put(key+"result", result);
	}
	
	public List<Examination> readPatientExamination(String taxCode){
		String myKey = "examinationId:";
		DBIterator iterator = levelDBStore.iterator();
		iterator.seek(bytes(myKey)); // starts from the specified key
		ArrayList<String> param = new ArrayList<>();
		final List<Examination> examinations = new ArrayList<>();
		int i = 0;
		while (iterator.hasNext()){
			byte[] key = iterator.peekNext().getKey();
			// key arrangement : examinationId:$examination_id:$patient_id:$doctor_id:$attribute_name = $value			
			String[] keySplit = asString(key).split(":"); // split the key
			if(keySplit[2].equals(taxCode)){
				byte[] value = iterator.peekNext().getValue();
				System.out.println("-> "+asString(key) + "\t|\t"+asString(value));
				param.add(asString(value));
				i++;
				if(i==3){
					Patient p = readPatient(keySplit[2]);
					Doctor d = readDoctor(Integer.parseInt(keySplit[3]));
					Examination e = new Examination(Integer.parseInt(keySplit[1]), p, d, param.get(0), param.get(2), param.get(1));
					examinations.add(e);
					System.out.println("//");
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
	
	/**
	 *Close the levelDB store
	 */
	public void close(){
		try {
			levelDBStore.close();
			
		} catch (IOException ex) {
			Logger.getLogger(levelDBManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public int getDoctorId() {
		return doctorId;
	}

	public int getExaminationId() {
		return examinationId;
	}
	
	public int getPatientId(){
		return patientId;
	}
	
	public int incrementAndGetDoctorId(){
		doctorId++;
		return  doctorId;
	}
	
	public int incrementAndGetExaminationId(){
		examinationId++;
		return examinationId;
	}
	
	
	
	public static void main(String[] args) {
		System.out.println("-----");
		
		levelDBManager l = new levelDBManager();
		l.init("mystore");
		System.out.println("Current= doc:"+l.getDoctorId()+"\tex:"+l.getExaminationId()+"\tpat:"+l.getPatientId());
		
		l.putPatient("pietro", "ducange", "male", "pisa", "1 jan 1970", "aa.bb@das.c", "duc1");
		
		Patient p = l.readPatient("duc1");
		System.out.println("-----");
		System.out.println(p);
		
		System.out.println("--------------");
		l.putDoctor("serial", "killer", "reaper@live.it");
		Doctor d = l.readDoctor(1);
		
		System.out.println(d);
		System.out.println("-----");
		l.putExamination("duc1", 1, "blood", "negative", "yesterday");
		l.putExamination("duc1", 1, "head", "positive", "20 oct");
		System.out.println("\n\n\n-----");
		System.out.println("-----");
		List<Examination> list = l.readPatientExamination("duc1");
		System.out.println("-----");
		System.out.println("-----");
		list.forEach((x) -> {
			System.out.println(x);
			System.out.println(x.getDoctor());
			System.out.println(x.getPatient());
		});
		
		
		l.dumpLevelDB();
		l.close();
		System.out.println("Finished");
	}
}
