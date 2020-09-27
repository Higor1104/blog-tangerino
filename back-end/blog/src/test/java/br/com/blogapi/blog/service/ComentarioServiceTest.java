package br.com.blogapi.blog.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.blogapi.blog.application.exception.RegistroNaoEncontradoException;
import br.com.blogapi.blog.domain.comentario.ComentarioService;
import br.com.blogapi.blog.domain.postagem.PostagemService;
import br.com.blogapi.blog.domain.usuario.UsuarioService;
import br.com.blogapi.blog.infrastructure.persistence.comentario.Comentario;
import br.com.blogapi.blog.infrastructure.persistence.comentario.ComentarioRepository;
import br.com.blogapi.blog.infrastructure.persistence.postagem.Postagem;
import br.com.blogapi.blog.infrastructure.persistence.postagem.PostagemRepository;
import br.com.blogapi.blog.infrastructure.persistence.usuario.Usuario;
import br.com.blogapi.blog.infrastructure.persistence.usuario.UsuarioRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class ComentarioServiceTest extends BaseServiceTest {

	private ComentarioService comentarioService;

	@Autowired
	public ComentarioServiceTest(ComentarioRepository comentarioRepository,
			PostagemRepository postagemRepository,
			UsuarioRepository usuarioRepository) {
		UsuarioService usuarioService = new UsuarioService(usuarioRepository);
		postagemService = new PostagemService(postagemRepository, usuarioService);
		comentarioService = new ComentarioService(comentarioRepository, postagemService, usuarioService);
	}

	@Test
	public void deveIncluirComentario() {
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setLogin("teste.higor");
		usuario.setSenha("123456");
		Postagem postagem = iniciarPostagem(usuario);
		Comentario comentario = new Comentario();
		comentario.setTexto("Comentario da postagem");
		comentarioService.incluir(usuario, postagem.getId(), comentario);
		assertNotNull(comentario.getId());
	}

	@Test
	public void naoDeveIncluirComentario() {
		boolean comentarioIncluido = true;
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setLogin("teste.higor");
		usuario.setSenha("123456");
		Comentario comentario = new Comentario();
		comentario.setTexto("Comentario da postagem");
		try {
		comentarioService.incluir(usuario, 33333333L, comentario);
		} catch (RegistroNaoEncontradoException e) {
			comentarioIncluido = false;
		}
		assertFalse(comentarioIncluido);
	}

	@Test
	public void deveBuscarComentario() {
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setLogin("teste.higor");
		usuario.setSenha("123456");
		Postagem postagem = iniciarPostagem(usuario);
		Comentario comentario = new Comentario();
		comentario.setTexto("Comentario da postagem");
		comentarioService.incluir(usuario, postagem.getId(), comentario);
		assertNotNull(comentario.getId());
		Comentario comentarioRecuperadaO = comentarioService.buscar(postagem.getId(), comentario.getId());
		assertNotNull(comentarioRecuperadaO);
	}

	@Test
	public void deveDeletarComentario() {
		boolean comentarioDeletado = true;
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setLogin("teste.higor");
		usuario.setSenha("123456");
		Postagem postagem = iniciarPostagem(usuario);
		Comentario comentario = new Comentario();
		comentario.setTexto("Comentario da postagem");
		comentarioService.incluir(usuario, postagem.getId(), comentario);
		assertNotNull(comentario.getId());
		comentarioService.deletar(usuario, postagem.getId(), comentario.getId());
		try {
			comentarioService.buscar(postagem.getId(), comentario.getId());
			comentarioDeletado = false;
		} catch (RegistroNaoEncontradoException e) {
		}
		assertTrue(comentarioDeletado);
	}

	@Test
	public void naoDeveDeletarComentario() {
		boolean comentarioDeletado = true;
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		Postagem postagem = iniciarPostagem(usuario);
		Comentario comentario = new Comentario();
		comentario.setTexto("Comentario da postagem");
		comentarioService.incluir(usuario, postagem.getId(), comentario);
		assertNotNull(comentario.getId());

		Usuario usuarioDiferente = new Usuario();
		usuarioDiferente.setId(2L);
		try {
			comentarioService.deletar(usuarioDiferente, postagem.getId(), comentario.getId());
		} catch (RegistroNaoEncontradoException e) {
			comentarioDeletado = false;
		}
		assertFalse(comentarioDeletado);
	}

}
