package com.foodie.foodieApp.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodie.foodieApp.entities.Restaurante;


@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Integer>{

	Page<Restaurante> findDistinctByNomeContaining(String nome, Pageable pageRequest);
}
