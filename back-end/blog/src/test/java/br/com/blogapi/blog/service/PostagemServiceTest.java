package br.com.blogapi.blog.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.blogapi.blog.application.exception.RegistroNaoEncontradoException;
import br.com.blogapi.blog.domain.postagem.PostagemService;
import br.com.blogapi.blog.domain.usuario.UsuarioService;
import br.com.blogapi.blog.infrastructure.persistence.postagem.Postagem;
import br.com.blogapi.blog.infrastructure.persistence.postagem.PostagemRepository;
import br.com.blogapi.blog.infrastructure.persistence.usuario.Usuario;
import br.com.blogapi.blog.infrastructure.persistence.usuario.UsuarioRepository;
import br.com.blogapi.blog.resource.dto.PostagemResponse;

@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class PostagemServiceTest extends BaseServiceTest {

	@Autowired
	public PostagemServiceTest(PostagemRepository postagemRepository, UsuarioRepository usuarioRepository) {
		postagemService = new PostagemService(postagemRepository, new UsuarioService(usuarioRepository));
	}

	@Test
	public void deveBuscarPostagem() {
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setLogin("teste.higor");
		usuario.setSenha("123456");
		Postagem postagem = iniciarPostagem(usuario);
		assertNotNull(postagem.getId());
		Optional<Postagem> postagemRecuperadaO = postagemService.buscarPostagem(postagem.getId());
		assertTrue(postagemRecuperadaO.isPresent());
	}

	@Test
	public void naoDeveBuscarPostagem() {
		Optional<Postagem> postagemRecuperadaO = postagemService.buscarPostagem(-1L);
		assertFalse(postagemRecuperadaO.isPresent());
	}

	@Test
	public void deveCriarPostagem() {
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setLogin("teste.higor");
		usuario.setSenha("123456");
		Postagem postagem = iniciarPostagem(usuario);
		assertNotNull(postagem.getId());
	}

	@Test
	public void naoDeveCriarPostagem() {
		boolean postagemCriada = true;
		Usuario usuario = new Usuario();
		usuario.setId(-1L);
		Postagem postagem = new Postagem();
		postagem.setTitulo("Postagem 1");
		postagem.setUsuario(usuario);
		try {
			postagemService.salvar(usuario, postagem);
		} catch (RegistroNaoEncontradoException e) {
			postagemCriada = false;
		}
		assertFalse(postagemCriada);
	}

	@Test
	public void deveDeletarPostagem() {
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setLogin("teste.higor");
		usuario.setSenha("123456");
		Postagem postagem = iniciarPostagem(usuario);
		assertNotNull(postagem.getId());
		postagemService.deletar(postagem.getId(), usuario);
		Optional<Postagem> postagemRecuperadaO = postagemService.buscarPostagem(postagem.getId());
		assertFalse(postagemRecuperadaO.isPresent());
	}

	@Test
	public void naoDeveDeletarPostagem() {
		boolean postagemDeletada = true;
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		Postagem postagem = iniciarPostagem(usuario);
		assertNotNull(postagem.getId());
		Usuario segundoUsuario = new Usuario();
		segundoUsuario.setId(333333L);
		try {
			postagemService.deletar(postagem.getId(), segundoUsuario);
		} catch (RegistroNaoEncontradoException e) {
			postagemDeletada = false;
		}
		assertFalse(postagemDeletada);
	}

	@Test
	public void deveBuscarPostagemResponse() {
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		Postagem postagem = iniciarPostagem(usuario);
		assertNotNull(postagem.getId());
		PostagemResponse postagemResponse = postagemService.buscar(postagem.getId());
		assertEquals(postagem.getId(), postagemResponse.getId());
		assertEquals(postagem.getTitulo(), postagemResponse.getTitulo());
		assertEquals(postagem.getConteudoPostagem().getTexto(), postagemResponse.getTexto());
	}

	@Test
	public void deveBuscarPostagensResponse() {
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		Postagem postagem = iniciarPostagem(usuario);
		assertNotNull(postagem.getId());
		Page<PostagemResponse> postagemResponseList = postagemService.buscar("", PageRequest.of(0, 10));
		assertFalse(postagemResponseList.isEmpty());

		Optional<PostagemResponse> postagemResponseO = postagemResponseList.stream().filter(p -> p.getId().equals(postagem.getId())).findFirst();
		assertTrue(postagemResponseO.isPresent());

		PostagemResponse postagemResponse = postagemResponseO.get();
		assertEquals(postagem.getId(), postagemResponse.getId());
		assertEquals(postagem.getTitulo(), postagemResponse.getTitulo());
		assertEquals(postagem.getConteudoPostagem().getTexto(), postagemResponse.getTexto());
		assertEquals(postagem.getId(), postagemResponse.getId());
		assertEquals(postagem.getTitulo(), postagemResponse.getTitulo());
		assertEquals(postagem.getConteudoPostagem().getTexto(), postagemResponse.getTexto());
	}

}
