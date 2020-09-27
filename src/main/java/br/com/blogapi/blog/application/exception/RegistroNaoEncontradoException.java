package br.com.blogapi.blog.application.exception;

public class RegistroNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = -6083167027928817154L;

	public RegistroNaoEncontradoException(String message) {
		super(message);
	}

}
