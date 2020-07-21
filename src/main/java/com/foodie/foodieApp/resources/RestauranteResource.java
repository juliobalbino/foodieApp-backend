package com.foodie.foodieApp.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodie.foodieApp.entities.Restaurante;
import com.foodie.foodieApp.services.RestauranteService;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteResource {

	@Autowired
	private RestauranteService service;
	
	@GetMapping
	public ResponseEntity<List<Restaurante>> findAll() {
		List<Restaurante> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Restaurante> findById(@PathVariable Integer id) {
		Restaurante obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
