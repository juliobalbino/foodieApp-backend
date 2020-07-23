package com.foodie.foodieApp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodie.foodieApp.entities.Critica;
import com.foodie.foodieApp.repositories.CriticaRepository;

@Service
public class CriticaService {

	@Autowired
	private CriticaRepository repository;
	
	public List<Critica> findAll() {
		return repository.findAll();
	}
	
	public Critica findById(Integer id) {
		Optional<Critica> obj = repository.findById(id);
		return obj.get();
	}
}
