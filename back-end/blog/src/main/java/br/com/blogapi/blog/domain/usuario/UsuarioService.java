package br.com.blogapi.blog.domain.usuario;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.blogapi.blog.application.exception.CadastroUsuarioException;
import br.com.blogapi.blog.infrastructure.persistence.usuario.Usuario;
import br.com.blogapi.blog.infrastructure.persistence.usuario.UsuarioRepository;

@Service
public class UsuarioService {

	private UsuarioRepository usuarioRepository;

	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;	
	}

	public void salvar(Usuario usuario) throws CadastroUsuarioException {
		validarCadastroUsuario(usuario);
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
		usuarioRepository.save(usuario);
	}

	private void validarCadastroUsuario(Usuario usuario) throws CadastroUsuarioException {
		Long numeroUsuariosCadastrados = usuarioRepository.countByLogin(usuario.getLogin());
		if (numeroUsuariosCadastrados > 0) {
			throw new CadastroUsuarioException("Usuário já cadastrado");
		}
	}

	@Cacheable(value = "Usuario.ID")
	public Optional<Usuario> buscarUsuario(Long id) {
		return usuarioRepository.findById(id);
	}

}
