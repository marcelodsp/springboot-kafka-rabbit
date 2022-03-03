package br.com.marcelodsp.rabbitteste.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@CrossOrigin(origins = "*")
@RestControllerAdvice
public class ApplicationControllerAdvice {

	// manipula exceptions de erros não tratados
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorDto handleRuntimeException(RuntimeException ex) {
		return new ErrorDto(ex.getMessage());
	}

	// manipula exceptions de validação
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDto handleMethodNotValidException(MethodArgumentNotValidException ex) {
		List<String> errors = ex.getBindingResult()
				.getAllErrors()
				.stream()
				.map(ObjectError::getDefaultMessage)
				.collect(Collectors.toList());
		return new ErrorDto(errors);
	}

}
