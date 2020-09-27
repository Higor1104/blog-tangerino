package br.com.blogapi.blog.resource.dto;

import java.util.List;

public class PostagemResponse {

	private Long id;
	private String titulo;
	private String texto;
	private List<ComentarioResponse> comentarios;

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<ComentarioResponse> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<ComentarioResponse> comentarios) {
		this.comentarios = comentarios;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
