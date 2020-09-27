
package br.com.blogapi.blog.domain.postagem;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

	@Autowired
	private PostagemRepository postagemRepository;

	@Autowired
	private UsuarioService usuarioService;

	public void deletar(Long id, Usuario usuarioAtual) {
		Optional<Postagem> postagemO = postagemRepository.findByIdAndUsuario(id, usuarioAtual);
		if (postagemO.isPresent()) {
			postagemRepository.delete(postagemO.get());
			return;
		}
		throw new RegistroNaoEncontradoException("Postagem nao encontrada");
	}

	public void salvar(Postagem postagem) {
		Optional<Usuario> usuarioO = usuarioService.buscarUsuario(postagem.getUsuario().getId());
		postagem.setUsuario(usuarioO.get());
		postagemRepository.save(postagem);
	}

	public Optional<Postagem> buscarPostagem(Long postId) {
		return postagemRepository.findById(postId);
	}

	public List<PostagemResponse> buscar(Integer page, Integer size) {
		Page<Postagem> postagens = postagemRepository.findAll(PageRequest.of(page, size));
		if (postagens.isEmpty()) {
			throw new RegistroNaoEncontradoException("Nenhuma Postagem encontrada");
		}
		List<PostagemResponse> postagemResponseList = postagens.stream().map(this::convertToPostagemResponse).collect(Collectors.toList());
		return postagemResponseList;
	}

	public PostagemResponse buscar(Long id) {
		Optional<Postagem> postagemO = postagemRepository.findById(id);
		if (postagemO.isPresent()) {
			Postagem postagem = postagemO.get();
			return convertToPostagemResponse(postagem);
		}
		throw new RegistroNaoEncontradoException("Postagem nao encontrada");
	}

	private PostagemResponse convertToPostagemResponse(Postagem postagem) {
		List<ComentarioResponse> comentarios = postagem.getComentarios().stream().map(ComentarioResponse::create).collect(Collectors.toList());
		PostagemResponse postagemResponse = new PostagemResponse();
		postagemResponse.setId(postagem.getId());
		postagemResponse.setTexto(postagem.getTexto());
		postagemResponse.setComentarios(comentarios);
		return postagemResponse;
	}
}
