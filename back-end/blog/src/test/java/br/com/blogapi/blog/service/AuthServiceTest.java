package br.com.blogapi.blog.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.blogapi.blog.domain.auth.AuthService;
import br.com.blogapi.blog.infrastructure.persistence.usuario.Usuario;
import br.com.blogapi.blog.infrastructure.persistence.usuario.UsuarioRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class AuthServiceTest {

	private AuthService authService;

	@Autowired
	public AuthServiceTest(@Value("${jwt.secret}") String jwtSecret,
			@Value("${jwt.expiration}") String jwtExpiration,
			UsuarioRepository usuarioRepository) {
		this.authService = new AuthService(jwtSecret, jwtExpiration, usuarioRepository);
	}

	@Test
	public void deveGerarToken() {
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setLogin("teste.higor");
		usuario.setSenha("123456");
		Authentication authentication = new UsernamePasswordAuthenticationToken(usuario, usuario.getAuthorities());
		String token = this.authService.gerarToken(authentication);
		assertNotNull(token);
		assertFalse(token.isEmpty());
	}

	@Test
	public void deveLogar() {
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setLogin("teste.higor");
		usuario.setSenha("123456");
		Authentication authentication = new UsernamePasswordAuthenticationToken(usuario, usuario.getAuthorities());
		String token = this.authService.gerarToken(authentication);
		assertNotNull(token);
		assertFalse(token.isEmpty());
	}

	@Test
	public void deveBuscarUsuario() {
		UserDetails userDetails = this.authService.loadUserByUsername("teste.higor");
		assertNotNull(userDetails);
	}

	@Test
	public void naoDeveBuscarUsuario() {
		boolean usuarioEncontrado = true;
		try {
			this.authService.loadUserByUsername("teste.higor.nao.existe");
			usuarioEncontrado = true;
		} catch (UsernameNotFoundException e) {
			usuarioEncontrado = false;
		}
		assertFalse(usuarioEncontrado);
	}

	@Test
	public void deveBuscarUsuarioPorToken() {
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setLogin("teste.higor");
		usuario.setSenha("123456");
		Authentication authentication = new UsernamePasswordAuthenticationToken(usuario, usuario.getAuthorities());
		String token = this.authService.gerarToken(authentication);
		assertNotNull(token);
		assertFalse(token.isEmpty());
		Optional<Usuario> usuarioO = this.authService.obterUsuario(token);
		assertTrue(usuarioO.isPresent());
	}

	@Test
	public void naoDeveBuscarUsuarioPorToken() {
		Optional<Usuario> usuarioO = this.authService.obterUsuario("123");
		assertFalse(usuarioO.isPresent());
	}

}
