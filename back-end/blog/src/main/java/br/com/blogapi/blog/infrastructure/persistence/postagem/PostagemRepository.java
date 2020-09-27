package br.com.blogapi.blog.infrastructure.persistence.postagem;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.blogapi.blog.infrastructure.persistence.usuario.Usuario;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {

	Optional<Postagem> findByIdAndUsuario(Long id, Usuario usuarioAtual);

	@Query("SELECT p FROM Postagem p WHERE p.conteudoPostagem.texto LIKE %:texto% OR p.titulo LIKE %:texto%")
	Page<Postagem> findByTexto(@Param("texto") String texto, Pageable paginacao);

}
