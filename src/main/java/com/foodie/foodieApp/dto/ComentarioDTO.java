package com.foodie.foodieApp.dto;

import java.io.Serializable;
import java.time.Instant;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.foodie.foodieApp.entities.Comentario;
import com.foodie.foodieApp.entities.Usuario;

public class ComentarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@Length(min = 3, max = 200, message = "O tamanho deve ser entre 3 e 200 caracteres")
	private String texto;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant data;
	
	private Usuario autor;
	

	public ComentarioDTO () {
	}
	
	public ComentarioDTO(Comentario obj) {
		id = obj.getId();
		texto = obj.getTexto();
		data = obj.getData();
		autor = obj.getAutor();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
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
}
