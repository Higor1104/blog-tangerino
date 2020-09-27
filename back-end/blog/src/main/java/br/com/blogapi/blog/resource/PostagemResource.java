package br.com.blogapi.blog.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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
		Postagem postagem = new Postagem();
		postagem.setTitulo(postagemRequest.getTitulo());
		ConteudoPostagem conteudoPostagem = new ConteudoPostagem();
		conteudoPostagem.setTexto(postagemRequest.getTexto());
		conteudoPostagem.setPostagem(postagem);
		postagem.setConteudoPostagem(conteudoPostagem);;
		postagemService.salvar(recuperarUsuarioAtual(), postagem);
		return ResponseEntity.ok(postagem.getId());
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		Usuario usuarioAtual = recuperarUsuarioAtual();
		postagemService.deletar(id, usuarioAtual);
		return ResponseEntity.ok().build();
	}

	@GetMapping
	public ResponseEntity<Page<PostagemResponse>> buscar(@RequestParam(required = false, defaultValue = "") String texto, 
			@PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable paginacao) {
		Page<PostagemResponse> postagemResponseList = postagemService.buscar(texto, paginacao);
		return ResponseEntity.ok(postagemResponseList);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<PostagemResponse> buscarPorId(@PathVariable("id") Long id) {
		PostagemResponse postagemResponse = postagemService.buscar(id);
		return ResponseEntity.ok(postagemResponse);
	}

}
