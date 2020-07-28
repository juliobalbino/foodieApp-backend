package com.foodie.foodieApp.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.foodie.foodieApp.entities.enums.RestauranteTipo;

@Entity
public class Critica implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Integer pontuacao;
	private Integer tipoDeRefeicao;
	private String corpo;
	private Integer curtidas;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant data;
	
	public Critica () {
	}

	public Critica(Integer id, String nome, Integer pontuacao, RestauranteTipo tipoDeRefeicao, String corpo, Integer curtidas, Instant data) {
		this.id = id;
		this.nome = nome;
		this.pontuacao = pontuacao;
		setRestauranteTipo(tipoDeRefeicao);
		this.corpo = corpo;
		this.curtidas = curtidas;
		this.data = data;
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

	public Integer getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(Integer pontuacao) {
		this.pontuacao = pontuacao;
	}

	public RestauranteTipo getRestauranteTipo() {
		return RestauranteTipo.valueOf(tipoDeRefeicao);
	}

	public void setRestauranteTipo(RestauranteTipo tipoDeRefeicao) {
		if (tipoDeRefeicao != null) {
			this.tipoDeRefeicao = tipoDeRefeicao.getCode();
		}
	}

	public String getCorpo() {
		return corpo;
	}

	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}

	public Integer getCurtidas() {
		return curtidas;
	}

	public void setCurtidas(Integer curtidas) {
		this.curtidas = curtidas;
	}
	
	public Instant getData() {
		return data;
	}

	public void setData(Instant data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Critica other = (Critica) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
