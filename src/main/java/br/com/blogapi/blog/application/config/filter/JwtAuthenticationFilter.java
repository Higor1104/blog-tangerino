package br.com.blogapi.blog.application.config.filter;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.blogapi.blog.application.exception.TokenInvalidoException;
import br.com.blogapi.blog.domain.Constantes;
import br.com.blogapi.blog.domain.auth.AuthService;
import br.com.blogapi.blog.infrastructure.persistence.usuario.Usuario;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private AuthService authService;

	public JwtAuthenticationFilter(AuthService authService) {
		this.authService = authService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			doJwtFilter(request);
			filterChain.doFilter(request, response);
		} catch (TokenInvalidoException e) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.getWriter().write(e.getMessage());
		}
	}

	private void doJwtFilter(HttpServletRequest request) throws TokenInvalidoException {
		Optional<String> token = recuperarJwtToken(request);
		if (token.isPresent()) {
			autenticarUsuario(token.get());
		}
	}

	private void autenticarUsuario(String token) throws TokenInvalidoException {
		Optional<Usuario> usuarioO = authService.obterUsuario(token);
		if (usuarioO.isPresent()) {
			Usuario usuario = usuarioO.get();
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuarioO, null,
					usuario.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} else {
			throw new TokenInvalidoException("Token inválido");
		}
	}

	private Optional<String> recuperarJwtToken(HttpServletRequest request) {
		String headerAuthentication = request.getHeader(Constantes.AUTHORIZATION_HEADER);
		if (!StringUtils.hasText(headerAuthentication) || !headerAuthentication.startsWith(Constantes.BEARER_PREFIX)) {
			return Optional.empty();
		}
		return Optional.of(headerAuthentication.substring(7, headerAuthentication.length()));
	}

}
