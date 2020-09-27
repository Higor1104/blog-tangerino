package br.com.blogapi.blog.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blogapi.blog.application.exception.CadastroUsuarioException;
import br.com.blogapi.blog.application.resourcehandler.Messages;
import br.com.blogapi.blog.domain.Constantes;
import br.com.blogapi.blog.domain.auth.AuthService;
import br.com.blogapi.blog.domain.auth.LoginForm;
import br.com.blogapi.blog.domain.usuario.UsuarioService;
import br.com.blogapi.blog.infrastructure.persistence.usuario.Usuario;
import br.com.blogapi.blog.resource.dto.UsuarioRequest;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioResource {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private AuthService authService;

	@PostMapping
	public ResponseEntity<Messages> criar(@RequestBody @Valid UsuarioRequest usuarioRequest) throws CadastroUsuarioException {
		Usuario usuario = new Usuario();
		usuario.setLogin(usuarioRequest.getLogin());
		usuario.setSenha(usuarioRequest.getSenha());
		usuarioService.salvar(usuario);
		return ResponseEntity.ok(new Messages().addMessage("Usuário criado com sucesso"));
	}

	@PostMapping(value = "/login")
	public ResponseEntity<?> login(@RequestBody @Valid LoginForm loginForm) {
		Authentication authentication = authManager.authenticate(loginForm.createAutenticator());
		String token = authService.gerarToken(authentication);
		BodyBuilder ok = ResponseEntity.ok();
		ok.header(Constantes.AUTHORIZATION_HEADER, Constantes.BEARER_PREFIX.concat(token));
		return ok.build();
	}

}
