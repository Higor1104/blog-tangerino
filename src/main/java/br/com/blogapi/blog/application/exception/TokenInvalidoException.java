package br.com.blogapi.blog.application.exception;

public class TokenInvalidoException extends RuntimeException {

	private static final long serialVersionUID = -6361005873044081958L;

	public TokenInvalidoException(String message) {
		super(message);
	}
}
