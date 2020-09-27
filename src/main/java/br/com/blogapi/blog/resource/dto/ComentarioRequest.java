package br.com.blogapi.blog.resource.dto;

import javax.validation.constraints.NotBlank;

public class ComentarioRequest {

	@NotBlank
	public String texto;

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

}
