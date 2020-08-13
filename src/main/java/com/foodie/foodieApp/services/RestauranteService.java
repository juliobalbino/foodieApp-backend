package com.foodie.foodieApp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.foodie.foodieApp.entities.Restaurante;
import com.foodie.foodieApp.repositories.RestauranteRepository;
import com.foodie.foodieApp.services.exceptions.DataIntegrityException;
import com.foodie.foodieApp.services.exceptions.ObjectNotFoundException;

@Service
public class RestauranteService {

	@Autowired
	private RestauranteRepository repository;
	
	public List<Restaurante> findAll() {
		return repository.findAll();
	}
	
	public Restaurante findById(Integer id) {
		Optional<Restaurante> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Restaurante.class.getName()));
	}
	
	public Restaurante insert(Restaurante obj) {
		obj.setId(null);
		return repository.save(obj);
	}
	
	public Restaurante update(Restaurante obj) {
		findById(obj.getId());
		return repository.save(obj);
	}
	
	public void delete (Integer id) {
		findById(id);
		try {
			repository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um restaurante que possui comentarios");
		}
	}
}
