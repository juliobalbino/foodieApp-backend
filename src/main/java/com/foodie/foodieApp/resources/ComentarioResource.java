package com.foodie.foodieApp.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodie.foodieApp.entities.Comentario;
import com.foodie.foodieApp.services.ComentarioService;

@RestController
@RequestMapping(value = "/comentarios")
public class ComentarioResource {

	@Autowired
	private ComentarioService service;
	
	@GetMapping
	public ResponseEntity<List<Comentario>> findAll() {
		List<Comentario> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Comentario> findById(@PathVariable Integer id) {
		Comentario obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
