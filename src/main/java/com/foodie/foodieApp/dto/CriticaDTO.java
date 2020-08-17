package com.foodie.foodieApp.dto;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.foodie.foodieApp.entities.Critica;
import com.foodie.foodieApp.entities.enums.TipoDeRefeicao;

public class CriticaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private Integer pontuacao;
	private TipoDeRefeicao tipoDeRefeicao;
	private String corpo;
	private Integer curtidas;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant data;
	
	public CriticaDTO() {
	}
	
	public CriticaDTO(Critica obj) {
		id = obj.getId();
		nome = obj.getNome();
		pontuacao = obj.getPontuacao();
		setTipoDeRefeicao(obj.getTipoDeRefeicao());
		corpo = obj.getCorpo();
		curtidas = obj.getCurtidas();
		data = obj.getData();
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

}
