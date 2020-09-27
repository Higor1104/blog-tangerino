package br.com.blogapi.blog.resource.dto;

import br.com.blogapi.blog.infrastructure.persistence.comentario.Comentario;

public class ComentarioResponse {

	private Long id;
	private String texto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public static ComentarioResponse create(Comentario comentario) {
		ComentarioResponse comentarioResponse = new ComentarioResponse();
		comentarioResponse.setId(comentario.getId());
		comentarioResponse.setTexto(comentario.getTexto());
		return comentarioResponse;
	}
}
