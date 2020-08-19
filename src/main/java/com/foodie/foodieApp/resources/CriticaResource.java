package com.foodie.foodieApp.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.foodie.foodieApp.dto.CriticaDTO;
import com.foodie.foodieApp.entities.Critica;
import com.foodie.foodieApp.resources.utils.URL;
import com.foodie.foodieApp.services.CriticaService;

@RestController
@RequestMapping(value = "/criticas")
public class CriticaResource {

	@Autowired
	private CriticaService service;
	
	@GetMapping
	public ResponseEntity<List<CriticaDTO>> findAll() {
		List<Critica> list = service.findAll();
		List<CriticaDTO> listDto = list.stream().map(obj -> new CriticaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping(value = "/page")
	public ResponseEntity<Page<CriticaDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Critica> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<CriticaDTO> listDto = list.map(obj -> new CriticaDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Critica> findById(@PathVariable Integer id) {
		Critica obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Critica obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Critica obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/searchnome")
	public ResponseEntity<Page<CriticaDTO>> searchNome(
			@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		nome = URL.decodeParam(nome);
		Page<Critica> list = service.searchNome(nome, page, linesPerPage, orderBy, direction);
		Page<CriticaDTO> listDto = list.map(obj -> new CriticaDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
}
