package br.com.blogapi.blog.resource.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ComentarioRequest {

	@NotBlank@Min(value = 10)
	public String texto;

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

}
