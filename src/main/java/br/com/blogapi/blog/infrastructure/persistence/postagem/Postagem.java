package br.com.blogapi.blog.infrastructure.persistence.postagem;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.blogapi.blog.infrastructure.persistence.comentario.Comentario;
import br.com.blogapi.blog.infrastructure.persistence.usuario.Usuario;

@Entity
@Table(name = "tb_postagem")
public class Postagem {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_postagem")
	@Column(name = "post_id", unique = true)
	private Long id;

	@Column(name = "post_titulo", nullable = false)
	private String titulo;

	@OneToOne(mappedBy="postagem", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private ConteudoPostagem conteudoPostagem;

	@ManyToOne(optional = false)
	@JoinColumn(name = "usu_id", nullable = false)
	private Usuario usuario;

	@OneToMany(mappedBy="postagem", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Comentario> comentarios;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public ConteudoPostagem getConteudoPostagem() {
		return conteudoPostagem;
	}

	public void setConteudoPostagem(ConteudoPostagem conteudoPostagem) {
		this.conteudoPostagem = conteudoPostagem;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
