package com.foodie.foodieApp.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.foodie.foodieApp.entities.Critica;
import com.foodie.foodieApp.entities.Usuario;
import com.foodie.foodieApp.entities.enums.Perfil;
import com.foodie.foodieApp.repositories.CriticaRepository;
import com.foodie.foodieApp.security.UserSS;
import com.foodie.foodieApp.services.exceptions.AuthorizationException;
import com.foodie.foodieApp.services.exceptions.DataIntegrityException;
import com.foodie.foodieApp.services.exceptions.ObjectNotFoundException;

@Service
public class CriticaService {

	@Autowired
	private CriticaRepository repository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private RestauranteService restauranteService;
	
	public List<Critica> findAll() {
		return repository.findAll();
	}
	
	public Critica findById(Integer id) {
		Optional<Critica> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Critica.class.getName()));
	}
	
	public Page<Critica> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
	
	public Page<Critica> findMinhasCriticas(Integer page, Integer linesPerPage, String orderBy, String direction) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Usuario autor = usuarioService.findById(user.getId());
		return repository.findByAutor(autor, pageRequest);
	}
	
	public Critica insert(Critica obj) {
		obj.setId(null);
		obj.setData(Instant.now());
		obj.setRestaurante(restauranteService.findById(obj.getRestaurante().getId()));
		obj.setAutor(usuarioService.findById(obj.getAutor().getId()));
		return repository.save(obj);
	}
	
	public Critica update(Critica obj) {
		Critica newObj = findById(obj.getId());
		
		UserSS user = UserService.authenticated();
		Integer id = newObj.getAutor().getId();
		
		if (user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Não Autorizado");
		}
		
		updateData(newObj, obj);
		return repository.save(newObj);
	}
	
	public void delete (Integer id) {
		Critica obj = findById(id);
		
		UserSS user = UserService.authenticated();
		Integer idautor = obj.getAutor().getId();
		
		if (user==null || !user.hasRole(Perfil.ADMIN) && !idautor.equals(user.getId())) {
			throw new AuthorizationException("Não Autorizado");
		}
		
		findById(id);
		try {
			repository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um restaurante que possui comentarios");
		}
	}
	
	public Page<Critica> searchNome(String nome, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findDistinctByNomeContaining(nome, pageRequest);
	}
	
	private void updateData(Critica newObj, Critica obj) {
		newObj.setNome(obj.getNome());
		newObj.setCorpo(obj.getCorpo());
		newObj.setCurtidas(obj.getCurtidas());
	}
}
