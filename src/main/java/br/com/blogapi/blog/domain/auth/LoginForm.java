package br.com.blogapi.blog.domain.auth;

import javax.validation.constraints.NotBlank;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {

	@NotBlank
	private String login;
	@NotBlank
	private String senha;

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public UsernamePasswordAuthenticationToken createAutenticator() {
		return new UsernamePasswordAuthenticationToken(getLogin(), getSenha());
	}
}
