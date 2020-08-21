package com.foodie.foodieApp.services;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.foodie.foodieApp.entities.Comentario;
import com.foodie.foodieApp.entities.Critica;
import com.foodie.foodieApp.entities.Restaurante;
import com.foodie.foodieApp.entities.Usuario;
import com.foodie.foodieApp.entities.enums.TipoDeRefeicao;
import com.foodie.foodieApp.repositories.ComentarioRepository;
import com.foodie.foodieApp.repositories.CriticaRepository;
import com.foodie.foodieApp.repositories.RestauranteRepository;
import com.foodie.foodieApp.repositories.UsuarioRepository;

@Service
public class DBService {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CriticaRepository criticaRespository;
	
	@Autowired
	private ComentarioRepository comentarioRespository;
	
	public void instantiateTestDatabase() {

	Usuario u1 = new Usuario(null, "Maria Green", "maria@gmail.com", pe.encode("123"));
	Usuario u2 = new Usuario(null, "Alex Brown", "alex@gmail.com", pe.encode("123"));
	Usuario u3 = new Usuario(null, "Will Blue", "Will@gmail.com", pe.encode("123"));
	
	Restaurante r1 = new Restaurante(null, "Paris6", 20);
	r1.addTipoDeRefeicao(TipoDeRefeicao.ALMOCO);
	Restaurante r2 = new Restaurante(null, "Outback", 18);
	r2.addTipoDeRefeicao(TipoDeRefeicao.ALMOCO);
	r2.addTipoDeRefeicao(TipoDeRefeicao.JANTAR);
	
	Critica cr1 = new Critica(null, "Paris6", 10, TipoDeRefeicao.JANTAR, "Fui no Paris6 e Amei, muito boa a comida", 9, Instant.parse("2019-06-20T18:10:32Z"), u1, r1);
	
	Comentario c1 = new Comentario(null, "Bom lugar, eu já fui lá", Instant.parse("2019-06-20T19:10:32Z"), r1, cr1, u1);
	Comentario c2 = new Comentario(null, "Gostei da publicação, fiquei com vontade de ir", Instant.parse("2019-06-20T19:32:06Z"), r2, cr1, u2);
	
	cr1.getComentarios().addAll(Arrays.asList(c1, c2));
	
	r1.getComentarios().addAll(Arrays.asList(c1));
	r1.getCriticas().addAll(Arrays.asList(cr1));
	r2.getComentarios().addAll(Arrays.asList(c2));
	
	u1.getCriticas().addAll(Arrays.asList(cr1));
	
	usuarioRepository.saveAll(Arrays.asList(u1, u2, u3));
	
	restauranteRepository.saveAll(Arrays.asList(r1, r2));
	
	criticaRespository.saveAll(Arrays.asList(cr1));
	
	comentarioRespository.saveAll(Arrays.asList(c1, c2));
	
	}

}

