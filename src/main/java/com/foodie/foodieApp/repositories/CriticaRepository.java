package com.foodie.foodieApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodie.foodieApp.entities.Critica;

@Repository
public interface CriticaRepository extends JpaRepository<Critica, Integer>{

}
