package com.foodie.foodieApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodie.foodieApp.entities.Restaurante;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Integer>{

}
