package com.mercadolibre.desafiospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
public class DesafioSpringApplication {
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(
				DesafioSpringApplication.class, args);
	}

}
