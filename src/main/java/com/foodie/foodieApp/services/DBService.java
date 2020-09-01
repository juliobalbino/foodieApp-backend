package com.foodie.foodieApp.services;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.foodie.foodieApp.entities.Comentario;
import com.foodie.foodieApp.entities.Critica;
import com.foodie.foodieApp.entities.Usuario;
import com.foodie.foodieApp.entities.enums.Perfil;
import com.foodie.foodieApp.entities.enums.TipoDeRefeicao;
import com.foodie.foodieApp.repositories.ComentarioRepository;
import com.foodie.foodieApp.repositories.CriticaRepository;
import com.foodie.foodieApp.repositories.UsuarioRepository;

@Service
public class DBService {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CriticaRepository criticaRespository;
	
	@Autowired
	private ComentarioRepository comentarioRespository;
	
	public void instantiateTestDatabase() {

	Usuario u1 = new Usuario(null, "Maria Green", "maria@gmail.com", pe.encode("123"));
	Usuario u2 = new Usuario(null, "Alex Brown", "alex@gmail.com", pe.encode("123"));
	Usuario u3 = new Usuario(null, "Julio Balbino", "julio4110@hotmail.com", pe.encode("123"));
	u3.addPerfil(Perfil.ADMIN);
	
	Critica cr1 = new Critica(null, "Restaurante incrível", "Paris6",10, TipoDeRefeicao.JANTAR, "Fui no Paris6 e Amei, muito boa a comida", 0, Instant.now(), u1);
	Critica cr2 = new Critica(null, "Outback Horrivel", "Outback", 2, TipoDeRefeicao.ALMOCO, "Fui ao Outback no ABC e Odiei, comida tudo doce", 0, Instant.parse("2019-06-20T19:32:06Z"), u2);
	
	Comentario c1 = new Comentario(null, "Bom lugar, eu já fui lá", Instant.parse("2019-06-20T19:10:32Z"), cr1, u1);
	Comentario c2 = new Comentario(null, "Gostei da publicação, fiquei com vontade de ir", Instant.parse("2019-06-20T19:32:06Z"), cr1, u2);
	
	cr1.getComentarios().addAll(Arrays.asList(c1, c2));
	
	u1.getCriticas().addAll(Arrays.asList(cr1));
	u2.getCriticas().addAll(Arrays.asList(cr2));
	
	usuarioRepository.saveAll(Arrays.asList(u1, u2, u3));
	
	criticaRespository.saveAll(Arrays.asList(cr1, cr2));
	
	comentarioRespository.saveAll(Arrays.asList(c1, c2));
	
	}

}

