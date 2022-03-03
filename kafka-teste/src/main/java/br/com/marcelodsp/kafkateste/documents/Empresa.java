package br.com.marcelodsp.kafkateste.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonInclude(value = Include.NON_NULL)
@Data
@Document
public class Empresa {

    @Id
    private String id;
    private String nome;
    private String setor;
}
