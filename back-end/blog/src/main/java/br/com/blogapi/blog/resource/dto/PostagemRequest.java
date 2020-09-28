package br.com.blogapi.blog.resource.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class PostagemRequest {

	@NotBlank@Min(value = 10)
	public String titulo;

	@NotBlank@Min(value = 10)
	public String texto;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

}
