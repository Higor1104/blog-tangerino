package br.com.blogapi.blog.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.blogapi.blog.application.exception.CadastroUsuarioException;
import br.com.blogapi.blog.domain.usuario.UsuarioService;
import br.com.blogapi.blog.infrastructure.persistence.usuario.Usuario;
import br.com.blogapi.blog.infrastructure.persistence.usuario.UsuarioRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UsuarioServiceTest {

	private UsuarioService usuarioService;

	@Autowired
	public UsuarioServiceTest(UsuarioRepository usuarioRepository) {
		this.usuarioService = new UsuarioService(usuarioRepository);
	}

	@Test
	public void deveBuscarUsuario() throws CadastroUsuarioException {
		Optional<Usuario> usuarioO = usuarioService.buscarUsuario(1L);
		assertTrue(usuarioO.isPresent());
	}

	@Test
	public void naoDeveBuscarUsuario() throws CadastroUsuarioException {
		Optional<Usuario> usuarioO = usuarioService.buscarUsuario(100000000L);
		assertFalse(usuarioO.isPresent());
	}

	@Test
	public void deveCriarUsuario() throws CadastroUsuarioException {
		Usuario usuario = new Usuario();
		usuario.setLogin("teste.criar.usuario");
		usuario.setSenha("1233456");
		usuarioService.salvar(usuario);

		Optional<Usuario> usuarioSalvo = usuarioService.buscarUsuario(usuario.getId());
		assertTrue(usuarioSalvo.isPresent());
	}

	@Test
	public void naoDeveCriarUsuario() {
		boolean usuarioCriado = false;
		Usuario usuario = new Usuario();
		usuario.setLogin("teste.higor");
		usuario.setSenha("1233456");
		try {
			usuarioService.salvar(usuario);
			usuarioCriado = true;
		} catch (CadastroUsuarioException e) {
			usuarioCriado = false;
		}
		assertFalse(usuarioCriado);
	}
}
