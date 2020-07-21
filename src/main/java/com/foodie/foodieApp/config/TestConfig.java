package com.foodie.foodieApp.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.foodie.foodieApp.entities.Usuario;
import com.foodie.foodieApp.repositories.UsuarioRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Usuario u1 = new Usuario(null, "Maria Green", "maria@gmail.com", "123");
		Usuario u2 = new Usuario(null, "Alex Brown", "alex@gmail.com", "123");
		
		usuarioRepository.saveAll(Arrays.asList(u1, u2));
	}
}
