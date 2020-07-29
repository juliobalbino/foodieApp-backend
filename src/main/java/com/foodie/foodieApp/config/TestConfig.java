package com.foodie.foodieApp.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.foodie.foodieApp.entities.Comentario;
import com.foodie.foodieApp.entities.Critica;
import com.foodie.foodieApp.entities.Restaurante;
import com.foodie.foodieApp.entities.Usuario;
import com.foodie.foodieApp.entities.enums.TipoDeRefeicao;
import com.foodie.foodieApp.repositories.ComentarioRepository;
import com.foodie.foodieApp.repositories.CriticaRepository;
import com.foodie.foodieApp.repositories.RestauranteRepository;
import com.foodie.foodieApp.repositories.UsuarioRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CriticaRepository criticaRespository;
	
	@Autowired
	private ComentarioRepository comentarioRespository;

	@Override
	public void run(String... args) throws Exception {
		
		Usuario u1 = new Usuario(null, "Maria Green", "maria@gmail.com", "123");
		Usuario u2 = new Usuario(null, "Alex Brown", "alex@gmail.com", "123");
		
		Restaurante r1 = new Restaurante(null, "Paris6", 20);
		r1.addTipoDeRefeicao(TipoDeRefeicao.ALMOCO);
		Restaurante r2 = new Restaurante(null, "Outback", 18);
		
		Critica cr1 = new Critica(null, "Paris6", 10, TipoDeRefeicao.JANTAR, "Fui no Paris6 e Amei, muito boa a comida", 9, Instant.parse("2019-06-20T18:10:32Z"), u1, r1);
		
		Comentario c1 = new Comentario(null, "Bom lugar, eu já fui lá", Instant.parse("2019-06-20T19:10:32Z"), r1, cr1, u1);
		Comentario c2 = new Comentario(null, "Gostei da publicação, fiquei com vontade de ir", Instant.parse("2019-06-20T19:32:06Z"), r2, cr1, u2);
		
		cr1.getComentarios().addAll(Arrays.asList(c1, c2));
		
		r1.getComentarios().addAll(Arrays.asList(c1));
		r1.getCriticas().addAll(Arrays.asList(cr1));
		r2.getComentarios().addAll(Arrays.asList(c2));
		
		u1.getCriticas().addAll(Arrays.asList(cr1));
		
		usuarioRepository.saveAll(Arrays.asList(u1, u2));
		
		

		restauranteRepository.saveAll(Arrays.asList(r1, r2));
		
		
		criticaRespository.saveAll(Arrays.asList(cr1));
		
		comentarioRespository.saveAll(Arrays.asList(c1, c2));
		
		//usuarioRepository.saveAll(Arrays.asList(u1, u2));
		
		/*Restaurante r1 = new Restaurante(null, "Paris6", 20);
		r1.addTipoDeRefeicao(TipoDeRefeicao.ALMOCO);
		Restaurante r2 = new Restaurante(null, "Outback", 18);*/
		//restauranteRepository.saveAll(Arrays.asList(r1, r2));
		
		/*Critica cr1 = new Critica(null, "Paris6", 10, TipoDeRefeicao.JANTAR, "Fui no Paris6 e Amei, muito boa a comida", 9, Instant.parse("2019-06-20T18:10:32Z"), u1, r1);
		criticaRespository.saveAll(Arrays.asList(cr1));*/
	
	}
}
