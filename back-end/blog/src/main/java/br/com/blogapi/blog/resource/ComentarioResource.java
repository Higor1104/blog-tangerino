package br.com.blogapi.blog.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blogapi.blog.domain.comentario.ComentarioService;
import br.com.blogapi.blog.infrastructure.persistence.comentario.Comentario;
import br.com.blogapi.blog.resource.dto.ComentarioRequest;
import br.com.blogapi.blog.resource.dto.ComentarioResponse;

@RestController
@RequestMapping(value = "/postagem/{post_id}/comentario")
public class ComentarioResource extends BaseResource {

	@Autowired
	private ComentarioService comentarioService;

	@PostMapping
	public ResponseEntity<Long> incluir(@PathVariable("post_id") Long postId, @RequestBody @Valid ComentarioRequest comentarioRequest) {
		Comentario comentario = new Comentario();
		comentario.setTexto(comentarioRequest.getTexto());
		comentarioService.incluir(recuperarUsuarioAtual(), postId, comentario);
		return ResponseEntity.ok(comentario.getId());
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<ComentarioResponse> buscarPorId(@PathVariable("post_id") Long postId, @PathVariable("id") Long id) {
		Comentario comentario = comentarioService.buscar(postId, id);
		ComentarioResponse comentarioResponse = new ComentarioResponse();
		comentarioResponse.setId(comentario.getId());
		comentarioResponse.setTexto(comentario.getTexto());
		return ResponseEntity.ok(comentarioResponse);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> delete(@PathVariable("post_id") Long postId, @PathVariable("id") Long id) {
		comentarioService.deletar(recuperarUsuarioAtual(), postId, id);
		return ResponseEntity.ok().build();
	}

}
