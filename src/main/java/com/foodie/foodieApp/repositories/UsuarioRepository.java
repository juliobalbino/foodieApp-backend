package com.foodie.foodieApp.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodie.foodieApp.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	Page<Usuario> findDistinctByNomeContaining(String nome, Pageable pageRequest);
	
	Usuario findByEmail(String email);
}
