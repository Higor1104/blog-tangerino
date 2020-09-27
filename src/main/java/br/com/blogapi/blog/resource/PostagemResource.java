package br.com.blogapi.blog.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.blogapi.blog.domain.postagem.PostagemService;
import br.com.blogapi.blog.infrastructure.persistence.postagem.ConteudoPostagem;
import br.com.blogapi.blog.infrastructure.persistence.postagem.Postagem;
import br.com.blogapi.blog.infrastructure.persistence.usuario.Usuario;
import br.com.blogapi.blog.resource.dto.PostagemRequest;
import br.com.blogapi.blog.resource.dto.PostagemResponse;

@RestController
@RequestMapping(value = "/postagem")
public class PostagemResource  extends BaseResource {

	@Autowired
	private PostagemService postagemService;

	@PostMapping
	public ResponseEntity<Long> incluir(@RequestBody @Valid PostagemRequest postagemRequest) {
		Usuario usuarioAtual = recuperarUsuarioAtual();
		Postagem postagem = new Postagem();
		postagem.setTitulo(postagemRequest.getTitulo());
		ConteudoPostagem conteudoPostagem = new ConteudoPostagem();
		conteudoPostagem.setTexto(postagemRequest.getTexto());
		conteudoPostagem.setPostagem(postagem);
		postagem.setConteudoPostagem(conteudoPostagem);;
		postagem.setUsuario(usuarioAtual);
		postagemService.salvar(postagem);
		return ResponseEntity.ok(postagem.getId());
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		Usuario usuarioAtual = recuperarUsuarioAtual();
		postagemService.deletar(id, usuarioAtual);
		return ResponseEntity.ok().build();
	}

	@GetMapping
	public ResponseEntity<List<PostagemResponse>> buscar(@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size) {
		List<PostagemResponse> postagemResponseList = postagemService.buscar(page, size);
		return ResponseEntity.ok(postagemResponseList);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<PostagemResponse> buscarPorId(@PathVariable("id") Long id) {
		PostagemResponse postagemResponse = postagemService.buscar(id);
		return ResponseEntity.ok(postagemResponse);
	}

}
