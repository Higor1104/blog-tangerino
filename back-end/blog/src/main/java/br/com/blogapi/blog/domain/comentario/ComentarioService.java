package br.com.blogapi.blog.domain.comentario;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blogapi.blog.application.exception.RegistroNaoEncontradoException;
import br.com.blogapi.blog.domain.postagem.PostagemService;
import br.com.blogapi.blog.domain.usuario.UsuarioService;
import br.com.blogapi.blog.infrastructure.persistence.comentario.Comentario;
import br.com.blogapi.blog.infrastructure.persistence.comentario.ComentarioRepository;
import br.com.blogapi.blog.infrastructure.persistence.postagem.Postagem;
import br.com.blogapi.blog.infrastructure.persistence.usuario.Usuario;

@Service
public class ComentarioService {

	private ComentarioRepository comentarioRepository;
	private PostagemService postagemService;
	private UsuarioService usuarioService;

	@Autowired
	public ComentarioService(ComentarioRepository comentarioRepository
			, PostagemService postagemService,
			UsuarioService usuarioService) {
		this.comentarioRepository = comentarioRepository;
		this.postagemService = postagemService;
		this.usuarioService = usuarioService;
		
	}

	public void incluir(Usuario usuarioAtual, Long postId, Comentario comentario) {
		Usuario usuario = usuarioService.buscarUsuario(usuarioAtual.getId()).get();
		Optional<Postagem> postagemO = postagemService.buscarPostagem(postId);
		if (postagemO.isPresent()) {
			Postagem postagem = postagemO.get();
			comentario.setUsuario(usuario);
			comentario.setPostagem(postagem);
			comentarioRepository.save(comentario);
			return;
		}
		throw new RegistroNaoEncontradoException("Postagem nao encontrada");
	}

	public void deletar(Usuario usuarioAtual, Long postagemId, Long comentarioId) {
		Postagem postagem = new Postagem();
		postagem.setId(postagemId);
		Optional<Comentario> comentarioO = comentarioRepository.findByIdAndPostagemAndUsuario(comentarioId, postagem, usuarioAtual);
		if (comentarioO.isPresent()) {
			comentarioRepository.delete(comentarioO.get());
			return;
		}
		throw new RegistroNaoEncontradoException("Comentario nao encontrado");
	}

	public Comentario buscar(Long postagemId, Long comentarioId) {
		Postagem postagem = new Postagem();
		postagem.setId(postagemId);
		Optional<Comentario> comentarioO = comentarioRepository.findByIdAndPostagem(comentarioId, postagem);
		if (comentarioO.isPresent()) {
			return comentarioO.get();
		}
		throw new RegistroNaoEncontradoException("Comentario nao encontrado");
	}

}
