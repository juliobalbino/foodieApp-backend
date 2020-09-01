package com.foodie.foodieApp.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.foodie.foodieApp.entities.Critica;
import com.foodie.foodieApp.entities.Usuario;
import com.foodie.foodieApp.entities.enums.Perfil;
import com.foodie.foodieApp.repositories.CriticaRepository;
import com.foodie.foodieApp.security.UserSS;
import com.foodie.foodieApp.services.exceptions.AuthorizationException;
import com.foodie.foodieApp.services.exceptions.DataIntegrityException;
import com.foodie.foodieApp.services.exceptions.ObjectNotFoundException;

@Service
public class CriticaService {

	@Autowired
	private CriticaRepository repository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private S3Service s3Service;
	
	@Autowired
	private ImageService imageService;
	
	@Value("${img.prefix.critica}")
	private String prefix;
	
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
	
	public Page<Critica> findMinhasCriticas(Integer page, Integer linesPerPage, String orderBy, String direction) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Usuario autor = usuarioService.findById(user.getId());
		return repository.findByAutor(autor, pageRequest);
	}
	
	public Critica insert(Critica obj) {
		obj.setId(null);
		obj.setData(Instant.now());
		obj.setAutor(usuarioService.findById(obj.getAutor().getId()));
		return repository.save(obj);
	}
	
	public Critica update(Critica obj) {
		Critica newObj = findById(obj.getId());
		
		UserSS user = UserService.authenticated();
		Integer id = newObj.getAutor().getId();
		
		if (user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Não Autorizado");
		}
		
		updateData(newObj, obj);
		return repository.save(newObj);
	}
	
	public void delete (Integer id) {
		Critica obj = findById(id);
		
		UserSS user = UserService.authenticated();
		Integer idautor = obj.getAutor().getId();
		
		if (user==null || !user.hasRole(Perfil.ADMIN) && !idautor.equals(user.getId())) {
			throw new AuthorizationException("Não Autorizado");
		}
		
		findById(id);
		try {
			repository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um restaurante que possui comentarios");
		}
	}
	
	public Page<Critica> searchTitulo(String titulo, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findDistinctByTituloContaining(titulo, pageRequest);
	}
	
	public Page<Critica> searchRestaurante(String restaurante, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findDistinctByRestauranteContaining(restaurante, pageRequest);
	}
	
	private void updateData(Critica newObj, Critica obj) {
		newObj.setTitulo(obj.getTitulo());
		newObj.setCorpo(obj.getCorpo());
		newObj.setCurtidas(obj.getCurtidas());
	}
	
	public URI uploadCriticaPicture(Integer id, MultipartFile multipartFile) {
		
		Critica obj = findById(id);
		
		UserSS user = UserService.authenticated();
		Integer idautor = obj.getAutor().getId();
		
		if (user==null || !user.hasRole(Perfil.ADMIN) && !idautor.equals(user.getId())) {
			throw new AuthorizationException("Não Autorizado");
		}
		
		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		String fileName = "autor-" + obj.getAutor().getId() + prefix + obj.getId() + ".jpg";
		
		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
	}
	
	public void like(Integer id) {
		
		Critica obj = findById(id);
		
		UserSS user = UserService.authenticated();
		
		if (user == null) {
			throw new AuthorizationException("É necessário estar logado");
		}
		
		obj.getCurtidores().add(user.getId());
		obj.setCurtidas(obj.getCurtidores().size());
		repository.save(obj);
	}
	
	public void unlike(Integer id) {
		
		Critica obj = findById(id);
		
		UserSS user = UserService.authenticated();
		
		if (user == null) {
			throw new AuthorizationException("É necessário estar logado");
		}
		
		obj.getCurtidores().remove(user.getId());
		obj.setCurtidas(obj.getCurtidores().size());
		repository.save(obj);
	}
}
