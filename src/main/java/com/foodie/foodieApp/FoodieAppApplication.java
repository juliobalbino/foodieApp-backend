package com.foodie.foodieApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.foodie.foodieApp.services.S3Service;

@SpringBootApplication
public class FoodieAppApplication implements CommandLineRunner{

	@Autowired
	private S3Service s3Service;
	
	public static void main(String[] args) {
		SpringApplication.run(FoodieAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		s3Service.uploadFile("D:\\Dev\\Projetos\\ws-sts\\fotos\\cr1.jpg");
		
	}

}
