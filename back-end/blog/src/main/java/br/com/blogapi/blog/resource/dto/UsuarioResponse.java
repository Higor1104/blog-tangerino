package br.com.blogapi.blog.resource.dto;

import org.springframework.security.core.Authentication;

public class UsuarioResponse {

	private String login;
	private String token;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public static UsuarioResponse create(Authentication authentication, String token) {
		UsuarioResponse usuarioResponse = new UsuarioResponse();
		usuarioResponse.setLogin(authentication.getName());
		usuarioResponse.setToken(token);
		return usuarioResponse;
	}
}
