package com.foodie.foodieApp.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

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
import org.springframework.web.multipart.MultipartFile;
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
			@RequestParam(value = "orderBy", defaultValue = "data") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "DESC") String direction) {
		Page<Critica> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<CriticaDTO> listDto = list.map(obj -> new CriticaDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping(value = "/minhascriticas")
	public ResponseEntity<Page<CriticaDTO>> findMinhasCriticas(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "data") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "DESC") String direction) {
		Page<Critica> list = service.findMinhasCriticas(page, linesPerPage, orderBy, direction);
		Page<CriticaDTO> listDto = list.map(obj -> new CriticaDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Critica> findById(@PathVariable Integer id) {
		Critica obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CriticaDTO objDto) {
		Critica obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CriticaDTO objDto, @PathVariable Integer id) {
		Critica obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/searchtitulo")
	public ResponseEntity<Page<CriticaDTO>> searchTitulo(
			@RequestParam(value = "titulo", defaultValue = "") String titulo,
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "data") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "DESC") String direction) {
		titulo = URL.decodeParam(titulo);
		Page<Critica> list = service.searchTitulo(titulo, page, linesPerPage, orderBy, direction);
		Page<CriticaDTO> listDto = list.map(obj -> new CriticaDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping(value = "/searchrestaurante")
	public ResponseEntity<Page<CriticaDTO>> searchRestaurante(
			@RequestParam(value = "restaurante", defaultValue = "") String restaurante,
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "data") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "DESC") String direction) {
		restaurante = URL.decodeParam(restaurante);
		Page<Critica> list = service.searchRestaurante(restaurante, page, linesPerPage, orderBy, direction);
		Page<CriticaDTO> listDto = list.map(obj -> new CriticaDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value = "/{id}/picture", method=RequestMethod.POST)
	public ResponseEntity<Void> uploadCriticaPicture(@PathVariable Integer id, @RequestParam(name="file") MultipartFile file) {
		URI uri = service.uploadCriticaPicture(id,file);
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}/like", method = RequestMethod.POST)
	public ResponseEntity<Void> like(@PathVariable Integer id) {
		service.like(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}/unlike", method = RequestMethod.POST)
	public ResponseEntity<Void> unlike(@PathVariable Integer id) {
		service.unlike(id);
		return ResponseEntity.noContent().build();
	}
}
