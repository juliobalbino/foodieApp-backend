package com.foodie.foodieApp.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.foodie.foodieApp.entities.enums.TipoDeRefeicao;

@Entity
public class Restaurante implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Integer pontuacaoMedia;
	
	@ElementCollection
	@CollectionTable(name = "Tipo_Restaurante")
	private Set<Integer> tiposRestaurante = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "restaurante")
	private List<Comentario> comentarios = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "restaurante")
	private List<Critica> criticas = new ArrayList<>();

	public Restaurante() {
	}

	public Restaurante(Integer id, String nome, Integer pontuacaoMedia) {
		this.id = id;
		this.nome = nome;
		this.pontuacaoMedia = pontuacaoMedia;
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
	
	public Set<TipoDeRefeicao> getTiposRestaurante() {
		return tiposRestaurante.stream().map(x -> TipoDeRefeicao.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addTipoDeRefeicao(TipoDeRefeicao tipoRestaurante) {
		tiposRestaurante.add(tipoRestaurante.getCode());
	}

	public Integer getPontuacaoMedia() {
		return pontuacaoMedia;
	}

	public void setPontuacaoMedia(Integer pontuacaoMedia) {
		this.pontuacaoMedia = pontuacaoMedia;
	}
	
	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public List<Critica> getCriticas() {
		return criticas;
	}

	public void setCriticas(List<Critica> criticas) {
		this.criticas = criticas;
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
		Restaurante other = (Restaurante) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
