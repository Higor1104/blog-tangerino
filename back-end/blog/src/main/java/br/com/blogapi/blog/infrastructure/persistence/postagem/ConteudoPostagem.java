package br.com.blogapi.blog.infrastructure.persistence.postagem;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_conteudo_postagem")
public class ConteudoPostagem {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_conteudo_postagem")
	@Column(name = "contpost_id", unique = true)
	private Long id;

	@Column(name = "contpost_texto", nullable = false)
	private String texto;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "post_id", nullable = false, foreignKey=@ForeignKey(name = "fk_postagem_conteudo_postagem"))
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

}
