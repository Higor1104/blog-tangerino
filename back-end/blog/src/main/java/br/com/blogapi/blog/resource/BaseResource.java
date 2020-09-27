package br.com.blogapi.blog.resource;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.blogapi.blog.application.exception.TokenInvalidoException;
import br.com.blogapi.blog.infrastructure.persistence.usuario.Usuario;

public class BaseResource {

	@SuppressWarnings("unchecked")
	public Usuario recuperarUsuarioAtual() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Optional<Usuario> usuarioO = (Optional<Usuario>) authentication.getPrincipal();
		if (usuarioO.isPresent()) {
			return usuarioO.get();
		}
		throw new TokenInvalidoException("Usuario nao autenticado");
	}
}
