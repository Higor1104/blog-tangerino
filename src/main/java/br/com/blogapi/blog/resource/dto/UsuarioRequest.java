package br.com.blogapi.blog.resource.dto;

import javax.validation.constraints.NotBlank;

public class UsuarioRequest {

	@NotBlank
	private String login;
	@NotBlank
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
