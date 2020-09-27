package br.com.blogapi.blog.infrastructure.persistence.comentario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.blogapi.blog.infrastructure.persistence.postagem.Postagem;
import br.com.blogapi.blog.infrastructure.persistence.usuario.Usuario;

@Entity
@Table(name = "tb_comentario")
public class Comentario {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_comentario")
	@Column(name = "coment_id", unique = true)
	private Long id;

	@Column(name = "coment_texto", nullable = false)
	private String texto;

	@ManyToOne(optional = false)
	@JoinColumn(name = "usu_id", nullable = false, foreignKey=@ForeignKey(name = "fk_postagem_usuario"))
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "post_id", nullable = false, foreignKey=@ForeignKey(name = "fk_postagem_comentario"))
	private Postagem postagem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Postagem getPostagem() {
		return postagem;
	}

	public void setPostagem(Postagem postagem) {
		this.postagem = postagem;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
