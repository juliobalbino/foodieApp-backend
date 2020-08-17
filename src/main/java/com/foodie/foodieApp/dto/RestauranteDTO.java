package com.foodie.foodieApp.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.foodie.foodieApp.entities.Restaurante;
import com.foodie.foodieApp.entities.enums.TipoDeRefeicao;

public class RestauranteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private Integer pontuacaoMedia;
	
	private Set<TipoDeRefeicao> tiposRestaurante = new HashSet<>();
	
	public RestauranteDTO() {
	}
	
	public RestauranteDTO(Restaurante obj) {
		id = obj.getId();
		nome = obj.getNome();
		pontuacaoMedia = obj.getPontuacaoMedia();
		tiposRestaurante = obj.getTiposRestaurante();
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

	public Integer getPontuacaoMedia() {
		return pontuacaoMedia;
	}

	public void setPontuacaoMedia(Integer pontuacaoMedia) {
		this.pontuacaoMedia = pontuacaoMedia;
	}

	public Set<TipoDeRefeicao> getTiposRestaurante() {
		return tiposRestaurante;
	}

	public void setTiposRestaurante(Set<TipoDeRefeicao> tiposRestaurante) {
		this.tiposRestaurante = tiposRestaurante;
	}
}
