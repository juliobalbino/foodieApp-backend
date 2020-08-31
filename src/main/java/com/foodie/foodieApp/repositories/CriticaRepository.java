package com.foodie.foodieApp.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodie.foodieApp.entities.Critica;
import com.foodie.foodieApp.entities.Usuario;

@Repository
public interface CriticaRepository extends JpaRepository<Critica, Integer>{

	Page<Critica> findDistinctByTituloContaining(String titulo, Pageable pageRequest);
	
	Page<Critica> findByAutor(Usuario autor, Pageable pageRequest);
}
