package com.foodie.foodieApp.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodie.foodieApp.entities.Usuario;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

	@GetMapping
	public ResponseEntity<Usuario> findAll() {
		Usuario u = new Usuario(1, "Julio", "julio@hotmail.com", "123");
		return ResponseEntity.ok().body(u);
	}
}
