package com.foodie.foodieApp.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.foodie.foodieApp.entities.Restaurante;
import com.foodie.foodieApp.entities.Usuario;
import com.foodie.foodieApp.entities.enums.RestauranteTipo;
import com.foodie.foodieApp.repositories.RestauranteRepository;
import com.foodie.foodieApp.repositories.UsuarioRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Usuario u1 = new Usuario(null, "Maria Green", "maria@gmail.com", "123");
		Usuario u2 = new Usuario(null, "Alex Brown", "alex@gmail.com", "123");
		
		usuarioRepository.saveAll(Arrays.asList(u1, u2));
		
		Restaurante r1 = new Restaurante(null, "Paris6", RestauranteTipo.JANTAR, 20);
		Restaurante r2 = new Restaurante(null, "Outback", RestauranteTipo.ALMOCO, 18);
		
		restauranteRepository.saveAll(Arrays.asList(r1, r2));
	}
}
