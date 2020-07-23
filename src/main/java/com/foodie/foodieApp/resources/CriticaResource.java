package com.foodie.foodieApp.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodie.foodieApp.entities.Critica;
import com.foodie.foodieApp.services.CriticaService;

@RestController
@RequestMapping(value = "/criticas")
public class CriticaResource {

	@Autowired
	private CriticaService service;
	
	@GetMapping
	public ResponseEntity<List<Critica>> findAll() {
		List<Critica> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Critica> findById(@PathVariable Integer id) {
		Critica obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
