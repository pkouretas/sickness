package com.kouretas;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SicknessMap {

	private Map<String, String> illnesses = new HashMap<String, String>();
	private volatile static SicknessMap instance;
	
	private SicknessMap(){}
	
	public static SicknessMap getInstance(){
		if (instance == null){
			synchronized (SicknessMap.class) {
				if (instance == null){
					instance = new SicknessMap();
				}
			}
		}
		return instance;
	}
	
	public void build() throws IOException {
		this.illnesses = SicknessMapBuilder.build();
	}
	
	/*public int getSize(){
		return illnesses.size();
	}*/
	
	public boolean contains(String key){
		return this.illnesses.containsKey(key.toLowerCase());
	}
	public String getByKey(String key){
		return this.illnesses.get(key.toLowerCase());
	}
}
