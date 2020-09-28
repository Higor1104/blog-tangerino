package br.com.blogapi.blog.resource.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class UsuarioRequest {

	@NotBlank@Min(value = 6)
	private String login;
	@NotBlank@Min(value = 6)
	private String senha;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
