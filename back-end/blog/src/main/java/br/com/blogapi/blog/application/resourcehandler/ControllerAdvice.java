package br.com.blogapi.blog.application.resourcehandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.blogapi.blog.application.exception.CadastroUsuarioException;
import br.com.blogapi.blog.application.exception.RegistroNaoEncontradoException;

@RestControllerAdvice
public class ControllerAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		Messages messages = new Messages();
		ex.getBindingResult().getFieldErrors().stream().forEach(x -> messages.addMessage(x.getField() + " - " + x.getDefaultMessage()));
		return new ResponseEntity<>(messages, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({UsernameNotFoundException.class, CadastroUsuarioException.class})
	protected ResponseEntity<Messages> handleUsernameNotFoundException(Exception ex) {
		Messages messages = new Messages(ex.getMessage());
		return new ResponseEntity<>(messages, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RegistroNaoEncontradoException.class)
	protected ResponseEntity<Messages> RegistroNaoEncontradoException(Exception ex) {
		Messages messages = new Messages(ex.getMessage());
		return new ResponseEntity<>(messages, HttpStatus.NOT_FOUND);
	}

}
