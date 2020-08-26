package com.foodie.foodieApp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.foodie.foodieApp.dto.UsuarioNewDTO;
import com.foodie.foodieApp.entities.Usuario;
import com.foodie.foodieApp.entities.enums.Perfil;
import com.foodie.foodieApp.repositories.UsuarioRepository;
import com.foodie.foodieApp.security.UserSS;
import com.foodie.foodieApp.services.exceptions.AuthorizationException;
import com.foodie.foodieApp.services.exceptions.DataIntegrityException;
import com.foodie.foodieApp.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private EmailService emailService;
	
	public List<Usuario> findAll() {
		return repository.findAll();
	}
	
	public Usuario findById(Integer id) {
		Optional<Usuario> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
	}
	
	public Page<Usuario> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
	
	public Usuario insert(Usuario obj) {
		obj.setId(null);
		emailService.sendAccConfirmationHtmlEmail(obj);
		return repository.save(obj);
	}
	
	public Usuario update(Usuario obj) {
		
		UserSS user = UserService.authenticated();
		
		if (user==null || !user.hasRole(Perfil.ADMIN) && !obj.getId().equals(user.getId())) {
			throw new AuthorizationException("Não Autorizado");
		}
		
		Usuario newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	public void delete (Integer id) {
		
		UserSS user = UserService.authenticated();
		
		if (user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Não Autorizado");
		}
		
		findById(id);
		try {
			repository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um cliente que possui criticas");
		}
	}
	
	public Page<Usuario> searchNome(String nome, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findDistinctByNomeContaining(nome, pageRequest);
	}
	
	public Usuario fromDTO(UsuarioNewDTO objDto) {
		Usuario us = new Usuario(null, objDto.getNome(), objDto.getEmail(), pe.encode(objDto.getSenha()));
		return us;
	}
	
	private void updateData(Usuario newObj, Usuario obj) {
		newObj.setSenha(obj.getSenha());
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
