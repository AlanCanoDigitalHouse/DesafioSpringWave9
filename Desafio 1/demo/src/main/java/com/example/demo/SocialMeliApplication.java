package com.example.demo;

import com.example.demo.Repositiories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SocialMeliApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialMeliApplication.class, args);
		UserRepository.getInstance();
	}

}
