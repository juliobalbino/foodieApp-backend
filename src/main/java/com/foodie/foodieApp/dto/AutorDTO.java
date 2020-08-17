package com.foodie.foodieApp.dto;

import java.io.Serializable;

import com.foodie.foodieApp.entities.Usuario;

public class AutorDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	
	public AutorDTO() {
	}
	
	public AutorDTO(Usuario obj) {
		obj.getId();
		obj.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
