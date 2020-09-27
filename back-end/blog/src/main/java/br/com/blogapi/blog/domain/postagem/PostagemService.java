
package br.com.blogapi.blog.domain.postagem;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.blogapi.blog.application.exception.RegistroNaoEncontradoException;
import br.com.blogapi.blog.domain.usuario.UsuarioService;
import br.com.blogapi.blog.infrastructure.persistence.postagem.Postagem;
import br.com.blogapi.blog.infrastructure.persistence.postagem.PostagemRepository;
import br.com.blogapi.blog.infrastructure.persistence.usuario.Usuario;
import br.com.blogapi.blog.resource.dto.ComentarioResponse;
import br.com.blogapi.blog.resource.dto.PostagemResponse;

@Service
public class PostagemService {

	private PostagemRepository postagemRepository;

	private UsuarioService usuarioService;

	@Autowired
	public PostagemService(PostagemRepository postagemRepository,
			UsuarioService usuarioService) {
		this.postagemRepository = postagemRepository;
		this.usuarioService = usuarioService;
	}

	public void deletar(Long id, Usuario usuarioAtual) {
		Optional<Postagem> postagemO = postagemRepository.findByIdAndUsuario(id, usuarioAtual);
		if (postagemO.isPresent()) {
			postagemRepository.delete(postagemO.get());
			return;
		}
		throw new RegistroNaoEncontradoException("Postagem nao encontrada");
	}

	public void salvar(Usuario usuario, Postagem postagem) {
		Optional<Usuario> usuarioO = usuarioService.buscarUsuario(usuario.getId());
		if (usuarioO.isPresent()) {
			postagem.setUsuario(usuarioO.get());
			postagemRepository.save(postagem);
		} else {
			throw  new RegistroNaoEncontradoException("Usuario nao encontrado");
		}
	}

	public Optional<Postagem> buscarPostagem(Long postId) {
		return postagemRepository.findById(postId);
	}

	public Page<PostagemResponse> buscar(String texto, Pageable paginacao) {
		Page<Postagem> postagens = postagemRepository.findByTexto(texto, paginacao);
		if (postagens.isEmpty()) {
			throw new RegistroNaoEncontradoException("Nenhuma Postagem encontrada");
		}
		List<PostagemResponse> postagemResponseList = postagens.stream().map(this::convertToPostagemResponseSimples).collect(Collectors.toList());
		return new PageImpl<PostagemResponse>(postagemResponseList, paginacao, postagemResponseList.size());
	}

	public PostagemResponse buscar(Long id) {
		Optional<Postagem> postagemO = postagemRepository.findById(id);
		if (postagemO.isPresent()) {
			Postagem postagem = postagemO.get();
			return convertToPostagemResponseCompleta(postagem);
		}
		throw new RegistroNaoEncontradoException("Postagem nao encontrada");
	}

	private PostagemResponse convertToPostagemResponseSimples(Postagem postagem) {
		PostagemResponse postagemResponse = new PostagemResponse();
		postagemResponse.setId(postagem.getId());
		postagemResponse.setTitulo(postagem.getTitulo());
		return postagemResponse;
	}

	private PostagemResponse convertToPostagemResponseCompleta(Postagem postagem) {
		List<ComentarioResponse> comentarios = postagem.getComentarios().stream().map(ComentarioResponse::create).collect(Collectors.toList());
		PostagemResponse postagemResponse = new PostagemResponse();
		postagemResponse.setId(postagem.getId());
		postagemResponse.setTitulo(postagem.getTitulo());
		postagemResponse.setTexto(postagem.getConteudoPostagem().getTexto());
		postagemResponse.setComentarios(comentarios);
		return postagemResponse;
	}
}
