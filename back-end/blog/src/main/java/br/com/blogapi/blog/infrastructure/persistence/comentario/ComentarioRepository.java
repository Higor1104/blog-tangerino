package br.com.blogapi.blog.infrastructure.persistence.comentario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.blogapi.blog.infrastructure.persistence.postagem.Postagem;
import br.com.blogapi.blog.infrastructure.persistence.usuario.Usuario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

	Optional<Comentario> findByIdAndPostagemAndUsuario(Long id, Postagem postagem, Usuario usuarioAtual);

	Optional<Comentario> findByIdAndPostagem(Long id, Postagem postagem);

}
