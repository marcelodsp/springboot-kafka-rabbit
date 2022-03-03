package br.com.marcelodsp.kafkateste.documents;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonInclude(value = Include.NON_NULL)
@Data
@Document
public class Funcionario {

	@Id
	private String id;
	private String nome;
	private String matricula;
	private String cargo;
	private LocalDate dataNascimento;

}
