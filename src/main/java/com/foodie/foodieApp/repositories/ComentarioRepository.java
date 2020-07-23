package com.foodie.foodieApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodie.foodieApp.entities.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer>{

}
