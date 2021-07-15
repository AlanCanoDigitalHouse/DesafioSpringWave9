package com.mercadolibre.socialmeli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SocialMeliApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialMeliApplication.class, args);
	}

}
