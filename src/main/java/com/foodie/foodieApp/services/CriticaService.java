package com.foodie.foodieApp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.foodie.foodieApp.entities.Critica;
import com.foodie.foodieApp.repositories.CriticaRepository;
import com.foodie.foodieApp.services.exceptions.DataIntegrityException;
import com.foodie.foodieApp.services.exceptions.ObjectNotFoundException;

@Service
public class CriticaService {

	@Autowired
	private CriticaRepository repository;
	
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
	
	public Critica insert(Critica obj) {
		obj.setId(null);
		return repository.save(obj);
	}
	
	public Critica update(Critica obj) {
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
