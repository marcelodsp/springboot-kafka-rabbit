package br.com.marcelodsp.kafkateste.controller;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public class ErrorDto {

	@Getter
	private List<String> errors;

	public ErrorDto(List<String> errors) {
		this.errors = errors;
	}

	public ErrorDto(String mensagemErro) {
		this.errors = Arrays.asList(mensagemErro);
	}
}
