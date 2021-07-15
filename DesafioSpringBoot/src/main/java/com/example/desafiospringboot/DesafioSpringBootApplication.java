package com.example.desafiospringboot;

import com.example.desafiospringboot.dao.DaoPost;
import com.example.desafiospringboot.dao.DaoUsuarios;
import com.example.desafiospringboot.model.Usuario;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DesafioSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioSpringBootApplication.class, args);
	}
	@Bean
	public CommandLineRunner initApp(DaoUsuarios usuarios, DaoPost post){
		return args -> {
			System.out.println("Hola mundo");
			//usuarios.loadUsersP();
			//Usuario a = new Usuario(3, "Juan");
			//usuarios.saveUser(a);
			//usuarios.loadUsersP();
			//Post p = new Post(3);
			Usuario sebastian = usuarios.getUser(1); // consulta en bd por usuario con id 1
			System.out.println(post.fetchPosts(sebastian.getUserId())); // consulta en bd por post de sebastian
			
			/*
			 //post.writePost(p);
			System.out.println(post.loadPost());
			System.out.println(post);
			Usuario no = usuarios.getUser(2);
			if(no == null){
				System.out.println("usuario # 7 no encontrado");
			}
			System.out.println(sebastian.getUserName());
			*/
		};
	}
}
