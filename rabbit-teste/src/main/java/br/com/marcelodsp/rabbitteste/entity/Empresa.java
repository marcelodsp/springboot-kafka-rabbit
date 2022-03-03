package br.com.marcelodsp.rabbitteste.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonInclude(value = Include.NON_NULL)
@Data
@Entity
public class Empresa {

    @Id
    private String id;
    private String nome;
    private String setor;

}
