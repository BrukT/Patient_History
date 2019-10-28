/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.codejava.hibernate;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.iq80.leveldb.DB;
import static org.iq80.leveldb.impl.Iq80DBFactory.factory;
import org.iq80.leveldb.Options;
import static org.iq80.leveldb.impl.Iq80DBFactory.asString;
import static org.iq80.leveldb.impl.Iq80DBFactory.bytes;

public class levelDBManager {

	private DB levelDBStore;
		
	public void init(String storeName){	//i.e. "levelDBStore"
		try {
			Options options = new Options();
			levelDBStore = factory.open(new File(storeName), options);
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
}
