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

import com.foodie.foodieApp.dto.ComentarioDTO;
import com.foodie.foodieApp.entities.Comentario;
import com.foodie.foodieApp.entities.enums.Perfil;
import com.foodie.foodieApp.repositories.ComentarioRepository;
import com.foodie.foodieApp.security.UserSS;
import com.foodie.foodieApp.services.exceptions.AuthorizationException;
import com.foodie.foodieApp.services.exceptions.DataIntegrityException;
import com.foodie.foodieApp.services.exceptions.ObjectNotFoundException;

@Service
public class ComentarioService {

	@Autowired
	private ComentarioRepository repository;
	
	@Autowired
	private CriticaService criticaService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public List<Comentario> findAll() {
		return repository.findAll();
	}
	
	public Comentario findById(Integer id) {
		Optional<Comentario> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Comentario.class.getName()));
	}
	
	public Page<Comentario> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
	
	public Comentario insert(Comentario obj) {
		obj.setId(null);
		obj.setCritica(criticaService.findById(obj.getCritica().getId()));
		obj.setData(Instant.now());
		obj.setAutor(usuarioService.findById(obj.getAutor().getId()));
		return repository.save(obj);
	}
	
	public Comentario update(Comentario obj) {
		Comentario newObj = findById(obj.getId());
		
		UserSS user = UserService.authenticated();
		Integer id = newObj.getAutor().getId();
		
		if (user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Não Autorizado");
		}
		
		updateData(newObj, obj);
		return repository.save(newObj);
	}
	
	public void delete (Integer id) {
		Comentario obj = findById(id);
		
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
	
	private void updateData(Comentario newObj, Comentario obj) {
		newObj.setTexto(obj.getTexto());
	}
	
	public Comentario fromDTO(ComentarioDTO objDto) {
		return new Comentario(objDto.getId(), objDto.getTexto(), objDto.getData(), null, objDto.getAutor());
	}
}
