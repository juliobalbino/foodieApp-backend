package com.foodie.foodieApp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodie.foodieApp.entities.Comentario;
import com.foodie.foodieApp.repositories.ComentarioRepository;
import com.foodie.foodieApp.services.exceptions.ObjectNotFoundException;

@Service
public class ComentarioService {

	@Autowired
	private ComentarioRepository repository;
	
	public List<Comentario> findAll() {
		return repository.findAll();
	}
	
	public Comentario findById(Integer id) {
		Optional<Comentario> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Comentario.class.getName()));
	}
}
