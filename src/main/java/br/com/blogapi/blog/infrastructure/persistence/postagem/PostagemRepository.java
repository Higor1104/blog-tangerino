package br.com.blogapi.blog.infrastructure.persistence.postagem;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.blogapi.blog.infrastructure.persistence.usuario.Usuario;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {

	Optional<Postagem> findByIdAndUsuario(Long id, Usuario usuarioAtual);

}
