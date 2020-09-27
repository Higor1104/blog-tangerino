package br.com.blogapi.blog.service;

import br.com.blogapi.blog.domain.postagem.PostagemService;
import br.com.blogapi.blog.infrastructure.persistence.postagem.ConteudoPostagem;
import br.com.blogapi.blog.infrastructure.persistence.postagem.Postagem;
import br.com.blogapi.blog.infrastructure.persistence.usuario.Usuario;

public class BaseServiceTest {

	protected PostagemService postagemService;

	protected Postagem iniciarPostagem(Usuario usuario) {
		ConteudoPostagem conteudoPostagem = new ConteudoPostagem();
		conteudoPostagem.setTexto("Texto da postagem");
		Postagem postagem = new Postagem();
		postagem.setTitulo("Postagem 1");
		postagem.setConteudoPostagem(conteudoPostagem);
		postagem.setUsuario(usuario);
		conteudoPostagem.setPostagem(postagem);
		postagemService.salvar(postagem);
		return postagem;
	}

}
