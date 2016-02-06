package com.kouretas;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		
		try{
			SicknessMap.getInstance().build();
		} catch(IOException e){
			e.printStackTrace();
		}
		SpringApplication.run(Application.class, args);
		
	}
}


