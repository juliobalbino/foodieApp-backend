package com.foodie.foodieApp.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.foodie.foodieApp.entities.Comentario;
import com.foodie.foodieApp.entities.Critica;
import com.foodie.foodieApp.entities.Usuario;
import com.foodie.foodieApp.entities.enums.TipoDeRefeicao;

public class CriticaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String titulo;
	private String restaurante;
	private Integer pontuacao;
	private TipoDeRefeicao tipoDeRefeicao;
	private String corpo;
	private Integer curtidas;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant data;
	
	private Usuario autor;
	
	private List<Comentario> comentarios = new ArrayList<>();
	
	public CriticaDTO () {
	}
	
	public CriticaDTO(Critica obj) {
		id = obj.getId();
		titulo = obj.getTitulo();
		restaurante = obj.getRestaurante();
		pontuacao = obj.getPontuacao();
		tipoDeRefeicao = obj.getTipoDeRefeicao();
		corpo = obj.getCorpo();
		curtidas = obj.getCurtidas();
		data = obj.getData();
		autor = obj.getAutor();
		comentarios = obj.getComentarios();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(String restaurante) {
		this.restaurante = restaurante;
	}

	public Integer getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(Integer pontuacao) {
		this.pontuacao = pontuacao;
	}

	public TipoDeRefeicao getTipoDeRefeicao() {
		return tipoDeRefeicao;
	}

	public void setTipoDeRefeicao(TipoDeRefeicao tipoDeRefeicao) {
		this.tipoDeRefeicao = tipoDeRefeicao;
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

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
}
